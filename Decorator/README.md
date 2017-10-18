装饰者模式
==========

说明：

    动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更有弹性的替代方案。

装饰者模式也体现了开闭原则。

包含：
- 组件（components）：示例中，`coffee`包下的类，表示各种咖啡。
- 装饰器（Decorator）：示例中，`condiment`包下的类，表示各种调料。

要求：
- components和Decorator实现相同的接口或者抽象类。 示例中是`Beverage`接口。

示例业务：

可以购买各种咖啡时，也可以要求在其中加入各种调料，例如：蒸奶（Steamed Milk）、豆浆（Soy）、摩卡（Mocha，也就是巧克力风味）或覆盖奶泡。根据所加入的调料收取不同的费用。

[示例代码](src/test/java/hello/designpatterns/decorator/BeverageTest.java)
