package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;

public class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
    public C c;

    public ThreeTuple() {

    }

    public ThreeTuple(A a, B b, C c) {
        super(a, b);
        this.c = c;
    }

    public static <A, B, C, DATA extends ThreeTuple<A, B, C>> List<C> cList(List<DATA> threeTuples) {
        List<C> data = new ArrayList<>(threeTuples.size());

        for (ThreeTuple<A, B, C> threeTuple : threeTuples) {
            data.add(threeTuple.c);
        }

        return data;
    }
}
