package hello.designpatterns.decorator;

import hello.designpatterns.decorator.coffee.DarkRoastCoffee;
import hello.designpatterns.decorator.coffee.DecafCoffee;
import hello.designpatterns.decorator.condiment.MilkCondiment;
import hello.designpatterns.decorator.condiment.MochaCondiment;
import hello.designpatterns.decorator.condiment.SoyCondiment;
import org.junit.Test;

/**
 * Created by Tykkidream on 2017/10/18.
 */
public class BeverageTest {

    @Test
    public void test(){
        Beverage darkRoastCoffee = new DarkRoastCoffee();

        Beverage decafCoffee = new DecafCoffee();

        Beverage milk = new MilkCondiment(darkRoastCoffee);

        Beverage mocha_milk = new MochaCondiment(milk);

        Beverage soy = new SoyCondiment(decafCoffee);

        System.out.println(mocha_milk.getDescription() + "\t" + mocha_milk.cost());
        System.out.println(soy.getDescription() + "\t" + soy.cost());
    }
}
