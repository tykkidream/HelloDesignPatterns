package hellodesignpatterns.reentrant;

import java.util.concurrent.Callable;

public abstract class ReentrantHolder<T> {
    private static final ThreadLocal<Counter> counter = new ThreadLocal<Counter>() {
        @Override
        protected Counter initialValue() {
            return new Counter();
        }
    };

    private final ThreadLocal<ReentrantHolder> reentrantHolder;

    private ReentrantHolder oldReentrantHolder;

    private ReentrantService<T> reentrantService;

    private T data;

    private int reentrantLayer = 0;

    private int reentrantNo = 0;

    public ReentrantHolder(ThreadLocal<ReentrantHolder> reentrantHolder, ReentrantService reentrantService) {
        this.reentrantHolder = reentrantHolder;
        this.reentrantService = reentrantService;
    }

    public ReentrantHolder currentReentrantHolder() {
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

    public <T> T process(Callable<T> callable) {
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
        oldReentrantHolder = reentrantHolder.get();

        reentrantHolder.set(this);

        if (oldReentrantHolder != null) {
            reentrantLayer = oldReentrantHolder.reentrantLayer + 1;
        }

        reentrantNo = counter.get().incrementAndGet();

        data = reentrantService.bindThread(reentrantLayer, reentrantNo);
    }

    public void restoreThread() {
        reentrantHolder.set(oldReentrantHolder);

        if (oldReentrantHolder != null) {
            reentrantLayer = oldReentrantHolder.reentrantLayer - 1;
        }

        reentrantService.restoreThread(data);

        data = null;
    }

    /* •••••••••••••••••••••••••••••••••••••••装••订••线••内••禁••止••作••答••否••则••记••零••分••••••••••••••••••••••••••••••••••••••• */

    public ReentrantHolder getOldReentrantHolder() {
        return oldReentrantHolder;
    }

    public void setOldReentrantHolder(ReentrantHolder oldReentrantHolder) {
        if (oldReentrantHolder != null) {
            this.oldReentrantHolder = oldReentrantHolder;
        }
    }

    public ReentrantService getReentrantService() {
        return reentrantService;
    }

    public void setReentrantService(ReentrantService reentrantService) {
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
