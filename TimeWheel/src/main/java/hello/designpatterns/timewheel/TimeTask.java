package hello.designpatterns.timewheel;

/**
 * Created by Saber on 2017/4/14.
 */
public class TimeTask {
    private long createTime;

    private long expiryTime;

    private Object data;

    private long restTime;

    public TimeTask(TimeTask timeTask, long restTime){
        this.createTime = timeTask.createTime;
        this.expiryTime = timeTask.expiryTime;
        this.data = timeTask.data;
        this.restTime = restTime;
    }

    public TimeTask(long createTime, long expiryTime, Object data, long restTime){
        this.createTime = createTime;
        this.expiryTime = expiryTime;
        this.data = data;
        this.restTime = restTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public Object getData() {
        return data;
    }

    public long getRestTime() {
        return restTime;
    }

    public void setRestTime(long restTime) {
        this.restTime = restTime;
    }
}
