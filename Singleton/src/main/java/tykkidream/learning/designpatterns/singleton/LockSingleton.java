package tykkidream.learning.designpatterns.singleton;

/**
 * Created by Tykkidream on 2015/10/8.
 */
public class LockSingleton {
    private static volatile LockSingleton uniqueInstance;

    private LockSingleton(){
    }

    public static synchronized LockSingleton getInstance(){
        if(uniqueInstance == null){
            synchronized(LockSingleton.class){
                if(uniqueInstance == null){
                    uniqueInstance = new LockSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
