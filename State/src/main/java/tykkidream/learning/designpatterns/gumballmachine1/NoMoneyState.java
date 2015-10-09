package tykkidream.learning.designpatterns.gumballmachine1;

/**
 * “没有投入钱币”,糖果机有没有投入钱币
 *
 * Created by Tykkidream on 2015/10/9.
 */
class NoMoneyState implements GumballState{
    protected static final NoMoneyState Instance = new NoMoneyState();

    @Override
    public String toString() {
        return "没有投入钱币";
    }

    @Override
    public void insertMoney(GumballMachine gm) {
        // 从当前“没有投入钱币”的状态,转变为“投入了钱币”的状态。
        gm.setState(HasMoneyState.Instance);
        System.out.println("\t【投入】【提示】你刚刚投入了一枚钱币！");
    }

    @Override
    public void ejectMoney(GumballMachine gm) {
        System.out.println("\t【退回】【注意】你没有投入枚钱币，不能退回钱币！");
    }

    @Override
    public void turnCrank(GumballMachine gm) {
        System.out.println("\t【转动】【注意】你没有投入枚钱币，不能要求糖果！");
    }

    @Override
    public void dispense(GumballMachine gm) {
        System.out.println("\t【发放】【注意】你没有投入枚钱币，不能发放糖果！");
    }

    private NoMoneyState(){

    }
}
