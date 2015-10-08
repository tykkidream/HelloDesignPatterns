package tykkidream.learning.designpatterns.singleton;

/**
 * Created by Tykkidream on 2015/10/8.
 *
 * 懒汉
 */
public class LazySingleton {
    private static LazySingleton uniqueInstance;

    private LazySingleton(){
    }

    public static synchronized LazySingleton getInstance(){
        if(uniqueInstance == null){
            uniqueInstance=new LazySingleton();
        }
        return uniqueInstance;
    }
}
