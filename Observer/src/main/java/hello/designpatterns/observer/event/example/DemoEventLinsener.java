package hello.designpatterns.observer.event.example;

import hello.designpatterns.observer.event.Event;
import hello.designpatterns.observer.event.EventListener;

/**
 * Created by Tykkidream on 2017/10/21.
 */
public class DemoEventLinsener implements EventListener {
    @Override
    public void handle(Event event) {
        if (event instanceof DemoEvent) {
            DemoEvent de = (DemoEvent)event;

            System.out.println(String.format("Demo Event >>> id：%s title：%s",de.getId(), de.getTitle()));
        }
    }
}
