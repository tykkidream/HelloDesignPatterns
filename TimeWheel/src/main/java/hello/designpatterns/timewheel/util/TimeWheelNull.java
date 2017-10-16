package hello.designpatterns.timewheel.util;

import hello.designpatterns.timewheel.TimeExpiredHandler;
import hello.designpatterns.timewheel.TimeScheduler;
import hello.designpatterns.timewheel.TimeTask;
import hello.designpatterns.timewheel.TimeWheel;

/**
 * Created by Saber on 2017/4/16.
 */
final class TimeWheelNull implements TimeWheel {
    @Override
    public void goToNextTime() {

    }

    @Override
    public Integer addTask(long expiryTime, Object data) {
        return null;
    }

    @Override
    public Integer addTask(TimeTask timeTask) {
        return null;
    }

    @Override
    public TimeExpiredHandler getTimeExpiredHandler() {
        return null;
    }

    @Override
    public void setTimeExpiredHandler(TimeExpiredHandler timeExpiredHandler) {

    }

    @Override
    public TimeScheduler getScheduler() {
        return null;
    }

    @Override
    public void setScheduler(TimeScheduler scheduler) {

    }

    @Override
    public long getTickTime() {
        return 0;
    }

    @Override
    public long getCycleTime() {
        return 0;
    }

    @Override
    public int getClockDial() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public boolean isLastClockDial() {
        return false;
    }
}
