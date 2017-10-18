package hello.designpatterns.decorator.condiment;

import hello.designpatterns.decorator.Beverage;

/**
 * 抽象的装饰者
 * Created by Tykkidream on 2017/10/18.
 */
public abstract class AbstractCondiment implements Beverage {
    /**
     * 饮料
     *
     * 被装饰的组件
     */
    private Beverage beverage;

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }


}
