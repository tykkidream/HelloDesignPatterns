package tykkidream.learning.designpatterns.gumballmachine1;

/**
 * “糖果售罄”，糖果机中没有任何一粒糖果
 *
 * Created by Tykkidream on 2015/10/9.
 */
class SoldOutState implements GumballState{
    protected static final SoldOutState Instance = new SoldOutState();

    @Override
    public String toString() {
        return "已经售罄";
    }

    @Override
    public void insertMoney(GumballMachine gm) {
        System.out.println("\t【投入】【注意】不可投入钱币，该机器已经卖完了！");
    }

    @Override
    public void ejectMoney(GumballMachine gm) {
        System.out.println("\t【退回】【注意】不能退回钱币，你还没有投入钱币！");
    }

    @Override
    public void turnCrank(GumballMachine gm) {
        System.out.println("\t【转动】【注意】已经转动了曲柄，但目前没有糖果！");
    }

    @Override
    public void dispense(GumballMachine gm) {
        System.out.println("\t【发放】【注意】已经售罄完毕，没有糖果可以发放！");
    }

    private SoldOutState(){}
}
