package hello.designpatterns.batch.task;


import java.util.Date;

/**
 * 任务
 */
public class Task implements TaskReference {

    private Long id;

    /**
     * 上一次任务（本次任务是重复执行了上一次任务）
     */
    private Long parentId;

    /**
     * 下一个任务（按顺序排序后要执行下一个任务）
     */
    private Long nextId;

    /**
     * 唯一编号
     */
    private String no;

    /**
     * 客户端（上游）提交数据的批次编号
     */
    private String commitNo;

    /**
     * 渠道编号
     */
    private String channelNo;

    /**
     * 业务信息
     */
    private TaskBusiness business;

    /**
     * 处理信息
     */
    private TaskProcessing processing;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date modifyTime;

    /**
     * 版本号
     */
    private Integer version;

    public Task() {
        setId(0L);
        setParentId(0L);
        setNextId(0L);
        setNo(NullValue.EmptyString);
        setCommitNo(NullValue.EmptyString);
        setChannelNo(NullValue.EmptyString);
        setBusiness(new TaskBusiness());
        setProcessing(new TaskProcessing());
        setCreateTime(NullValue.NullDate);
        setModifyTime(NullValue.NullDate);
        setVersion(0);
    }

    public Long id() {
        return id;
    }

    public String no() {
        return no;
    }

    public String channelNo() {
        return channelNo;
    }

    public String businessTransactionNo() {
        return business.getTransactionNo();
    }

    public String businessType() {
        return business.getType();
    }

    public boolean dispatchedExtendBy(Task headTask, Date currentTime) {
        if (processing.isDispatchSuccess()) {
            return false;
        }

        processing.setState(TaskSyncState.DispatchSuccess);
        setModifyTime(currentTime);

        if (headTask != null) {
            headTask.setNextId(id);
            headTask.setModifyTime(currentTime);
        }

        return true;
    }

    public void dispatchRepeatIgnore(Date currentTime) {
        processing.setState(TaskSyncState.DispatchRepeatIgnore);
        setNextId(0L);
        setModifyTime(currentTime);
    }

    public void dispatchOutdatedIgnore(Date currentTime) {
        processing.setState(TaskSyncState.DispatchOutdatedIgnore);
        setNextId(0L);
        setModifyTime(currentTime);
    }

    public void executeIgnore(String statusCode, Date executionTime) {
        processing.ignore(statusCode, executionTime);
        setModifyTime(executionTime);
    }

    public void executeDelay(String statusCode, Date executionTime) {
        processing.delay(statusCode, executionTime);
        setModifyTime(executionTime);
    }

    public void executeFailure(String statusCode, Date executionTime) {
        processing.failure(statusCode, executionTime);
        setModifyTime(executionTime);
    }

    public void executeSuccess(String statusCode, Date executionTime) {
        processing.success(statusCode, executionTime);
        setModifyTime(executionTime);
    }

    public void execute(TaskSyncState state, String statusCode, String businessCode, Date executionTime) {
        processing.update(state, statusCode, executionTime);
        business.setStatusCode(businessCode);
        setModifyTime(executionTime);
    }

    public void checkNext(Task nextTask) {
        if (!this.channelNo.equals(nextTask.channelNo)) {
            throw new BusinessException("", "当前任务与下一个任务的 channelNo 不一致，当前任务id：" + this.id + " 下一个任务id：" + nextTask.id);
        }

        if (!this.business.getType().equals(nextTask.business.getType())) {
            throw new BusinessException("", "当前任务与下一个任务的 business.type 不一致，当前任务id：" + this.id + " 下一个任务id：" + nextTask.id);
        }

        if (!this.business.getTransactionNo().equals(nextTask.business.getTransactionNo())) {
            throw new BusinessException("", "当前任务与下一个任务的 business.transactionNo 不一致，当前任务id：" + this.id + " 下一个任务id：" + nextTask.id);
        }
    }

    /**
     * 判断当前任务是否已过期
     *
     * @param latestSuccessTaskId 已成功的任务 id
     * @return
     */
    public boolean isExpired(Long latestSuccessTaskId) {
        if (latestSuccessTaskId == null) {
            return false;
        }

        return latestSuccessTaskId > getId();
    }

    /**
     * 判断当前任务是否已过期
     *
     * @param previousTask 上一个任务
     * @return
     */
    public boolean isExpired(Task previousTask) {
        if (previousTask == null) {
            return false;
        }

        return previousTask.getId() > getId();
    }

    public boolean isDispatchSuccess() {
        return processing.isDispatchSuccess();
    }

    public boolean isExecuting() {
        return processing.isExecuting();
    }

    public boolean isExecuteException() {
        return processing.isExecuteException();
    }

    public boolean isExecuteSuccess() {
        return processing.getState().isExecuteSuccess();
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
        sb.append("\"id\":").append(id);
        sb.append(",\"parentId\":").append(parentId);
        sb.append(",\"nextId\":").append(nextId);
        sb.append(",\"no\":\"").append(no).append('\"');
        sb.append(",\"commitNo\":\"").append(commitNo).append('\"');
        sb.append(",\"channelNo\":\"").append(channelNo).append('\"');
        sb.append(",\"business\":");
        business.toString(sb);
        sb.append(",\"sync\":");
        processing.toString(sb);
        sb.append(",\"createTime\":").append(createTime.getTime());
        sb.append(",\"updateTime\":").append(modifyTime.getTime());
        sb.append(",\"$version\":\"").append(version).append('\"');
        sb.append('}');
    }

    @Override
    public Task getTask() {
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        if (id != null) {
            this.id = id;
        }
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        if (parentId != null) {
            this.parentId = parentId;
        }
    }

    public Long getNextId() {
        return nextId;
    }

    public void setNextId(Long nextId) {
        if (nextId != null) {
            this.nextId = nextId;
        }
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        if (no != null) {
            this.no = no;
        }
    }

    public String getCommitNo() {
        return commitNo;
    }

    public void setCommitNo(String commitNo) {
        if (commitNo != null) {
            this.commitNo = commitNo;
        }
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        if (channelNo != null) {
            this.channelNo = channelNo;
        }
    }

    public TaskBusiness getBusiness() {
        return business;
    }

    public void setBusiness(TaskBusiness business) {
        if (business != null) {
            this.business = business;
        }
    }

    public TaskProcessing getProcessing() {
        return processing;
    }

    public void setProcessing(TaskProcessing processing) {
        if (processing != null) {
            this.processing = processing;
        }
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            this.createTime = createTime;
        }
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            this.modifyTime = modifyTime;
        }
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        if (version != null) {
            this.version = version;
        }
    }

}
