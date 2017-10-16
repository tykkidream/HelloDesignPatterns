package hello.designpatterns.timewheel;

import hello.designpatterns.timewheel.feature.HierarchicalTimeWheel;
import hello.designpatterns.timewheel.feature.MillisecondTimeWheel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Saber on 2017/4/15.
 */
public class HieracrhicalTimeWheelTest {

    TimeScheduler scheduler = new TimeScheduler() {
        @Override
        public void schedule(Runnable runnable) {

        }
    };

    TimeExpiredHandler handler = new TimeExpiredHandler() {
        @Override
        public void handle(int timeIndex, List<TimeTask> timeTasks) {
            System.out.println("收到过期数据 : " + timeIndex);
            for (TimeTask timeTask : timeTasks){
                Object data = timeTask.getData();
                System.out.println("\t" + data);
            }
        }
    };

    @Test
    public void test() throws IOException {
        int[] sizes = {6, 6}; // 60秒，60分。
        long tickTime = 1000L; // 1秒是1000毫秒。

        HierarchicalTimeWheel hierarchicalTimeWheel = new HierarchicalTimeWheel(sizes, tickTime);

        TimeWheel timeWheel = new MillisecondTimeWheel(hierarchicalTimeWheel);

        // timeWheel.setScheduler(scheduler);
        timeWheel.setTimeExpiredHandler(handler);

        long expiredTime = System.currentTimeMillis() + 5000L;

        timeWheel.addTask(expiredTime, "aaafe12312sadvzxvcxvwer235");
        timeWheel.addTask(expiredTime, "11123123");
        timeWheel.addTask(expiredTime, "sdfgdsfgdsfg");
        timeWheel.addTask(expiredTime, "1 2 3 4 5 6 7 8 9");


        expiredTime = System.currentTimeMillis() + 8000L;
        timeWheel.addTask(expiredTime, "aaaaaaaaaaaaaaaaaaa");
        timeWheel.addTask(expiredTime, "dssssssssssssssss");
        timeWheel.addTask(expiredTime, "dfffffffffffffffffff");

        System.in.read();
    }
}
