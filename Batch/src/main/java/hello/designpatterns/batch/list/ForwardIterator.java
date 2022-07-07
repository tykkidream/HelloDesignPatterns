package hello.designpatterns.batch.list;

import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ForwardIterator<E> implements ListIterator<E> {
    private List<E> list;

    private transient int modCount = 0;

    private int cursor = 0;

    private int ringStartingPoint = -2;

    private int lastRet = -1;

    private int expectedModCount = modCount;

    private Function<E, E> attributeGetter;

    private Consumer<E> attributeSetter;

    public ForwardIterator(List<E> list) {
        this.list = list;
    }

    private int size() {
        return list.size();
    }

    public void markRingStartingPoint() {
        if (cursor == size()) {
            this.cursor = size() -1;
        }

        this.ringStartingPoint = this.cursor;
    }

    public boolean hasNext() {
        return (ringStartingPoint == -2 && cursor < size()) || ringStartingPoint > -1;
    }

    public E next() {
        if (ringStartingPoint == -1) {
            throw new NoSuchElementException();
        }

        checkForComodification();
        try {
            int i = cursor;
            E next = list.get(i);
            lastRet = i;
            cursor = i + 1;

            if (ringStartingPoint > -1) {
                if (cursor == size()) {
                    cursor = 0;
                }

                if (cursor == ringStartingPoint) {
                    ringStartingPoint = -1;
                }
            }

            return next;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }
    }

    public E root() {
        return list.get(lastRet);
    }

    public void remove() {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            list.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException e) {
            throw new ConcurrentModificationException();
        }
    }

    final void checkForComodification() {
        if (modCount != expectedModCount)
            throw new ConcurrentModificationException();
    }

    public boolean hasPrevious() {
        return (ringStartingPoint == -2 && cursor != 0) || ringStartingPoint > -1;
    }

    public E previous() {
        if (ringStartingPoint == -1) {
            throw new NoSuchElementException();
        }

        checkForComodification();
        try {
            int i = cursor - 1;
            E previous = list.get(i);
            lastRet = cursor = i;

            if (ringStartingPoint > -1) {
                if (cursor == 0) {
                    cursor = size();
                } else if (cursor == ringStartingPoint) {
                    ringStartingPoint = -1;
                }
            }

            return previous;
        } catch (IndexOutOfBoundsException e) {
            checkForComodification();
            throw new NoSuchElementException();
        }

    }

    public int nextIndex() {
        return cursor;
    }

    public int previousIndex() {
        return cursor - 1;
    }

    public void set(E e) {
        if (lastRet < 0)
            throw new IllegalStateException();
        checkForComodification();

        try {
            list.set(lastRet, e);
            expectedModCount = modCount;
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }

    public void add(E e) {
        throw new UnsupportedOperationException();
    }
}
