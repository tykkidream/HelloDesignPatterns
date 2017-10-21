package hello.designpatterns.observer.weather.display;

import hello.designpatterns.observer.weather.Observer;

/**
 * 当前气象布告板
 * Created by Tykkidream on 2017/10/19.
 */
public class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    private void display() {
        System.out.println(String.format("当前气象信息 >>> 温度：%s 湿度：%s", temperature, humidity));
    }
}
