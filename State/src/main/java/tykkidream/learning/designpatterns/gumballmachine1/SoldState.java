package tykkidream.learning.designpatterns.gumballmachine1;

/**
 * “出售糖果”，买家只有获得一粒糖果
 *
 * Created by Tykkidream on 2015/10/9.
 */
class SoldState implements GumballState{
    protected static final SoldState Instance = new SoldState();

    @Override
    public String toString() {
        return "正在出售糖果中";
    }

    @Override
    public void insertMoney(GumballMachine gm) {
        System.out.println("\t【投入】【注意】请等待，我们准备要给了你糖果了！");
    }

    @Override
    public void ejectMoney(GumballMachine gm) {
        System.out.println("\t【退回】【注意】抱歉，你已经转动了糖果机的曲柄！");
    }

    @Override
    public void turnCrank(GumballMachine gm) {
        System.out.println("\t【转动】【注意】转两次也不会再多给你其它的糖果！");
    }

    @Override
    public void dispense(GumballMachine gm) {
        gm.releaseBall();
        if (gm.gumballNum() > 0){
            // 从当前“出售糖果”的状态,转变为“没有投入钱币”的状态。
            gm.setState(NoMoneyState.Instance);
        } else {
            // 从当前“出售糖果”的状态,转变为“糖果售罄”的状态。
            gm.setState(SoldOutState.Instance);
        }
        System.out.println("\t【发放】【提示】你的糖果已经发放完成！");
    }

    private SoldState(){

    }
}
