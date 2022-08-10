package hello.designpatterns.batch.tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OneTuple<A>{
    public A a;

    public OneTuple() {

    }

    public OneTuple(A a) {
        this.a = a;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneTuple<?> oneTuple = (OneTuple<?>) o;
        return Objects.equals(a, oneTuple.a);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a);
    }

    public static <A, DATA extends OneTuple<A>> List<A> aList(List<DATA> oneTuples) {
        return aList(oneTuples, new ArrayList<>(oneTuples.size()));
    }
    public static <A, DATA extends OneTuple<A>> List<A> aList(List<DATA> oneTuples, List<A> list) {
        for (DATA oneTuple : oneTuples) {
            list.add(oneTuple.a);
        }

        return list;
    }

    public A get() {
        return getA();
    }

    public void set(A a) {
        setA(a);
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        if (a != null) {
            this.a = a;
        }
    }
}
