package hello.designpatterns.decorator.condiment;

import hello.designpatterns.decorator.Beverage;

/**
 * 摩卡调料
 * Created by Tykkidream on 2017/10/18.
 */
public class MochaCondiment extends AbstractCondiment{
    public MochaCondiment(Beverage beverage){
        super();
        this.setBeverage(beverage);
    }
    @Override
    public String getDescription() {
        return "摩卡 " + getBeverage().getDescription();
    }

    @Override
    public double cost() {
        return 0.20 + getBeverage().cost();
    }
}
