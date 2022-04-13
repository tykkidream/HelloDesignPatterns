package hello.designpatterns.batch.task;

public class TaskBusiness {

    /**
     * 业务类型
     */
    private String type;

    /**
     * 事务编码
     *
     * 可重复，任务将会按事务编码排序，同一事务编码的任务只能同时执行一个。
     */
    private String transactionNo;

    /**
     * 参数数据
     */
    private String param;

    /**
     * 结果数据
     */
    private String result;

    /**
     * 状态码
     */
    private String statusCode;

    private String attribute1;

    private String attribute2;

    private String attribute3;

    public TaskBusiness() {
        setType(NullValue.EmptyString);
        setTransactionNo(NullValue.EmptyString);
        setStatusCode(NullValue.EmptyString);
        setParam(NullValue.EmptyString);
        setResult(NullValue.EmptyString);
        setAttribute1(NullValue.EmptyString);
        setAttribute2(NullValue.EmptyString);
        setAttribute3(NullValue.EmptyString);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    protected void toString(StringBuilder sb) {
        sb.append("{");
        sb.append("\"type\":\"").append(type).append('\"');
        sb.append(",\"transactionNo\":\"").append(transactionNo).append('\"');
        sb.append(",\"param\":\"").append(param).append('\"');
        sb.append(",\"result\":\"").append(result).append('\"');
        sb.append(",\"statusCode\":\"").append(statusCode).append('\"');
        sb.append(",\"attribute1\":\"").append(attribute1).append('\"');
        sb.append(",\"attribute2\":\"").append(attribute2).append('\"');
        sb.append(",\"attribute3\":\"").append(attribute3).append('\"');
        sb.append('}');
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type != null) {
            this.type = type;
        }
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        if (transactionNo != null) {
            this.transactionNo = transactionNo;
        }
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        if (param != null) {
            this.param = param;
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        if (result != null) {
            this.result = result;
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

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        if (attribute1 != null) {
            this.attribute1 = attribute1;
        }
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        if (attribute2 != null) {
            this.attribute2 = attribute2;
        }
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        if (attribute3 != null) {
            this.attribute3 = attribute3;
        }
    }
}
