package hello.designpatterns.timewheel.feature;


import hello.designpatterns.timewheel.TimeWheel;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Saber on 2017/4/13.
 */
public class DateTimeWheel {
    private ExecutorService pool = Executors.newCachedThreadPool();

    private TimeWheel yearTimeWheel;

    private TimeWheel monthTimeWheel;

    private TimeWheel dayTimeWheel;

    private TimeWheel hourTimeWheel;

    private TimeWheel minuteTimeWheel;

    private TimeWheel secondTimeWheel;

    private Timer timer;

    public DateTimeWheel(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                nextSecond();
            }
        }, 0L, 1000L);
    }


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            secondTimeWheel.goToNextTime();
        }
    };

    private void nextSecond(){
        pool.execute(runnable);
    }
}
