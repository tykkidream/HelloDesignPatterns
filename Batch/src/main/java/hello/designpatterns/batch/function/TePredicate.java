package hello.designpatterns.batch.function;

import java.util.Objects;

@FunctionalInterface
public interface TePredicate<T, U, E> {

    boolean test(T t, U u, E e);

    default TePredicate<T, U, E> and(TePredicate<? super T, ? super U, ? super E> other) {
        Objects.requireNonNull(other);
        return (T t, U u, E e) -> test(t, u, e) && other.test(t, u, e);
    }

    default TePredicate<T, U, E> or(TePredicate<? super T, ? super U, ? super E> other) {
        Objects.requireNonNull(other);
        return (T t, U u, E e) -> test(t, u, e) || other.test(t, u, e);
    }
}
