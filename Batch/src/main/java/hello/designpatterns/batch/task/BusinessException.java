package hello.designpatterns.batch.task;

public class BusinessException extends RuntimeException {

    private String code;

    public BusinessException() {
        super();
    }

    public BusinessException(String code) {
        super();
        setCode(code);
    }

    public BusinessException(String code, String message) {
        super(message);
        setCode(code);
    }

    public BusinessException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    public BusinessException(String code, Throwable cause) {
        super(cause);
        setCode(code);
    }

    protected BusinessException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code != null) {
            this.code = code;
        }
    }
}
