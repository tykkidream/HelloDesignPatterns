package hello.designpatterns.batch.task;

public class SynchronizationException extends RuntimeException {

    private String code;

    public SynchronizationException() {
        super();
    }

    public SynchronizationException(String code) {
        super();
        setCode(code);
    }

    public SynchronizationException(String code, String message) {
        super(message);
        setCode(code);
    }

    public SynchronizationException(String code, String message, Throwable cause) {
        super(message, cause);
        setCode(code);
    }

    public SynchronizationException(String code, Throwable cause) {
        super(cause);
        setCode(code);
    }

    protected SynchronizationException(String code, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
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
