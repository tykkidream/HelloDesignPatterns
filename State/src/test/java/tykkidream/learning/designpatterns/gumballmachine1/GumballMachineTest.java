package tykkidream.learning.designpatterns.gumballmachine1;

/**
 * Created by Tykkidream on 2015/10/9.
 */
public class GumballMachineTest {
    public static void main(String[] args) {
        GumballMachine gumballMachine = new GumballMachine(10);

        System.out.println(gumballMachine);

        gumballMachine.insertMoney();
        gumballMachine.turnCrank();
        gumballMachine.insertMoney();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertMoney();
        gumballMachine.turnCrank();
        gumballMachine.insertMoney();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertMoney();
        gumballMachine.turnCrank();
        gumballMachine.insertMoney();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertMoney();
        gumballMachine.turnCrank();
        gumballMachine.insertMoney();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);

        gumballMachine.insertMoney();
        gumballMachine.turnCrank();
        gumballMachine.insertMoney();
        gumballMachine.turnCrank();

        System.out.println(gumballMachine);
    }
}
