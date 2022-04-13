package hello.designpatterns.batch.function;

import java.util.Objects;
import java.util.function.Function;

/**
 * Ternary
 *
 * @param <T>
 * @param <U>
 * @param <E>
 * @param <R>
 */
@FunctionalInterface
public interface TeFunction<T, U, E, R> {

    R apply(T t, U u, E e);

    default <V> TeFunction<T, U, E, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u, E e) -> after.apply(apply(t, u, e));
    }
}
