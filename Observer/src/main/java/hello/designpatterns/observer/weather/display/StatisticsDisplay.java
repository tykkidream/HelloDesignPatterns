package hello.designpatterns.observer.weather.display;

import hello.designpatterns.observer.weather.Observer;

/**
 * 天气预报布告板
 * Created by Tykkidream on 2017/10/19.
 */
public class StatisticsDisplay implements Observer {
    public void update(float temp, float humidity, float pressure) {
        System.out.println(String.format("天气预报信息 >>> 温度：%s 湿度：%s 压力：%s", temp, humidity, pressure));
    }
}
