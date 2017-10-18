package hello.designpatterns.decorator.condiment;

import hello.designpatterns.decorator.Beverage;

/**
 * 豆浆调料
 *
 * Created by Tykkidream on 2017/10/18.
 */
public class SoyCondiment extends AbstractCondiment {
    public SoyCondiment(Beverage beverage) {
        super();
        this.setBeverage(beverage);
    }
    @Override
    public String getDescription() {
        return "豆浆 " + getBeverage().getDescription();
    }

    @Override
    public double cost() {
        return 0.15 + getBeverage().cost();
    }
}
