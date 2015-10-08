package tykkidream.learning.designpatterns.singleton;

/**
 * Created by Tykkidream on 2015/10/8.
 *
 * 饿汉
 */
public class HungrySingleton {
    private static HungrySingleton uniqueInstance = new HungrySingleton();

    private HungrySingleton(){
    }

    public static synchronized HungrySingleton getInstance(){
        return uniqueInstance;
    }
}
