package hello.designpatterns.quota.queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class QuotaQueue1Test {

    @Test
    public void test1() throws InterruptedException {
        QuotaQueue1<String> quotaQueue = new QuotaQueue1("abc");

        Queue<String> queue1 = quotaQueue.addQuota("a", 1, new LinkedBlockingDeque<>());
        Queue<String> queue2 = quotaQueue.addQuota("b", 2, new LinkedBlockingDeque<>());
        Queue<String> queue3 = quotaQueue.addQuota("c", 1, new LinkedBlockingDeque<>());
        Queue<String> queue4 = quotaQueue.addQuota("d", 3, new LinkedBlockingDeque<>());

        queue1.add("a-quota1-0");
        queue1.add("a-quota1-1");
        queue1.add("a-quota1-2");
        queue1.add("a-quota1-3");
        queue1.add("a-quota1-4");
        queue1.add("a-quota1-5");
        queue1.add("a-quota1-6");
        queue1.add("a-quota1-7");
        queue1.add("a-quota1-8");
        queue1.add("a-quota1-9");

        queue2.add("b-quota2-0");
        queue2.add("b-quota2-1");
        queue2.add("b-quota2-2");
        queue2.add("b-quota2-3");
        queue2.add("b-quota2-4");
        queue2.add("b-quota2-5");
        queue2.add("b-quota2-6");
        queue2.add("b-quota2-7");
        queue2.add("b-quota2-8");
        queue2.add("b-quota2-9");

        queue3.add("c-quota1-0");
        queue3.add("c-quota1-1");
        queue3.add("c-quota1-2");
        queue3.add("c-quota1-3");
        queue3.add("c-quota1-4");
        queue3.add("c-quota1-5");
        queue3.add("c-quota1-6");
        queue3.add("c-quota1-7");
        queue3.add("c-quota1-8");
        queue3.add("c-quota1-9");

        queue4.add("d-quota3-0");
        queue4.add("d-quota3-1");
        queue4.add("d-quota3-2");
        queue4.add("d-quota3-3");
        queue4.add("d-quota3-4");
        queue4.add("d-quota3-5");
        queue4.add("d-quota3-6");
        queue4.add("d-quota3-7");
        queue4.add("d-quota3-8");
        queue4.add("d-quota3-9");

        quotaQueue.run();

        while (true) {
            String poll = quotaQueue.pollTask();

            if (poll == null) {
                break;
            }

            System.out.println(poll);
        }
    }

    @Test
    public void test2() throws InterruptedException {
        QuotaQueue1<String> quotaQueue = new QuotaQueue1("abc");

        Queue<String> queue1 = quotaQueue.addQuota("a", 1, new LinkedBlockingDeque<>());
        Queue<String> queue2 = quotaQueue.addQuota("b", 2, new LinkedBlockingDeque<>());
        Queue<String> queue3 = quotaQueue.addQuota("c", 1, new LinkedBlockingDeque<>());
        Queue<String> queue4 = quotaQueue.addQuota("d", 3, new LinkedBlockingDeque<>());

        quotaQueue.put("a", "a-quota1-0");
        quotaQueue.put("a", "a-quota1-1");
        quotaQueue.put("a", "a-quota1-2");
        quotaQueue.put("a", "a-quota1-3");
        quotaQueue.put("a", "a-quota1-4");
        quotaQueue.put("a", "a-quota1-5");
        quotaQueue.put("a", "a-quota1-6");
        quotaQueue.put("a", "a-quota1-7");
        quotaQueue.put("a", "a-quota1-8");
        quotaQueue.put("a", "a-quota1-9");

        quotaQueue.put("b", "b-quota2-0");
        quotaQueue.put("b", "b-quota2-1");
        quotaQueue.put("b", "b-quota2-2");
        quotaQueue.put("b", "b-quota2-3");
        quotaQueue.put("b", "b-quota2-4");
        quotaQueue.put("b", "b-quota2-5");
        quotaQueue.put("b", "b-quota2-6");
        quotaQueue.put("b", "b-quota2-7");
        quotaQueue.put("b", "b-quota2-8");
        quotaQueue.put("b", "b-quota2-9");

        quotaQueue.put("c", "c-quota1-0");
        quotaQueue.put("c", "c-quota1-1");
        quotaQueue.put("c", "c-quota1-2");
        quotaQueue.put("c", "c-quota1-3");
        quotaQueue.put("c", "c-quota1-4");
        quotaQueue.put("c", "c-quota1-5");
        quotaQueue.put("c", "c-quota1-6");
        quotaQueue.put("c", "c-quota1-7");
        quotaQueue.put("c", "c-quota1-8");
        quotaQueue.put("c", "c-quota1-9");

        quotaQueue.put("d", "d-quota3-0");
        quotaQueue.put("d", "d-quota3-1");
        quotaQueue.put("d", "d-quota3-2");
        quotaQueue.put("d", "d-quota3-3");
        quotaQueue.put("d", "d-quota3-4");
        quotaQueue.put("d", "d-quota3-5");
        quotaQueue.put("d", "d-quota3-6");
        quotaQueue.put("d", "d-quota3-7");
        quotaQueue.put("d", "d-quota3-8");
        quotaQueue.put("d", "d-quota3-9");


        while (true) {
            String poll = quotaQueue.pollTask(1000, TimeUnit.MILLISECONDS);

            if (poll == null) {
                break;
            }

            System.out.println(poll);
        }

        Thread.sleep(300000);
    }
}
