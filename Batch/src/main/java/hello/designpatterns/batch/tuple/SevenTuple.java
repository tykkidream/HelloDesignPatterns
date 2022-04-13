package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;

public class SevenTuple<A, B, C, D, E, F, G> extends SixTuple<A, B, C, D, E, F> {
    public G g;

    public SevenTuple() {

    }

    public SevenTuple(A a, B b, C c, D d, E e, F f, G g) {
        super(a, b, c, d, e, f);
        this.g = g;
    }

    public static <A, B, C, D, E, F, G, DATA extends SevenTuple<A, B, C, D, E, F, G>> List<G> gList(List<DATA> sevenTuples) {
        List<G> data = new ArrayList<>(sevenTuples.size());

        for (DATA sevenTuple : sevenTuples) {
            data.add(sevenTuple.g);
        }

        return data;
    }
}
