package hello.designpatterns.timewheel;

import java.util.List;

/**
 * Created by Saber on 2017/4/15.
 */
public interface TimeExpiredHandler {
    void handle(int timeIndex, List<TimeTask> timeTasks);

    TimeExpiredHandler Default = new TimeExpiredHandler(){

        @Override
        public void handle(int timeIndex, List<TimeTask> timeTasks) {

        }
    };
}
