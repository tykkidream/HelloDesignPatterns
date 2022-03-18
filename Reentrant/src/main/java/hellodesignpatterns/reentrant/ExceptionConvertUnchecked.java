package hellodesignpatterns.reentrant;

final class ExceptionConvertUnchecked {
    /**
     * 这个方法会将检查型异常转换为非检查型异常
     *
     *
     *
     * @param t
     * @param <T> 这里的 T 会被视为 unchecked exception
     * @param <R>
     * @return
     * @throws T
     */
    static <T extends Exception, R> R throwException(Throwable t) throws T {
        throw (T) t;
    }
}
