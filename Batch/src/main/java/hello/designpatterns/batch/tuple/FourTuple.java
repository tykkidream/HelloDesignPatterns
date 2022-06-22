package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;

public class FourTuple<A, B, C, D> extends ThreeTuple<A, B, C> {
    public D d;

    public FourTuple() {

    }

    public FourTuple(A a, B b, C c, D d) {
        super(a, b, c);
        this.d = d;
    }

    public static <A, B, C, D, DATA extends FourTuple<A, B, C, D>> List<D> dList(List<DATA> fourTuples) {
        List<D> data = new ArrayList<>(fourTuples.size());

        for (DATA threeTuple : fourTuples) {
            data.add(threeTuple.d);
        }

        return data;
    }

    public D getD() {
        return d;
    }

    public void setD(D d) {
        if (d != null) {
            this.d = d;
        }
    }
}
