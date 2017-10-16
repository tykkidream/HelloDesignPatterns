package hello.designpatterns.timewheel.feature;


import hello.designpatterns.timewheel.TimeExpiredHandler;
import hello.designpatterns.timewheel.TimeScheduler;
import hello.designpatterns.timewheel.TimeTask;
import hello.designpatterns.timewheel.TimeWheel;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by Saber on 2017/4/15.
 */
public class MillisecondTimeWheel extends AbstractTimeWheel {
    private ScheduledExecutorService executor = null;

    private Runnable runnable = null;

    private TimeWheel timeWheel = null;

    public MillisecondTimeWheel(TimeWheel timeWheel) {
        this.timeWheel = timeWheel;

        this.runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("当前时间 " + System.currentTimeMillis());
                goToNextTime();
            }
        };

        this.executor = Executors.newSingleThreadScheduledExecutor();
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间
        this.executor.scheduleAtFixedRate(runnable, 100, timeWheel.getTickTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public void goToNextTime() {
        timeWheel.goToNextTime();
    }

    @Override
    public void setScheduler(TimeScheduler scheduler) {
        timeWheel.setScheduler(scheduler);
    }

    @Override
    public void setTimeExpiredHandler(TimeExpiredHandler timeExpiredHandler) {
        timeWheel.setTimeExpiredHandler(timeExpiredHandler);
    }
    @Override
    public Integer addTask(long expiryTime, Object data) {
        return timeWheel.addTask(expiryTime, data);
    }

    @Override
    public Integer addTask(TimeTask timeTask) {
        return timeWheel.addTask(timeTask);
    }

    @Override
    public long getTickTime() {
        return timeWheel.getTickTime();
    }

    @Override
    public int getSize() {
        return timeWheel.getSize();
    }

    @Override
    public boolean isLastClockDial() {
        return timeWheel.isLastClockDial();
    }


}
