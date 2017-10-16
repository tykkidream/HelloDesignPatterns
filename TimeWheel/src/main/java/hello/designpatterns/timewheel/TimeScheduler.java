package hello.designpatterns.timewheel;

/**
 * Created by Saber on 2017/4/14.
 */
public interface TimeScheduler {
    void schedule(Runnable runnable);

    TimeScheduler Single_Thread_Scheduler = new TimeScheduler() {
        @Override
        public void schedule(Runnable runnable) {
            try {
                runnable.run();
            } catch (Throwable throwable){
                throwable.printStackTrace();
            }
        }
    };
}
