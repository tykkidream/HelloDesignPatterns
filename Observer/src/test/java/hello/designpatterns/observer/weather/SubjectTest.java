package hello.designpatterns.observer.weather;

import hello.designpatterns.observer.weather.data.WeatherData;
import hello.designpatterns.observer.weather.display.CurrentConditionsDisplay;
import hello.designpatterns.observer.weather.display.ForecastDisplay;
import hello.designpatterns.observer.weather.display.StatisticsDisplay;
import hello.designpatterns.observer.weather.display.ThirdPartyDisplay;
import org.junit.Test;

/**
 * Created by Tykkidream on 2017/10/21.
 */
public class SubjectTest {
    @Test
    public void test(){
        WeatherData weatherData = new WeatherData();

        weatherData.registerObserver(new CurrentConditionsDisplay());
        weatherData.registerObserver(new ForecastDisplay());
        weatherData.registerObserver(new StatisticsDisplay());
        weatherData.registerObserver(new ThirdPartyDisplay());

        weatherData.measurementsChanged(1,2,3);
    }
}
