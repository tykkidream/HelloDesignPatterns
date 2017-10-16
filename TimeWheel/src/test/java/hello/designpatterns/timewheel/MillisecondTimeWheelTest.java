package hello.designpatterns.timewheel;

import hello.designpatterns.timewheel.feature.MillisecondTimeWheel;
import hello.designpatterns.timewheel.feature.SimpleTimeWheel;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Saber on 2017/4/15.
 */
public class MillisecondTimeWheelTest {

    TimeExpiredHandler handler = new TimeExpiredHandler() {
        @Override
        public void handle(int timeIndex, List<TimeTask> timeTasks) {
            for (TimeTask timeTask : timeTasks){
                Object data = timeTask.getData();
                System.out.println(data);
            }
        }
    };


    @Test
    public void test() throws IOException {
        int size = 10; // 60 秒。
        long tickTime = 1000L; // 1秒是1000毫秒。

        SimpleTimeWheel simpleTimeWheel = new SimpleTimeWheel(size, tickTime);

        TimeWheel timeWheel = new MillisecondTimeWheel(simpleTimeWheel);
        timeWheel.setTimeExpiredHandler(handler);

        long expiredTime = System.currentTimeMillis() + 5000L;

        timeWheel.addTask(expiredTime, "aaafe12312sadvzxvcxvwer235");
        timeWheel.addTask(expiredTime, "11123123");
        timeWheel.addTask(expiredTime, "sdfgdsfgdsfg");
        timeWheel.addTask(expiredTime, "1 2 3 4 5 6 7 8 9");

        System.in.read();
    }
}
