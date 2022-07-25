package hello.designpatterns.quota.queue;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class QuotaQueueTest {

    @Test
    public void test1() throws InterruptedException {
        QuotaQueue<String> quotaQueue = new QuotaQueue();

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

        quotaQueue.temp();
        quotaQueue.run();

        while (true) {
            String poll = quotaQueue.poll();

            if (poll == null) {
                break;
            }

            System.out.println(poll);
        }
    }
}
