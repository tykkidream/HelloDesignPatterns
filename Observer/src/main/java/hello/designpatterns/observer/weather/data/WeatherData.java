package hello.designpatterns.observer.weather.data;

import hello.designpatterns.observer.weather.Observer;
import hello.designpatterns.observer.weather.Subject;

import java.util.ArrayList;
import java.util.List;

/**
 * 气象信息的主题
 * Created by Tykkidream on 2017/10/19.
 */
public class WeatherData implements Subject {
    private List<Observer> observers;

    /**
     * 温度
     */
    private float temperature;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 压力
     */
    private float pressure;

    public WeatherData(){
        observers = new ArrayList<Observer>();
    }

    /**
     * 注册观察者
     * @return
     */
    public boolean registerObserver(Observer observer) {
        return observers.add(observer);
    }

    /**
     * 移除观察者
     * @return
     */
    public boolean removeObserver(Observer observer) {
        return observers.remove(observer);
    }

    /**
     * 通知所有的观察者
     */
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = observers.get(i);
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged(float temperature, float humidity, float pressure) {
        setTemperature(temperature);
        setHumidity(humidity);
        setPressure(pressure);
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }
}
