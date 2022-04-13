package hello.designpatterns.batch.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Quaternary
 *
 * @param <T>
 * @param <U>
 * @param <E>
 * @param <Q>
 * @param <R>
 */
@FunctionalInterface
public interface QuFunction<T, U, E, Q, R> {

    R apply(T t, U u, E e, Q q);

    default <V> QuFunction<T, U, E, Q, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, E e, Q q) -> after.apply(apply(t, u, e, q));
    }
}
