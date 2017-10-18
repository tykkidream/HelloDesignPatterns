package hello.designpatterns.decorator.coffee;

import hello.designpatterns.decorator.Beverage;

/**
 * 特浓咖啡
 * Created by Tykkidream on 2017/10/17.
 */
public class EspressoCoffee implements Beverage {
    @Override
    public String getDescription() {
        return "特浓咖啡";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
