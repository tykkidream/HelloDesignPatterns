package hello.designpatterns.batch.task;

import hello.designpatterns.batch.lock.LockInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Consumer;

/**
 * 【】批量执行记录
 * =====================================================================================================================
 *
 * 在批量处理中，包装 ImportsTask ，比如可以暂存批量处理中间步骤中的失败，以方便各种中间步骤和最终步骤的一些批量处理。
 *
 * 使用时就不要直接使用 ImportsTask 了。
 *
 * 批量处理多个 ImportsTask ，处理时并不一定所有 ImportsTask 都能成功，有的可能会在参数校验时失败，有的可能在状态校验时失败，失败的位置会有很多种。
 * 且由于不得影响其它 ImportsTask 执行后续的批量处理，所以要以“静默”的方式中止失败的任务，暂存失败时的状态或信息。
 *
 * 主要功能：
 *
 * 1. 标识 ImportsTask 是否执行了批量处理。比如在中间步骤失败时要标识为已执行，最终完成时也要标记为已执行。
 * 2. 记录失败状态或信息。包括 ImportsTaskExecuteLog、Throwable。
 *
 */
public class TaskBatchExecutionRecord implements TaskReference {
    private static final Logger logger = LoggerFactory.getLogger(TaskBatchExecutionRecord.class);

    /**
     * 是否执行了批量处理
     */
    private TaskSyncState state;

    /**
     *
     */
    private Task task;

    /**
     * 批量处理打败时的异常对象
     */
    private Throwable throwable;

    private String businessCode;

    private String businessMessage;

    private LockInfo<TaskBatchExecutionRecord> lockInfo;

    public TaskBatchExecutionRecord() {
    }

    public TaskBatchExecutionRecord(Task task) {
        setTask(task);
    }

    public TaskBatchExecutionRecord(Task task, Throwable throwable) {
        setTask(task);
        executeFailure(throwable);
    }

    public String taskNo() {
        return task.getNo();
    }

    /**
     * 执行忽略
     *
     * @param businessCode
     * @param businessMessage
     */
    public void executeIgnore(String businessCode, String businessMessage) {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteIgnore;

        this.businessCode = businessCode;
        this.businessMessage = businessMessage;
    }

    /**
     * 执行延后
     *
     * @param businessCode
     * @param businessMessage
     */
    public void executeDelay(String businessCode, String businessMessage) {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteDelay;

        this.businessCode = businessCode;
        this.businessMessage = businessMessage;
    }

    public void executeFailure(Throwable throwable) {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteFailure;

        this.throwable = throwable;
    }

    /**
     * 执行失败。失败多次为执行异常
     *
     *
     * @param businessCode
     * @param businessMessage
     */
    public void executeFailure(String businessCode, String businessMessage) {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteFailure;

        this.businessCode = businessCode;
        this.businessMessage = businessMessage;
    }

    /**
     * 执行成功
     *
     */
    public void executeSuccess() {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteSuccess;
    }

    /**
     * 执行成功
     *
     * @param businessCode
     * @param businessMessage
     */
    public void executeSuccess(String businessCode, String businessMessage) {
        if (isDone()) {
            return;
        }

        this.state = TaskSyncState.ExecuteSuccess;

        this.businessCode = businessCode;
        this.businessMessage = businessMessage;
    }

    public void executeDone() {
        if (isInProgress()) {
            this.state = TaskSyncState.ExecuteSuccess;
        }
    }

    public boolean execute(Consumer<Task> consumer) {
        if (isDone()) {
            return false;
        }

        try {
            consumer.accept(task);
            return true;
        } catch (Throwable throwable) {
            this.state = TaskSyncState.ExecuteFailure;
            this.throwable = throwable;

            if (throwable instanceof SynchronizationException) {
                if (logger.isWarnEnabled()) {
                    logger.warn("批量执行【任务】管控失败，taskId：{} taskNo：{} code：{} message：{}", task.getId(),
                            task.getNo(), ((SynchronizationException) throwable).getCode(), throwable.getMessage(), throwable);
                }
            } else if (throwable instanceof BusinessException) {
                if (logger.isErrorEnabled()) {
                    logger.error("批量执行【任务】业务失败，taskId：{} taskNo：{} code：{} message：{}",
                            task.getId(), task.getNo(), ((BusinessException) throwable).getCode(), throwable.getMessage(), throwable);
                }
            } else {
                if (logger.isErrorEnabled()) {
                    logger.error("批量执行【任务】未知异常，taskId：{} taskNo：{} message：{}",
                            task.getId(), task.getNo(), throwable.getMessage(), throwable);
                }
            }

        }

        return false;
    }

    /**
     * 是否已结束
     *
     * @return
     */
    public boolean isDone() {
        return state != null;
    }

    /**
     * 是否可继续处理中
     *
     * @return
     */
    public boolean isInProgress(){
        return state == null;
    }

    public boolean isLocked() {
        return lockInfo != null && lockInfo.locked();
    }

    /* •••••••••••••••••••••••••••••••••••••••装••订••线••内••禁••止••作••答••否••则••记••零••分••••••••••••••••••••••••••••••••••••••• */

    public TaskSyncState getState() {
        return state;
    }

    public void setState(TaskSyncState state) {
        if (state != null) {
            this.state = state;
        }
    }

    @Override
    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        if (task != null) {
            this.task = task;
        }
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        if (throwable != null) {
            this.throwable = throwable;
        }
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        if (businessCode != null) {
            this.businessCode = businessCode;
        }
    }

    public String getBusinessMessage() {
        return businessMessage;
    }

    public void setBusinessMessage(String businessMessage) {
        if (businessMessage != null) {
            this.businessMessage = businessMessage;
        }
    }

    public LockInfo<TaskBatchExecutionRecord> getLockInfo() {
        return lockInfo;
    }

    public void setLockInfo(LockInfo<TaskBatchExecutionRecord> lockInfo) {
        if (lockInfo != null) {
            this.lockInfo = lockInfo;
        }
    }
}
