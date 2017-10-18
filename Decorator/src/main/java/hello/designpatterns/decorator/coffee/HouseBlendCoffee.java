package hello.designpatterns.decorator.coffee;

import hello.designpatterns.decorator.Beverage;

/**
 * 家常咖啡
 * Created by Tykkidream on 2017/10/17.
 */
public class HouseBlendCoffee implements Beverage {
    @Override
    public String getDescription() {
        return "家常咖啡";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
