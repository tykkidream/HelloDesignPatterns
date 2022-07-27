package hello.designpatterns.quota.queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class QuotaQueue2<T> {
    private static <T extends Exception, R> R throwException(Throwable t) throws T {
        throw (T) t;
    }

    private List<QuotaRule> quotaRuleQueue;

    /**
     * 所有业务的任务，在根据配额规则计算后，编排进入此队列。
     */
    private BlockingQueue<T> taskQueue;

    private Map<String, QuotaRule> quotaRules;

    private AtomicBoolean signal = new AtomicBoolean();

    private Thread quotaShapingThread;

    public QuotaQueue2(String name, int size) {
        quotaRuleQueue = new LinkedList<>();

        taskQueue = new ArrayBlockingQueue<>(size);

        quotaRules = new HashMap<>();

        quotaShapingThread = new Thread(new QuotaShaping(), "QuotaQueue-Thread-" + name);

        quotaShapingThread.start();
    }

    private class QuotaShaping implements Runnable {

        @Override
        public void run() {
            while (!quotaShapingThread.isInterrupted()) {
                System.out.println("thread run 中");

                LockSupport.parkNanos(10000000000L);

                boolean hasNew = signal.getAndSet(false);

                if (hasNew) {
                    try {
                        QuotaQueue2.this.run();
                    } catch (Throwable throwable) {
                        throwable.printStackTrace();
                    }
                } else {

                }
            }
        }
    }

    public synchronized Queue<T> addQuota(String name, int quota) {
        return addQuota(name, quota, null);
    }

    public synchronized Queue<T> addQuota(String name, int quota, BlockingQueue<T> queue) {
        if (quotaRules.containsKey(name)) {
            return null;
        }

        if (queue == null) {
            queue = new ArrayBlockingQueue<>(quota);
        }

        QuotaRule quotaRule = new QuotaRule();
        quotaRule.name = name;
        quotaRule.quota = quota;
        quotaRule.queue = queue;

        quotaRules.put(name, quotaRule);
        quotaRuleQueue.add(quotaRule);

        if (quotaRule.queue.size() > 0) {
            signal.set(true);
            LockSupport.unpark(quotaShapingThread);
        }

        return queue;
    }

    public void put(String name, T element) {
        try {
            QuotaRule quotaRule = quotaRules.get(name);

            // 阻塞
            quotaRule.queue.put(element);

            signal.set(true);

            LockSupport.unpark(quotaShapingThread);
        } catch (Throwable throwable) {
            throwException(throwable);
        }
    }

    public void run() throws InterruptedException {
        while (true) {
            boolean hasData = false;

            for (QuotaRule quotaRule : quotaRuleQueue) {
                if (quotaRule == null) {
                    continue;
                }

                if (quotaRule.queue.isEmpty()) {
                    continue;
                }

                for (int i = 0; i < quotaRule.quota; i++) {
                    T task = quotaRule.queue.poll();

                    if (task == null) {
                        break;
                    }

                    // 阻塞
                    taskQueue.put(task);
                }

                hasData = true;
            }

            if (!hasData) {
                break;
            }
        }
    }

    public T pollTask() {
        return taskQueue.poll();
    }

    public T pollTask(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return taskQueue.poll(timeout, timeUnit);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();

        if (quotaShapingThread != null) {
            quotaShapingThread.interrupt();
            quotaShapingThread = null;
        }
    }

    private class QuotaRule {
        private String name;

        private int quota;

        private BlockingQueue<T> queue;
    }

}
