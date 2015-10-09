状态模式
========

[InterfaceState]: ./src/main/java/tykkidream/learning/designpatterns/gumballmachine1/ "使用接口的状态"
[EnumerateState]: ./src/main/java/tykkidream/learning/designpatterns/gumballmachine2/ "使用枚举的状态"
[InterfaceStateTest]: ./src/test/java/tykkidream/learning/designpatterns/gumballmachine1/GumballMachineTest.java "GumballMachineTest.java"
[EnumerateStateTest]: ./src/test/java/tykkidream/learning/designpatterns/gumballmachine2/GumballMachineTest.java "GumballMachineTest.java"

有两种写法：

1. [接口状态] [InterfaceState]（[测试] [InterfaceStateTest]）
2. [枚举状态] [EnumerateState]（[测试] [EnumerateStateTest]）

这两人例子是同一个业务需求的不同实现，业务需求如下：

    某个糖果公司有一款糖果机，装有很多的糖果。当买家向糖果机中投入钱币后，就可以转动曲柄了，也可退回钱币给买家；当转动完曲柄后，糖果机就会发放一个糖果；为了吸引更多的客户购买糖果，会有10%的机率多送买家一个糖果；糖果机的的糖果存量不断会减少，直至糖果售罄。
