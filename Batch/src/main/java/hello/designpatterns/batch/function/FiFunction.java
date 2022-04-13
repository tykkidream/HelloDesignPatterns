package hello.designpatterns.batch.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Five into the system
 *
 * @param <T>
 * @param <U>
 * @param <E>
 * @param <Q>
 * @param <R>
 */
@FunctionalInterface
public interface FiFunction<T, U, E, Q, F, R> {

    R apply(T t, U u, E e, Q q, F f);

    default <V> FiFunction<T, U, E, Q, F, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, E e, Q q, F f) -> after.apply(apply(t, u, e, q, f));
    }
}
