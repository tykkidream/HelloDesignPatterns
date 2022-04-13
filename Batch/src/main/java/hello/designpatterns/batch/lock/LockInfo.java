package hello.designpatterns.batch.lock;

public class LockInfo<T> {
    private String key;

    private String value;

    private Boolean locked = false;

    private T data;

    public LockInfo() {

    }

    public LockInfo(T data, String key, String value) {
        setData(data);
        setKey(key);
        setValue(value);
    }

    public String key() {
        return key;
    }

    public String value() {
        return value;
    }

    public Boolean locked() {
        return locked;
    }

    public T data() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        if (key != null) {
            this.key = key;
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value != null) {
            this.value = value;
        }
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        if (locked != null) {
            this.locked = locked;
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        if (data != null) {
            this.data = data;
        }
    }
}
