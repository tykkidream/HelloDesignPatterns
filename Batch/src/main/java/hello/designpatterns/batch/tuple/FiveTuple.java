package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;

public class FiveTuple<A, B, C, D, E> extends FourTuple<A, B, C, D> {
    public E e;

    public FiveTuple() {

    }

    public FiveTuple(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        this.e = e;
    }

    public static <A, B, C, D, E, DATA extends FiveTuple<A, B, C, D, E>> List<E> eList(List<DATA> fiveTuples) {
        List<E> data = new ArrayList<>(fiveTuples.size());

        for (DATA fiveTuple : fiveTuples) {
            data.add(fiveTuple.e);
        }

        return data;
    }

    public E getE() {
        return e;
    }

    public void setE(E e) {
        if (e != null) {
            this.e = e;
        }
    }
}
