package hello.designpatterns.timewheel;

/**
 * Created by Saber on 2017/4/11.
 */
public interface TimeWheel {
    /**
     * The clock dial turns by one unit.
     */
    void goToNextTime();

    Integer addTask(long expiryTime, Object data);

    Integer addTask(TimeTask timeTask);

    TimeExpiredHandler getTimeExpiredHandler();

    void setTimeExpiredHandler(TimeExpiredHandler timeExpiredHandler);

    TimeScheduler getScheduler();

    void setScheduler(TimeScheduler scheduler);

    long getTickTime();

    long getCycleTime();

    int getClockDial();

    int getSize();

    boolean isLastClockDial();
}
