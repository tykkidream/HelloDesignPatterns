package hello.designpatterns.timewheel.feature;

import hello.designpatterns.timewheel.TimeScheduler;
import hello.designpatterns.timewheel.TimeSlot;
import hello.designpatterns.timewheel.TimeTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Saber on 2017/4/15.
 */
public class SimpleTimeWheel extends AbstractTimeWheel {
    protected List<TimeSlot> timeSlots;

    private long tickTime;

    protected long cycleTime;

    private int clockDial = -1;

    private int clockDialMax;

    public SimpleTimeWheel(int size, long tickTime) {
        this.tickTime = tickTime;
        this.cycleTime = tickTime * size;
        this.timeSlots = createTimeSlots(size);
        this.scheduler = TimeScheduler.Single_Thread_Scheduler;
        this.clockDialMax = size - 1;
    }

    private List<TimeSlot> createTimeSlots(int size) {
        List<TimeSlot> timeSlots = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            TimeSlot timeSlot = new TimeSlot();
            timeSlots.add(i, timeSlot);
        }
        return timeSlots;
    }

    public synchronized void goToNextTime() {
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                int clockDial = nextClockDial(getClockDial(), timeSlots.size());
                setClockDial(clockDial);
                TimeSlot timeSlot = timeSlots.get(clockDial);
                List<TimeTask> timeTasks = timeSlot.expired();
                if (timeExpiredHandler != null && timeTasks != null) {
                    timeExpiredHandler.handle(clockDial, timeTasks);
                }
            }
        });
    }

    protected int nextClockDial(int currentDial, int maxDial) {
        currentDial++;
        if (currentDial < maxDial) {
            return currentDial;
        } else {
            return 0;
        }
    }

    public Integer addTask(long expiryTime, Object data) {
        TimeTask timeTask = new TimeTask(System.currentTimeMillis(), expiryTime, data, 0L);

        return addTask(timeTask);
    }

    @Override
    public Integer addTask(TimeTask timeTask) {
        long createTime = System.currentTimeMillis();

        if (timeTask == null){
            System.out.println("失败");
            return -1;
        }

        long expiryTime = timeTask.getExpiryTime();

        if (timeTask.getExpiryTime() < createTime) {
            System.out.println("失败");
            return -1;
        }

        int index;
        int cycleNum;
        long restTime;

        restTime = expiryTime - createTime;

        if (restTime <  tickTime){
            return -1;
        }

        cycleNum = (int) (restTime / cycleTime);

        restTime = restTime % cycleTime;
        index = calculateIndex((int) (restTime / tickTime));

        if (restTime % tickTime != 0){
            index++;
            restTime = index * tickTime;
        }

        restTime = restTime % tickTime == 0 ? 0 : tickTime + restTime;

        timeTask.setRestTime(restTime);
        TimeSlot timeSlot = timeSlots.get(index);
        return timeSlot.addTask(cycleNum, timeTask);
    }

    private int calculateIndex(int index){
        index = index + clockDial;
        if (index > timeSlots.size()){
            return timeSlots.size() - index;
        }
        return index;
    }

    public long getTickTime() {
        return tickTime;
    }

    private void setClockDial(int clockDial) {
        this.clockDial = clockDial;
    }

    public int getClockDial() {
        return clockDial;
    }

    @Override
    public int getSize() {
        return timeSlots.size();
    }

    @Override
    public boolean isLastClockDial() {
        return 0 == clockDial;
    }

    public long getCycleTime() {
        return cycleTime;
    }

    public List<TimeSlot> getTimeSlots() {
        return Collections.unmodifiableList(timeSlots);
    }

}
