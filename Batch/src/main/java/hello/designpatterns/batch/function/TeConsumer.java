package hello.designpatterns.batch.function;

import java.util.Objects;

@FunctionalInterface
public interface TeConsumer<T, U, E> {

    void accept(T t, U u, E e);

    default TeConsumer<T, U, E> andThen(TeConsumer<? super T, ? super U, ? super E> after) {
        Objects.requireNonNull(after);

        return (l, r, p) -> {
            accept(l, r, p);
            after.accept(l, r, p);
        };
    }
}
