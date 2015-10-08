package tykkidream.learning.designpatterns.singleton;

/**
 * Created by Tykkidream on 2015/10/8.
 *
 * 静态内部类
 */
public class InternalSingleton {

    private static class SingletonHolder{
        private final static  InternalSingleton uniqueInstance = new InternalSingleton();
    }

    private InternalSingleton(){
    }

    public static InternalSingleton getInstance(){
        return SingletonHolder.uniqueInstance;
    }
}
