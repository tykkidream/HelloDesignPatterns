package hellodesignpatterns.reentrant;

import java.util.concurrent.Callable;

public abstract class ReentrantHolder<T> {
    private static final ThreadLocal<Counter> counter = new ThreadLocal<Counter>() {
        @Override
        protected Counter initialValue() {
            return new Counter();
        }
    };

    private final ThreadLocal<ReentrantHolder<T>> reentrantHolder;

    private ReentrantHolder<T> parent;

    private ReentrantService<T> reentrantService;

    private T data;

    private int reentrantLayer = 0;

    private int reentrantNo = 0;

    public ReentrantHolder(ThreadLocal<ReentrantHolder<T>> reentrantHolder, ReentrantService<T> reentrantService) {
        this.reentrantHolder = reentrantHolder;
        this.reentrantService = reentrantService;
    }

    public ReentrantHolder<T> currentReentrantHolder() {
        return reentrantHolder.get();
    }

    public void process(Runnable runnable) {
        bindThread();

        try {
            runnable.run();
        } catch (Throwable throwable) {
            ExceptionConvertUnchecked.throwException(throwable);
        } finally {
            restoreThread();
        }
    }

    public <R> R process(Callable<R> callable) {
        bindThread();

        try {
            return callable.call();
        } catch (Throwable throwable) {
            return ExceptionConvertUnchecked.throwException(throwable);
        } finally {
            restoreThread();
        }
    }

    public void bindThread() {
        parent = reentrantHolder.get();

        reentrantHolder.set(this);

        T parentData = null;

        if (parent != null) {
            reentrantLayer = parent.reentrantLayer + 1;
            parentData = parent.getData();
        }

        reentrantNo = counter.get().incrementAndGet();

        data = reentrantService.bindThread(reentrantLayer, reentrantNo, parentData);
    }

    public void restoreThread() {
        reentrantHolder.set(parent);

        T parentData = null;

        if (parent != null) {
            parentData = parent.getData();
        }

        reentrantService.restoreThread(reentrantLayer, reentrantNo, data, parentData);

        data = null;
    }

    /* •••••••••••••••••••••••••••••••••••••••装••订••线••内••禁••止••作••答••否••则••记••零••分••••••••••••••••••••••••••••••••••••••• */

    public ReentrantHolder<T> getParent() {
        return parent;
    }

    public void setParent(ReentrantHolder<T> parent) {
        if (parent != null) {
            this.parent = parent;
        }
    }

    public ReentrantService<T> getReentrantService() {
        return reentrantService;
    }

    public void setReentrantService(ReentrantService<T> reentrantService) {
        if (reentrantService != null) {
            this.reentrantService = reentrantService;
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
