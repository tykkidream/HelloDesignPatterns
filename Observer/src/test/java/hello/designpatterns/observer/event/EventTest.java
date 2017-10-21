package hello.designpatterns.observer.event;

import hello.designpatterns.observer.event.example.DemoEvent;
import hello.designpatterns.observer.event.example.DemoEventLinsener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tykkidream on 2017/10/21.
 */
public class EventTest {
    @Test
    public void test(){
        EventBus eventBus = new EventBus();

        eventBus.registerListener(new DemoEventLinsener());

        List<Event> events = new ArrayList<>();

        events.add(new DemoEvent(523L, "发现"));

        eventBus.publishEvents(events);
    }
}
