package hello.designpatterns.observer.weather;

/**
 * 观察者
 * Created by Tykkidream on 2017/10/19.
 */
public interface Observer {
    void update(float temp, float humidity, float pressure);
}
