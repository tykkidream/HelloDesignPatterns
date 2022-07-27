package hello.designpatterns.quota.queue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class QuotaQueue1Test {

    @Test
    public void test1() throws InterruptedException {
        QuotaQueue1<String> quotaQueue = new QuotaQueue1("abc",  100);

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
        QuotaQueue1<String> quotaQueue = new QuotaQueue1("abc", 1000);

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

    @Test
    public void test3() throws InterruptedException {
        QuotaQueue1<String> quotaQueue = new QuotaQueue1("abc", 100);

        Queue<String> queue1 = quotaQueue.addQuota("a", 1, new LinkedBlockingDeque<>());
        Queue<String> queue2 = quotaQueue.addQuota("b", 1, new LinkedBlockingDeque<>());
        Queue<String> queue3 = quotaQueue.addQuota("c", 1, new LinkedBlockingDeque<>());
        Queue<String> queue4 = quotaQueue.addQuota("d", 1, new LinkedBlockingDeque<>());
        Queue<String> queue5 = quotaQueue.addQuota("e", 1, new LinkedBlockingDeque<>());
        Queue<String> queue6 = quotaQueue.addQuota("f", 1, new LinkedBlockingDeque<>());
        Queue<String> queue7 = quotaQueue.addQuota("g", 1, new LinkedBlockingDeque<>());
        Queue<String> queue8 = quotaQueue.addQuota("h", 1, new LinkedBlockingDeque<>());
        Queue<String> queue9 = quotaQueue.addQuota("i", 1, new LinkedBlockingDeque<>());
        Queue<String> queue10 = quotaQueue.addQuota("j", 1, new LinkedBlockingDeque<>());
        Queue<String> queue11 = quotaQueue.addQuota("k", 1, new LinkedBlockingDeque<>());
        Queue<String> queue12 = quotaQueue.addQuota("l", 1, new LinkedBlockingDeque<>());

        Test3Runnable runnable1 = new Test3Runnable(quotaQueue, 5000 , "a", 1);
        Test3Runnable runnable2 = new Test3Runnable(quotaQueue, 5000 , "b", 1);
        Test3Runnable runnable3 = new Test3Runnable(quotaQueue, 5000 , "c", 1);
        Test3Runnable runnable4 = new Test3Runnable(quotaQueue, 5000 , "d", 1);
        Test3Runnable runnable5 = new Test3Runnable(quotaQueue, 5000 , "e", 1);
        Test3Runnable runnable6 = new Test3Runnable(quotaQueue, 5000 , "f", 1);
        Test3Runnable runnable7 = new Test3Runnable(quotaQueue, 5000 , "g", 1);
        Test3Runnable runnable8 = new Test3Runnable(quotaQueue, 5000 , "h", 1);
        Test3Runnable runnable9 = new Test3Runnable(quotaQueue, 5000 , "i", 1);
        Test3Runnable runnable10 = new Test3Runnable(quotaQueue, 5000 , "j", 1);
        Test3Runnable runnable11 = new Test3Runnable(quotaQueue, 5000 , "k", 1);
        Test3Runnable runnable12 = new Test3Runnable(quotaQueue, 5000 , "l", 1);

        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        Thread thread4 = new Thread(runnable4);
        Thread thread5 = new Thread(runnable5);
        Thread thread6 = new Thread(runnable6);
        Thread thread7 = new Thread(runnable7);
        Thread thread8 = new Thread(runnable8);
        Thread thread9 = new Thread(runnable9);
        Thread thread10 = new Thread(runnable10);
        Thread thread11 = new Thread(runnable11);
        Thread thread12 = new Thread(runnable12);

        Thread threadPrint = new Thread(new Runnable() {
            private int count;

            private long begin;

            private long end;

            @Override
            public void run() {
                List<String> data = new LinkedList<>();

                try {
                    while (true) {
                        String poll = quotaQueue.pollTask(1000, TimeUnit.MILLISECONDS);

                        if (poll == null) {
                            continue;
                        }

                        data.add(poll);

                        count++;

                        if (begin == 0) {
                            begin = System.currentTimeMillis();
                        }

                        if (count == 60000) {
                            end = System.currentTimeMillis();
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("结束： " + (end - begin));

                /*for (String d : data) {
                    System.out.println(d);
                }*/
            }
        });

        threadPrint.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        thread11.start();
        thread12.start();

        Thread.sleep(1 * 1000);
    }

    private static class Test3Runnable implements Runnable {
        private QuotaQueue1<String> quotaQueue;

        private String name;

        private List<String> data = new LinkedList<>();
        private Test3Runnable(QuotaQueue1<String> quotaQueue, int num, String name, int quota) {
            this.quotaQueue = quotaQueue;
            this.name = name;
            String pre = name + "-quota" + quota + "-";

            for (int i = 0; i < num; i++) {
                data.add(pre + i);
            }
        }

        @Override
        public void run() {
            for (String d : data) {
                try {
                    quotaQueue.put(name, d);
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        }
    }


}
