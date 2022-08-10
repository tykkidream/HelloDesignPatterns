package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TwoTuple<A, B> extends OneTuple<A> {
    public B b;

    public TwoTuple() {

    }

    public TwoTuple(A a, B b) {
        super(a);
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoTuple<?, ?> twoTuple = (TwoTuple<?, ?>) o;
        return Objects.equals(a, twoTuple.a) &&
                Objects.equals(b, twoTuple.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    public static <A, B, DATA extends TwoTuple<A, B>> List<B> bList(List<DATA> twoTuples) {
        return bList(twoTuples, new ArrayList<>(twoTuples.size()));
    }

    public static <A, B, DATA extends TwoTuple<A, B>> List<B> bList(List<DATA> twoTuples, List<B> list) {
        for (DATA twoTuple : twoTuples) {
            list.add(twoTuple.b);
        }

        return list;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        if (b != null) {
            this.b = b;
        }
    }
}
