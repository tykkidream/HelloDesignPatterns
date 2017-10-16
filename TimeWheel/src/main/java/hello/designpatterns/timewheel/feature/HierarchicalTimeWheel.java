package hello.designpatterns.timewheel.feature;


import hello.designpatterns.timewheel.TimeExpiredHandler;
import hello.designpatterns.timewheel.TimeScheduler;
import hello.designpatterns.timewheel.TimeTask;
import hello.designpatterns.timewheel.TimeWheel;
import hello.designpatterns.timewheel.util.TimeWheelUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Saber on 2017/4/15.
 */
public class HierarchicalTimeWheel extends AbstractTimeWheel implements TimeExpiredHandler, TimeScheduler {

    private static class HierarchicalTimeWheelInner extends AbstractTimeWheel implements TimeExpiredHandler{
        private TimeWheel selfTimeWheel;

        private TimeWheel leftTimeWheel;

        private TimeWheel rightTimeWheel;

        public HierarchicalTimeWheelInner(TimeWheel timeWheel){
            this.selfTimeWheel = timeWheel;
            this.selfTimeWheel.setTimeExpiredHandler(this);
        }

        public void goToNextTime(){
            selfTimeWheel.goToNextTime();

            if (leftTimeWheel != null && selfTimeWheel.isLastClockDial()){
                scheduler.schedule(new Runnable() {
                    @Override
                    public void run() {
                        leftTimeWheel.goToNextTime();
                    }
                });
            }
        }

        @Override
        public Integer addTask(long expiryTime, Object data) {
            Integer addTask = selfTimeWheel.addTask(expiryTime, data);
            if (rightTimeWheel != null && addTask < 0){
                addTask = rightTimeWheel.addTask(expiryTime, data);
            }
            return addTask;
        }

        @Override
        public Integer addTask(TimeTask timeTask) {
            Integer addTask = selfTimeWheel.addTask(timeTask);
            if (rightTimeWheel != null && addTask < 0){
                addTask = rightTimeWheel.addTask(timeTask);
            }
            return addTask;
        }

        @Override
        public void handle(int timeIndex, List<TimeTask> timeTasks) {
            if (rightTimeWheel != null){
                for (TimeTask timeTask : timeTasks){
                    rightTimeWheel.addTask(timeTask.getExpiryTime(), timeTask.getData());
                }
            } else if (timeExpiredHandler != null){
                timeExpiredHandler.handle(timeIndex, timeTasks);
            }
        }

        @Override
        public long getTickTime() {
            return selfTimeWheel.getTickTime();
        }

        @Override
        public long getCycleTime() {
            return selfTimeWheel.getCycleTime();
        }

        @Override
        public int getSize() {
            return selfTimeWheel.getSize();
        }

        @Override
        public boolean isLastClockDial() {
            return selfTimeWheel.isLastClockDial();
        }
    }

    private HierarchicalTimeWheelInner firstTimeWheel;

    private HierarchicalTimeWheelInner lastTimeWheel;

    private List<HierarchicalTimeWheelInner> timeWheels;

    public HierarchicalTimeWheel(int[] sizes, long tickTime){
        if (sizes == null || sizes.length == 0) {
            throw new HierarchicalCreateException("构造参数 sizes 不能为空。");
        }
        if (tickTime < 0){
            throw new HierarchicalCreateException("构造参数 index 越界，不能比 0 小。。");
        }

        timeWheels = new LinkedList<>();

        HierarchicalTimeWheelInner right = new HierarchicalTimeWheelInner(TimeWheelUtil.TimeWheelNull);

        for (int i = sizes.length - 1; i >= 0; i--){
            TimeWheel timeWheel = new SimpleTimeWheel(sizes[i], tickTime);

            HierarchicalTimeWheelInner htw = new HierarchicalTimeWheelInner(timeWheel);
            htw.scheduler = this;
            htw.rightTimeWheel = right;
            htw.timeExpiredHandler = this;

            timeWheels.add(0, htw);

            // 下一轮的数据
            right.leftTimeWheel = htw;
            right = htw;
            tickTime = htw.getCycleTime();
        }

        firstTimeWheel = timeWheels.get(0);
        lastTimeWheel = timeWheels.get(timeWheels.size() - 1);
        lastTimeWheel.rightTimeWheel = null;
    }


    @Override
    public void schedule(Runnable runnable) {
        scheduler.schedule(runnable);
    }

    @Override
    public void handle(int timeIndex, List<TimeTask> timeTasks) {
        timeExpiredHandler.handle(timeIndex, timeTasks);
    }

    @Override
    public void goToNextTime() {
        lastTimeWheel.goToNextTime();
    }

    @Override
    public Integer addTask(long expiryTime, Object data) {
        return firstTimeWheel.addTask(expiryTime, data);
    }

    @Override
    public Integer addTask(TimeTask timeTask) {
        return firstTimeWheel.addTask(timeTask);
    }

    @Override
    public long getTickTime() {
        return lastTimeWheel.getTickTime();
    }

    @Override
    public long getCycleTime() {
        return lastTimeWheel.getCycleTime();
    }

    @Override
    public int getClockDial() {
        return 0;
    }

    @Override
    public int getSize() {
        return lastTimeWheel.getSize();
    }

    @Override
    public boolean isLastClockDial() {
        return lastTimeWheel.isLastClockDial();
    }
}
