package hello.designpatterns.decorator.condiment;

import hello.designpatterns.decorator.Beverage;

/**
 * 奶泡调料
 * Created by Tykkidream on 2017/10/18.
 */
public class WhipCondiment extends AbstractCondiment {
    public WhipCondiment(Beverage beverage) {
        super();
        this.setBeverage(beverage);
    }
    @Override
    public String getDescription() {
        return "奶泡 " + getBeverage().getDescription();
    }

    @Override
    public double cost() {
        return 0.10 + getBeverage().cost();
    }
}
