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

    public static <A, B, DATA extends TwoTuple<A, B>> List<A> aList(List<DATA> twoTuples) {
        List<A> data = new ArrayList<>(twoTuples.size());

        for (DATA twoTuple : twoTuples) {
            data.add(twoTuple.a);
        }

        return data;
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
