package tykkidream.learning.designpatterns.gumballmachine2;

/**
 * 糖果机（枚举版）
 *
 * Created by Tykkidream on 2015/10/9.
 */
public class GumballMachine {
    private GumballState state = GumballState.SoldOut;
    private int gumballNum = 0;

    /**
     * 获取糖果机剩余糖果存量
     * @return
     */
    public int gumballNum() {
        return this.gumballNum;
    }

    /**
     * 投入钱币
     */
    public void insertMoney(){
        state.insertMoney(this);
    }

    /**
     * 退回钱币
     */
    public void ejectMoney(){
        state.ejectMoney(this);
    }

    /**
     * 转动糖果机的曲柄
     */
    public void turnCrank(){
        state.turnCrank(this);
        state.dispense(this);
    }

    public GumballMachine(int gumballNum){
        this.setGumballNum(gumballNum);

        if (gumballNum > 0) {
            state = GumballState.NoMoney;
        }
    }

    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("\n强强糖果公司");
        result.append("\n库存： " + gumballNum + " 个糖果");
        if (gumballNum != 1) {
            result.append("s");
        }
        result.append("\n");
        result.append("糖果目前 " + state + "\n");
        return result.toString();
    }

    protected void releaseBall(){
        if (this.gumballNum != 0){
            this.gumballNum = this.gumballNum - 1;
        }
    }

    protected void setState(GumballState state) {
        this.state = state;
    }

    private void setGumballNum(int gumballNum){
        this.gumballNum = gumballNum;
    }
}
