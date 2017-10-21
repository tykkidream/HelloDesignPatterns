package hello.designpatterns.observer.weather.display;

import hello.designpatterns.observer.weather.Observer;

/**
 * 气象统计布告板
 * Created by Tykkidream on 2017/10/19.
 */
public class ForecastDisplay implements Observer {
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println(String.format("气象统计信息 >>> 温度：%s 湿度：%s 压力：%s", temp, humidity, pressure));
    }
}
