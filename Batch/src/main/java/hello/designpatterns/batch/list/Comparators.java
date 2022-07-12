package hello.designpatterns.batch.list;

import java.util.Comparator;

public class Comparators {
    public static <T> Comparator<T> cascadeComparator(Comparator<T> ... cs) {
        return (o1, o2) -> {
            for (Comparator<T> c : cs) {
                int compare = c.compare(o1, o2);
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        };
    }

    public static <A, B> DiffComparator<A, B> cascadeDiffComparator(DiffComparator<A, B> ... cs) {
        return (o1, o2) -> {
            for (DiffComparator<A, B> c : cs) {
                int compare = c.compare(o1, o2);
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        };
    }
}
