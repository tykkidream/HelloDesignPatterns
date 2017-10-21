package hello.designpatterns.observer.event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tykkidream on 2017/10/21.
 */
public class EventBus  {
    private List<EventListener> eventListeners = new ArrayList<>();

    public boolean registerListener(EventListener listener) {
        return eventListeners.add(listener);
    }

    public boolean removeListener(EventListener listener) {
        return eventListeners.remove(listener);
    }

    public void publishEvents(List<Event> events) {
        for (Event event : events) {
            for (EventListener el : eventListeners) {
                el.handle(event);
            }
        }
    }
}
