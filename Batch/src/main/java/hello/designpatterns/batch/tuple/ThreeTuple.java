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
        return cList(threeTuples, new ArrayList<>(threeTuples.size()));
    }

    public static <A, B, C, DATA extends ThreeTuple<A, B, C>> List<C> cList(List<DATA> threeTuples, List<C> list) {
        for (ThreeTuple<A, B, C> threeTuple : threeTuples) {
            list.add(threeTuple.c);
        }

        return list;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        if (c != null) {
            this.c = c;
        }
    }
}
