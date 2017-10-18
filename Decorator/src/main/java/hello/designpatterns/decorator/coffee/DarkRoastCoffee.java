package hello.designpatterns.decorator.coffee;

import hello.designpatterns.decorator.Beverage;

/**
 * 深度烘焙咖啡
 * Created by Tykkidream on 2017/10/17.
 */
public class DarkRoastCoffee implements Beverage {
    @Override
    public String getDescription() {
        return "深度烘焙咖啡";
    }

    @Override
    public double cost() {
        return 0.99;
    }
}
