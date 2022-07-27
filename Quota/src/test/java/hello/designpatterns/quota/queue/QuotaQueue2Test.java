package hello.designpatterns.quota.queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class QuotaQueue2Test {
    @Test
    public void test1() throws InterruptedException {
        QuotaQueue2<String> quotaQueue = new QuotaQueue2("abc", 20);

        Thread.sleep(300000);
    }

    @Test
    public void test2() throws InterruptedException {
        QuotaQueue2<String> quotaQueue = new QuotaQueue2("abc", 20);

        Queue<String> queue1 = quotaQueue.addQuota("a", 1, new LinkedBlockingDeque<>());
        Queue<String> queue2 = quotaQueue.addQuota("b", 2, new LinkedBlockingDeque<>());
        Queue<String> queue3 = quotaQueue.addQuota("c", 1, new LinkedBlockingDeque<>());
        Queue<String> queue4 = quotaQueue.addQuota("d", 3, new LinkedBlockingDeque<>());

        System.out.println("=============================================");

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
            String poll = quotaQueue.pollTask(1000L, TimeUnit.MILLISECONDS);

            if (poll == null) {
                break;
            }

            System.out.println(poll);
        }

        Thread.sleep(300000);

    }

    @Test
    public void test3() throws InterruptedException {

        {
            test3sub();
        }

        System.gc();
        System.gc();

        System.out.println("==========================");

        Thread.sleep(10000);

        System.gc();
        System.gc();

        System.out.println("==========================");

        Thread.sleep(300000);
    }

    private void test3sub() throws InterruptedException {
        QuotaQueue2<String> quotaQueue = new QuotaQueue2("abc", 20);
        Thread.sleep(10000);
    }
}
