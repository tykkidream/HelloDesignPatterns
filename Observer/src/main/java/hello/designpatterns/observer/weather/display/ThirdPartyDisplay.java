package hello.designpatterns.observer.weather.display;

import hello.designpatterns.observer.weather.Observer;

/**
 * 第三方布告板
 * Created by Tykkidream on 2017/10/19.
 */
public class ThirdPartyDisplay implements Observer {
    @Override
    public void update(float temp, float humidity, float pressure) {
        System.out.println(String.format("第三方信息   >>> 温度：%s 湿度：%s 压力：%s", temp, humidity, pressure));
    }
}
