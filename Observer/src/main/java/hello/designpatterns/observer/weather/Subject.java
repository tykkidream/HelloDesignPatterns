package hello.designpatterns.observer.weather;

/**
 * 主题
 * Created by Tykkidream on 2017/10/19.
 */
public interface Subject {
    boolean registerObserver(Observer observer);

    boolean removeObserver(Observer observer);

    void notifyObservers();
}
