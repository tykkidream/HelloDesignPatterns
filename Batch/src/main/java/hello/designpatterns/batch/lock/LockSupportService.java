package hello.designpatterns.batch.lock;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;

public interface LockSupportService {
    /* --------------------------------------------------  单次锁 -----------------------------------------------------*/
    Boolean doLock(String key, String value, int second);

    Boolean doLockOneMinute(String key, String value);

    Boolean doLockOneHour(String key, String value);

    Boolean doLockOneMinute(String key);

    Boolean doLockOneHour(String key);

    <T> boolean doLockOneHour(T data, Function<T, String> keyFunction, Function<T, String> valueFunction, BiConsumer<T, Boolean> resultFunction);

    /* --------------------------------------------------  批量锁 -----------------------------------------------------*/

    /**
     * 批量加锁
     * =================================================================================================================
     *
     * @param datas 原始数据
     * @param keyFunction 从原始数据上获得的加锁的 key 的方式
     * @param valueFunction 从原始数据上获得的加锁的 value 的方式
     * @param resultFunction 处理加锁结果（true：加锁成功，false：加锁失败）的方式
     * @param lockInfoFunction 处理加锁信息（LockInfo中包含锁的key、结果等信息）的方式
     * @param second 加锁时间，单位秒
     * @param <T>
     * @return
     */
    <T> List<LockInfo<T>> doLock(List<T> datas, Function<T, String> keyFunction, Function<T, String> valueFunction, BiConsumer<T, Boolean> resultFunction,
			BiConsumer<T, LockInfo<T>> lockInfoFunction, int second);

    <T> void doLockOneMinute(List<T> datas, Function<T, String> keyFunction, Function<T, String> valueFunction, BiConsumer<T, Boolean> resultFunction,
			BiConsumer<T, LockInfo<T>> lockInfoFunction);

    /**
     * TODO 需要考虑不返回 List
     *
     * @param datas
     * @param keyFunction
     * @param valueFunction
     * @param resultFunction
     * @param lockInfoFunction
     * @param <T>
     * @return
     */
    <T> List<LockInfo<T>> doLockOneHour(List<T> datas, Function<T, String> keyFunction, Function<T, String> valueFunction,
			BiConsumer<T, Boolean> resultFunction, BiConsumer<T, LockInfo<T>> lockInfoFunction);

    List<LockInfo<String>> doLockOneHour(List<String> datas);

    /* --------------------------------------------------   续期  -----------------------------------------------------*/
    void expireOneMinute(String key);

    void expireOneHour(String key);

    /* --------------------------------------------------   解锁  -----------------------------------------------------*/
    void unLock(String key);

    void unLock(Collection<String> keys);

    void unLockInfo(Collection<LockInfo> lockInfos);

    <T> void unLockInfo(Collection<T> datas, Function<T, LockInfo<T>> lockInfoFunction);

    /* -------------------------------------------------   是否存在  ---------------------------------------------------*/
    <T> List<Boolean> exists(List<T> datas, Function<T, String> keyFunction, BiConsumer<T, Boolean> resultFunction);

    <T> Boolean exists(T data, Function<T, String> keyFunction, BiConsumer<T, Boolean> resultFunction);

    /* -------------------------------------------------  锁事务处理 ---------------------------------------------------*/
    <T> T lockOneMinuteTransaction(String key, Callable<T> callable, BiPredicate<String, Integer> lockFailurePredicate);

    void lockOneMinuteTransaction(String key, Runnable runnable, BiPredicate<String, Integer> lockFailurePredicate);

    <T> T lockOneHourTransaction(String key, Callable<T> callable, BiPredicate<String, Integer> lockFailurePredicate);

    void lockOneHourTransaction(String key, Runnable runnable, BiPredicate<String, Integer> lockFailurePredicate);
}
