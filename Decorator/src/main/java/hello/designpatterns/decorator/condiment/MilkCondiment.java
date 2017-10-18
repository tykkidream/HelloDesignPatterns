package hello.designpatterns.decorator.condiment;

import hello.designpatterns.decorator.Beverage;

/**
 * 牛奶调料
 * Created by Tykkidream on 2017/10/18.
 */
public class MilkCondiment extends AbstractCondiment{
    public MilkCondiment(Beverage beverage) {
        super();
        this.setBeverage(beverage);
    }
    @Override
    public String getDescription() {
        return "牛奶 " + getBeverage().getDescription();
    }

    @Override
    public double cost() {
        return 0.10 + getBeverage().cost();
    }
}
