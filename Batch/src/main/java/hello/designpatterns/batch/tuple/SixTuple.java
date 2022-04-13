package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;

public class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public F f;

    public SixTuple() {

    }

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        this.f = f;
    }

    public static <A, B, C, D, E, F, DATA extends SixTuple<A, B, C, D, E, F>> List<F> fList(List<DATA> sixTuples) {
        List<F> data = new ArrayList<>(sixTuples.size());

        for (DATA sixTuple : sixTuples) {
            data.add(sixTuple.f);
        }

        return data;
    }
}
