package hello.designpatterns.timewheel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Saber on 2017/4/13.
 */
public class TimeSlot {
    private List<TimeTask> timeTasks;

    private TimeSlot next;

    public List<TimeTask> expired(){
        return popupTimeSlots();
    }

    private List<TimeTask> popupTimeSlots(){
        List<TimeTask> currentTimeTasks = timeTasks;
        timeTasks = null;
        if (next != null){
            timeTasks = next.popupTimeSlots();
            if (next.timeTasks == null){
                next = null;
            }
        }
        return currentTimeTasks;
    }

    public Integer addTask(int index, TimeTask timeTask){
        TimeSlot timeSlot = getOrCreateTimeSlotByIndex(index);

        if (timeSlot == null){
            return -1;
        }

        List<TimeTask> timeTasks = timeSlot.getOrCrateTimeSlotItems();
        timeTasks.add(timeTask);
        return 1;
    }

    private TimeSlot getOrCreateTimeSlotByIndex(int index) {
        if (index == 0){
            return this;
        } else if (index > 0){
            TimeSlot nextTimeSlot = getOrCrateNextTimeSlot();
            return nextTimeSlot.getOrCreateTimeSlotByIndex(index - 1);
        }
        return null;
    }

    private TimeSlot getOrCrateNextTimeSlot() {
        if (next == null){
            synchronized (this){
                if (next == null){
                    next = new TimeSlot();
                }
            }
        }
        return next;
    }

    private List<TimeTask> getOrCrateTimeSlotItems() {
        if (timeTasks == null){
            synchronized (this) {
                if (timeTasks == null) {
                    timeTasks = new LinkedList<>();
                }
            }
        }
        return timeTasks;
    }
}
