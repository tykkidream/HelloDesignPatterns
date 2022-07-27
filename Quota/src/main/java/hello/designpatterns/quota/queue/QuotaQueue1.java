package hello.designpatterns.quota.queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 未完成，有并发问题。
 * @param <T>
 */
public class QuotaQueue1<T> {
    private static final long FIRST_TIME_OUT = 60 * 1000;

    private static <T extends Exception, R> R throwException(Throwable t) throws T {
        throw (T) t;
    }


    private BlockingQueue<QuotaRule> taskQueues;

    private List<QuotaRule> taskQueueRounds;

    /**
     * 所有业务的任务，在根据配额规则计算后，编排进入此队列。
     */
    private BlockingQueue<T> taskQueue;

    private Map<String, QuotaRule> quotaRules;

    private Thread quotaShapingThread;

    public QuotaQueue1(String name, int size) {
        taskQueues = new LinkedBlockingDeque<>();

        taskQueueRounds = new LinkedList<>();

        taskQueue = new ArrayBlockingQueue<>(size);

        quotaRules = new HashMap<>();

        quotaShapingThread = new Thread(new QuotaShaping(), "QuotaQueue-Thread-" + name);

        quotaShapingThread.start();
    }

    private class QuotaShaping implements Runnable {

        @Override
        public void run() {
            while (!quotaShapingThread.isInterrupted()) {
                try {
                    //System.out.println("run 中");
                    QuotaQueue1.this.run();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
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

        return queue;
    }

    /**
     * 与 {@link #run()} 接口有并发问题。
     * @param name
     * @param element
     * @throws InterruptedException
     */
    public void put(String name, T element) throws InterruptedException {
        QuotaRule quotaRule = quotaRules.get(name);

        int in = quotaRule.in.incrementAndGet();

        quotaRule.queue.put(element);

        if (in > 1) {
            return;
        }

        synchronized (quotaRule) {
            taskQueues.add(quotaRule);
        }
    }

    /**
     * 与 {@link #put(String, Object)} 接口有并发问题。
     */
    public void run() {
        while (poll(FIRST_TIME_OUT, TimeUnit.SECONDS)) {
            while (poll(0, null)) {}

            if (taskQueueRounds.size() > 0) {
                Iterator<QuotaRule> iterator = taskQueueRounds.iterator();

                while (iterator.hasNext()) {
                    QuotaRule quotaRule = iterator.next();
                    taskQueues.add(quotaRule);
                    iterator.remove();
                }
            } else {
                break;
            }
        }
    }

    private boolean poll(long timeout, TimeUnit unit) {
        try {
            QuotaRule quotaRule = null;

            if (timeout <= 0) {
                //System.out.println("poll 中");
                quotaRule = taskQueues.poll();
            } else {
                //System.out.println("poll timeout 中");
                quotaRule = taskQueues.poll(timeout, unit);
            }

            //System.out.println("poll 处理 中");

            if (quotaRule == null) {
                //System.out.println("poll false of null");
                return false;
            }

            int in = quotaRule.in.get();

            if (in == 0) {
                //System.out.println("poll false of 0");
                return false;
            }

            if (quotaRule.queue.isEmpty()) {
                if (quotaRule.in.compareAndSet(in, 0)) {
                    //System.out.println("poll false of empty");
                    return false;
                }
            }

            for (int i = 0; i < quotaRule.quota; i++) {
                T task = quotaRule.queue.poll();

                if (task == null) {
                    break;
                }

                taskQueue.put(task);
            }

            // 此时，quotaRule.quota 只可能有 0 个数据，也要将其添加到 taskQueueRounds 中一次。
            // 下一次执行本接口 poll 时，将对本轮所有 taskQueues 中还未被处理的下一个或下 N 个 quotaRule 进行处理，
            // 如果不将当前 quotaRule 添加到 taskQueueRounds 中，当 quotaRule.quota 突然有了新数据时，
            // 可能导致当前 quotaRule 插队到本轮 taskQueues 后续还未被处理 quotaRule 之前，多执行处理一次，变得不公平。
            taskQueueRounds.add(quotaRule);

            quotaRule.in.set(2);

            return true;
        } catch (InterruptedException e) {
            throwException(e);
            return false;
        }
    }

    public T pollTask() {
        return taskQueue.poll();
    }

    public T pollTask(long timeout, TimeUnit timeUnit) throws InterruptedException {
        return taskQueue.poll(timeout, timeUnit);
    }

    private class QuotaRule {
        private String name;

        private int quota;

        private BlockingQueue<T> queue;

        private AtomicInteger in = new AtomicInteger(0);

        private AtomicInteger inPrevious = new AtomicInteger(0);
    }

}
