package hello.designpatterns.quota.queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class QuotaQueue<T> {

    private BlockingQueue<QuotaRule> taskQueues;

    private List<QuotaRule> taskQueueRounds;

    /**
     * 所有业务的任务，在根据配额规则计算后，编排进入此队列。
     */
    private BlockingQueue<T> taskQuotaQueue;

    private Map<String, QuotaRule> quotaRules;

    public QuotaQueue() {
        taskQueues = new LinkedBlockingDeque<>();

        taskQueueRounds = new LinkedList<>();

        taskQuotaQueue = new LinkedBlockingDeque<>();

        quotaRules = new HashMap<>();
    }

    public synchronized Queue<T> addQuota(String name, int quota) {
        return addQuota(name, quota, null);
    }

    public synchronized Queue<T> addQuota(String name, int quota, Queue<T> queue) {
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

    public void temp() {
        for (Map.Entry<String, QuotaRule> entry : quotaRules.entrySet()) {
            taskQueues.add(entry.getValue());
        }
    }


    public void run() throws InterruptedException {
        while (poll(1, TimeUnit.SECONDS)) {
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

    private boolean poll(long timeout, TimeUnit unit) throws InterruptedException {
        QuotaRule quotaRule;

        if (timeout <= 0) {
            quotaRule = taskQueues.poll();
        } else {
            quotaRule = taskQueues.poll(timeout, unit);
        }

        if (quotaRule == null || quotaRule.queue.isEmpty()) {
            return false;
        }

        for (int i = 0; i < quotaRule.quota; i++) {
            T task = quotaRule.queue.poll();

            if (task != null) {
                taskQuotaQueue.put(task);
            }
        }

        taskQueueRounds.add(quotaRule);

        return true;
    }

    public T poll() {
        return taskQuotaQueue.poll();
    }

    private class QuotaRule {
        private String name;

        private int quota;

        private Queue<T> queue;
    }

}
