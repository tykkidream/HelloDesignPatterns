package hellodesignpatterns.reentrant;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public abstract class ReentrantHolder<T> {
    private static final Logger logger = LoggerFactory.getLogger(ReentrantHolder.class);

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

    private boolean boundThread = false;

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
        try {
            if (boundThread) {
                return;
            }

            boundThread = true;

            parent = reentrantHolder.get();

            reentrantHolder.set(this);

            T parentData = null;

            if (parent != null) {
                reentrantLayer = parent.reentrantLayer + 1;
                parentData = parent.getData();
            }

            reentrantNo = counter.get().incrementAndGet();

            data = reentrantService.bindThread(reentrantLayer, reentrantNo, parentData);
        } catch (Throwable throwable) {
            if (logger.isErrorEnabled()) {
                logger.error(throwable.getMessage(), throwable);
            }

            enforceRestoreThread();
        }
    }

    public void restoreThread() {
        try {
            if (!boundThread) {
                return;
            }

            reentrantHolder.set(parent);

            T parentData = null;

            if (parent != null) {
                parentData = parent.getData();
            }

            reentrantService.restoreThread(reentrantLayer, reentrantNo, data, parentData);

            data = null;
            parent = null;
            boundThread = false;
        } catch (Throwable throwable) {
            if (logger.isErrorEnabled()) {
                logger.error(throwable.getMessage(), throwable);
            }

            enforceRestoreThread();
        }
    }

    private void enforceRestoreThread() {
        if (!boundThread) {
            return;
        }

        reentrantHolder.set(parent);
        data = null;
        parent = null;
        boundThread = false;
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
