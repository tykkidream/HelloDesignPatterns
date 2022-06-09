package hello.designpatterns.batch.list;

@FunctionalInterface
public interface DiffComparator<A, B> {
    int compare(A o1, B o2);
}
