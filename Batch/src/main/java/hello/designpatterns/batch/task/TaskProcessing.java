package hello.designpatterns.batch.task;

import java.util.Date;

public class TaskProcessing {
    /**
     * 处理状态
     */
    private TaskSyncState state;

    /**
     * 处理状态码
     */
    private String statusCode;

    /**
     * 处理次数
     */
    private Integer times;

    /**
     * 最后处理时间
     */
    private Date latestTime;

    /**
     * 同步成功的方式（客户主动请求同步、推送同步）
     *
     * TODO 需要考虑是否需要此字段，需要需要则还要考虑最佳方式
     */
    private String method;

    public TaskProcessing() {
        setState(TaskSyncState.None);
        setStatusCode(NullValue.EmptyString);
        setTimes(0);
        setLatestTime(NullValue.NullDate);
    }

    public boolean isExecuting() {
        if (state.isExecuteFailure()) {
            return true;
        }
        if (state.isExecuteException()) {
            return true;
        }
        if (state.isExecuteSuccess()) {
            return true;
        }

        return false;
    }

    public boolean isExecuteDelay() {
        return state.isExecuteDelay();
    }

    public boolean isExecuteIgnore() {
        return state.isExecuteIgnore();
    }

    public boolean isExecuteFailure() {
        return state.isExecuteFailure();
    }

    public boolean isExecuteException() {
        return state.isExecuteException();
    }

    public boolean isDispatchSuccess() {
        return state.isDispatchSuccess();
    }

    public void ignore(String statusCode, Date executionTime) {
        // TODO 已经成功的不可忽略
        setStatusCode(statusCode);
        setLatestTime(executionTime);
        setState(TaskSyncState.ExecuteIgnore);
    }

    public void delay(String statusCode, Date executionTime) {
        // TODO 已经成功的不可延迟
        setStatusCode(statusCode);
        setLatestTime(executionTime);
        setState(TaskSyncState.ExecuteDelay);
    }

    public void failure(String statusCode, Date executionTime) {
        // TODO 已经成功的不可失败
        setStatusCode(statusCode);
        setTimes(times + 1);
        setLatestTime(executionTime);
        if (times >= 3) {
            setState(TaskSyncState.ExecuteException);
        } else {
            setState(TaskSyncState.ExecuteFailure);
        }
    }

    public void success(String statusCode, Date executionTime) {
        // TODO 已经成功的不可重复成功
        setStatusCode(statusCode);
        setTimes(times + 1);
        setLatestTime(executionTime);
        setState(TaskSyncState.ExecuteSuccess);
    }

    public void update(TaskSyncState state, String statusCode, Date executionTime) {
        // TODO 已经成功的不可做其它任何事
        setStatusCode(statusCode);
        setTimes(times + 1);
        setLatestTime(executionTime);
        setState(state);

        if (state == TaskSyncState.ExecuteFailure && times >= 3) {
            setState(TaskSyncState.ExecuteException);
        }
    }

    /* •••••••••••••••••••••••••••••••••••••••装••订••线••内••禁••止••作••答••否••则••记••零••分••••••••••••••••••••••••••••••••••••••• */

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    protected void toString(StringBuilder sb) {
        sb.append("{");
        sb.append("\"state\":\"").append(state).append("\"");
        sb.append(",\"statusCode\":\"").append(statusCode).append('\"');
        sb.append(",\"times\":").append(times);
        sb.append(",\"latestTime\":").append(latestTime.getTime());
        sb.append('}');
    }

    public TaskSyncState getState() {
        return state;
    }

    public void setState(TaskSyncState state) {
        if (state != null) {
            this.state = state;
        }
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        if (statusCode != null) {
            this.statusCode = statusCode;
        }
    }

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        if (times != null) {
            this.times = times;
        }
    }

    public Date getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(Date latestTime) {
        if (latestTime != null) {
            this.latestTime = latestTime;
        }
    }

}
