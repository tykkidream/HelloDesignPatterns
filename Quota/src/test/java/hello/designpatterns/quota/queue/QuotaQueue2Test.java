package hello.designpatterns.quota.queue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
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


    @Test
    public void test4() throws InterruptedException {
        QuotaQueue2<String> quotaQueue = new QuotaQueue2("abc", 1000);

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

        Test4Runnable runnable1 = new Test4Runnable(quotaQueue, 5000 , "a", 1);
        Test4Runnable runnable2 = new Test4Runnable(quotaQueue, 5000 , "b", 1);
        Test4Runnable runnable3 = new Test4Runnable(quotaQueue, 5000 , "c", 1);
        Test4Runnable runnable4 = new Test4Runnable(quotaQueue, 5000 , "d", 1);
        Test4Runnable runnable5 = new Test4Runnable(quotaQueue, 5000 , "e", 1);
        Test4Runnable runnable6 = new Test4Runnable(quotaQueue, 5000 , "f", 1);
        Test4Runnable runnable7 = new Test4Runnable(quotaQueue, 5000 , "g", 1);
        Test4Runnable runnable8 = new Test4Runnable(quotaQueue, 5000 , "h", 1);
        Test4Runnable runnable9 = new Test4Runnable(quotaQueue, 5000 , "i", 1);
        Test4Runnable runnable10 = new Test4Runnable(quotaQueue, 5000 , "j", 1);
        Test4Runnable runnable11 = new Test4Runnable(quotaQueue, 5000 , "k", 1);
        Test4Runnable runnable12 = new Test4Runnable(quotaQueue, 5000 , "l", 1);

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

                for (String d : data) {
                    System.out.println(d);
                }
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


    private static class Test4Runnable implements Runnable {
        private QuotaQueue2<String> quotaQueue;

        private String name;

        private List<String> data = new LinkedList<>();
        private Test4Runnable(QuotaQueue2<String> quotaQueue, int num, String name, int quota) {
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
