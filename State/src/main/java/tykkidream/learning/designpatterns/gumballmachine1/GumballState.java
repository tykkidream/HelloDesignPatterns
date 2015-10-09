package tykkidream.learning.designpatterns.gumballmachine1;

/**
 * 糖果机状态（接口版）
 *
 * Created by Tykkidream on 2015/10/9.
 */
interface GumballState {
    /**
     * 投入钱币
     */
    void insertMoney(GumballMachine gm);

    /**
     * 退回钱币
     */
    void ejectMoney(GumballMachine gm);

    /**
     * 转动糖果机的曲柄
     */
    void turnCrank(GumballMachine gm);

    /**
     * 发放糖果
     */
    void dispense(GumballMachine gm);
}
