package tykkidream.learning.designpatterns.gumballmachine1;

import java.util.Random;

/**
 * “投入了钱币”，向糖果中投入了钱币
 *
 * Created by Tykkidream on 2015/10/9.
 */
class HasMoneyState implements GumballState{
    protected static final HasMoneyState Instance = new HasMoneyState();

    @Override
    public String toString() {
        return "已经投入钱币";
    }

    Random randomWinner = new Random(System.currentTimeMillis());

    @Override
    public void insertMoney(GumballMachine gm) {
        System.out.println("\t【投入】【注意】你已经投入枚钱币，不能再次投入！");
    }

    @Override
    public void ejectMoney(GumballMachine gm) {
        // 从当前“投入了钱币”的状态,转变为“没有投入钱币”的状态。
        gm.setState(HasMoneyState.Instance);
        System.out.println("\t【退回】【提示】已经退回了你的钱币！");
    }

    @Override
    public void turnCrank(GumballMachine gm) {
        System.out.println("\t【转动】【提示】正在转动曲柄！");
        int winner = randomWinner.nextInt(10);
        if ((winner == 0) && (gm.gumballNum() > 1)) {
            // 从当前“投入了钱币”的状态,转变为“幸运者”的状态。
            gm.setState(WinnerState.Instance);
        } else {
            // 从当前“投入了钱币”的状态,转变为“出售糖果”的状态。
            gm.setState(SoldState.Instance);
        }

    }

    @Override
    public void dispense(GumballMachine gm) {
        System.out.println("\t【发放】【注意】你没有转动的曲柄，不能发放糖果！");
    }

    private HasMoneyState(){

    }
}
