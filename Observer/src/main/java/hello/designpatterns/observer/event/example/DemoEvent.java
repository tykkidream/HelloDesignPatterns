package hello.designpatterns.observer.event.example;

import hello.designpatterns.observer.event.Event;

/**
 * Created by Tykkidream on 2017/10/21.
 */
public class DemoEvent implements Event {
    private Long id;

    private String title;

    public DemoEvent(){}

    public DemoEvent(Long id, String title) {
        setId(id);
        setTitle(title);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
