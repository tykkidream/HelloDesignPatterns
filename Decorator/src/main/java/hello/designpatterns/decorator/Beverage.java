package hello.designpatterns.decorator;

/**
 * 饮料
 * Created by Tykkidream on 2017/10/17.
 */
public interface Beverage {
    /**
     * 得到描述信息
     * @return
     */
    String getDescription();

    /**
     * 计算金额
     * @return
     */
    double cost();
}
