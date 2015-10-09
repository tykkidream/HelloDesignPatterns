package tykkidream.learning.designpatterns.gumballmachine2;

import java.util.Random;

/**
 * 糖果机状态（枚举版）
 *
 * Created by Tykkidream on 2015/10/9.
 */
enum GumballState {
    /**
     * “出售糖果”，买家只有获得一粒糖果
     */
    Sold {
        @Override
        public String toString() {
            return "正在出售糖果中";
        }

        @Override
        protected void insertMoney(GumballMachine gm) {
            System.out.println("\t【投入】【注意】请等待，我们准备要给了你糖果了！");
        }

        @Override
        protected void ejectMoney(GumballMachine gm) {
            System.out.println("\t【退回】【注意】抱歉，你已经转动了糖果机的曲柄！");
        }

        @Override
        protected void turnCrank(GumballMachine gm) {
            System.out.println("\t【转动】【注意】转两次也不会再多给你其它的糖果！");
        }

        @Override
        protected void dispense(GumballMachine gm) {
            gm.releaseBall();
            if (gm.gumballNum() > 0){
                // 从当前“出售糖果”的状态,转变为“没有投入钱币”的状态。
                gm.setState(NoMoney);
            } else {
                // 从当前“出售糖果”的状态,转变为“糖果售罄”的状态。
                gm.setState(SoldOut);
            }
            System.out.println("\t【发放】【提示】你的糖果已经发放完成！");
        }
    },
    /**
     * “糖果售罄”，糖果机中没有任何一粒糖果
     */
    SoldOut{
        @Override
        public String toString() {
            return "已经售罄";
        }

        @Override
        protected void insertMoney(GumballMachine gm) {
            System.out.println("\t【投入】【注意】不可投入钱币，该机器已经卖完了！");
        }

        @Override
        protected void ejectMoney(GumballMachine gm) {
            System.out.println("\t【退回】【注意】不能退回钱币，你还没有投入钱币！");
        }

        @Override
        protected void turnCrank(GumballMachine gm) {
            System.out.println("\t【转动】【注意】已经转动了曲柄，但目前没有糖果！");
        }

        @Override
        protected void dispense(GumballMachine gm) {
            System.out.println("\t【发放】【注意】已经售罄完毕，没有糖果可以发放！");
        }
    },
    /**
     * “没有投入钱币”,糖果机有没有投入钱币
     */
    NoMoney {
        @Override
        public String toString() {
            return "没有投入钱币";
        }

        @Override
        protected void insertMoney(GumballMachine gm) {
            // 从当前“没有投入钱币”的状态,转变为“投入了钱币”的状态。
            gm.setState(HasMoney);
            System.out.println("\t【投入】【提示】你刚刚投入了一枚钱币！");
        }

        @Override
        protected void ejectMoney(GumballMachine gm) {
            System.out.println("\t【退回】【注意】你没有投入枚钱币，不能退回钱币！");
        }

        @Override
        protected void turnCrank(GumballMachine gm) {
            System.out.println("\t【转动】【注意】你没有投入枚钱币，不能要求糖果！");
        }

        @Override
        protected void dispense(GumballMachine gm) {
            System.out.println("\t【发放】【注意】你没有投入枚钱币，不能发放糖果！");
        }
    },
    /**
     * “投入了钱币”，向糖果中投入了钱币
     */
    HasMoney {
        @Override
        public String toString() {
            return "已经投入钱币";
        }

        Random randomWinner = new Random(System.currentTimeMillis());

        @Override
        protected void insertMoney(GumballMachine gm) {
            System.out.println("\t【投入】【注意】你已经投入枚钱币，不能再次投入！");
        }

        @Override
        protected void ejectMoney(GumballMachine gm) {
            // 从当前“投入了钱币”的状态,转变为“没有投入钱币”的状态。
            gm.setState(HasMoney);
            System.out.println("\t【退回】【提示】已经退回了你的钱币！");
        }

        @Override
        protected void turnCrank(GumballMachine gm) {
            System.out.println("\t【转动】【提示】正在转动曲柄！");
            int winner = randomWinner.nextInt(10);
            if ((winner == 0) && (gm.gumballNum() > 1)) {
                // 从当前“投入了钱币”的状态,转变为“幸运者”的状态。
                gm.setState(Winner);
            } else {
                // 从当前“投入了钱币”的状态,转变为“出售糖果”的状态。
                gm.setState(Sold);
            }

        }

        @Override
        protected void dispense(GumballMachine gm) {
            System.out.println("\t【发放】【注意】你没有转动的曲柄，不能发放糖果！");
        }
    },
    /**
     * “幸运者”，可以匈牙利丙粒糖果
     */
    Winner {
        @Override
        public String toString() {
            return "有个幸运儿";
        }

        @Override
        protected void insertMoney(GumballMachine gm) {
            System.out.println("\t【投入】【注意】请等待，我们准备要给了你糖果了！");
        }

        @Override
        protected void ejectMoney(GumballMachine gm) {
            System.out.println("\t【退回】【注意】抱歉，你已经转动了糖果机的曲柄！");
        }

        @Override
        protected void turnCrank(GumballMachine gm) {
            System.out.println("\t【转动】【注意】转两次也不会再多给你其它的糖果！");
        }

        @Override
        protected void dispense(GumballMachine gm) {
            gm.releaseBall();
            if (gm.gumballNum() == 0) {
                // 从当前“幸运者”的状态,转变为“糖果售罄”的状态。
                gm.setState(SoldOut);
            } else {
                gm.releaseBall();
                if (gm.gumballNum() > 0) {
                    // 从当前“幸运者”的状态,转变为“没有投入钱币”的状态。
                    gm.setState(NoMoney);
                } else {
                    // 从当前“幸运者”的状态,转变为“糖果售罄”的状态。
                    gm.setState(SoldOut);
                }
            }
            System.out.println("\t【幸运】【提示】你的糖果已经发放完成！");
        }
    };

    /**
     * 投入钱币
     */
    protected abstract void insertMoney(GumballMachine gm);

    /**
     * 退回钱币
     */
    protected abstract void ejectMoney(GumballMachine gm);

    /**
     * 转动糖果机的曲柄
     */
    protected abstract void turnCrank(GumballMachine gm);

    /**
     * 发放糖果
     */
    protected abstract void dispense(GumballMachine gm);
}
