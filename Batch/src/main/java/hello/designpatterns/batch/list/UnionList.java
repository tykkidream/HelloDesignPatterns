package hello.designpatterns.batch.list;

import java.util.*;

/**
 * 可以将多个 List 联合成一个 List 来使用，实际还是各自分开的 List 。
 * @param <K>
 * @param <D>
 */
public class UnionList<K, D> implements List<D>, Cloneable, java.io.Serializable {
    private static abstract class UnionListData {

    }

    private static abstract class UnionListHashMapData {

    }
    private static abstract class UnionListLinkedListData {

    }

    public static <D> UnionList<Object, D> union(List<D>... lists) {
        UnionList<Object, D> list = new UnionList<>(lists.length);
        for (List<D> d : lists) {
            list.put(d, d);
        }
        return list;
    }


    private Map<K, List<D>> allData;

    private List<D> lastData;

    public UnionList(int size) {
        allData = new LinkedHashMap<>(size);
        lastData = EmptyList.EMPTY_LIST;
    }

    private void initLastData() {
        if (allData == null) {
            return;
        }
        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            lastData = entry.getValue();
        }
    }

    public void put(K key, List<D> data) {
        if (key == null) {
            throw new IllegalArgumentException("参数 key 不能为 null！");
        }
        if (data == null) {
            throw new IllegalArgumentException("参数 data 不能为 null！");
        }

        if (allData.containsKey(key)) {
            throw new RuntimeException();
        }

        allData.put(key, data);

        lastData = data;
    }

    public void putIgnore(K key, List<D> data) {
        if (key == null || data == null || data.isEmpty()) {
            return;
        }

        if (allData.containsKey(key)) {
            return;
        }

        allData.put(key, data);

        lastData = data;
    }

    public void del(K key, List<D> data) {
        if (key == null) {
            throw new IllegalArgumentException("参数 key 不能为 null！");
        }
        if (data == null) {
            throw new IllegalArgumentException("参数 data 不能为 null！");
        }

        if (allData.containsKey(key)) {
            throw new RuntimeException();
        }

        allData.remove(key, data);

        initLastData();
    }

    @Override
    public int size() {
        int size = 0;
        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            size = size + entry.getValue().size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            if (entry.getValue().contains(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<D> iterator() {
        return new ListItr();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(D d) {
        return lastData.add(d);
    }

    @Override
    public boolean remove(Object o) {
        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            boolean removed =  entry.getValue().remove(o);
            if (removed) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return lastData.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends D> c) {
        return lastData.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends D> c) {
        List<D> list = null;

        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            list = entry.getValue();
        }

        if (lastData != null) {
            return lastData.addAll(index, c);
        } else {
            return false;
        }
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return lastData.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return lastData.retainAll(c);
    }

    @Override
    public void clear() {
        allData.clear();
    }

    private static class PartData<K, D> {
        private int globalIndex;
        private int partIndex;
        private K partKey;
        private List<D> partList;

        private PartData(int globalIndex, int partIndex, K partKey, List<D> partList) {
            this.globalIndex = globalIndex;
            this.partIndex = partIndex;
            this.partKey = partKey;
            this.partList = partList;
        }

        private D get() {
            return partList.get(partIndex);
        }

        private D set(D newD) {
            return partList.set(partIndex, newD);
        }

        public void add(D newD) {
            partList.add(partIndex, newD);
        }

        public D remove() {
            return partList.remove(partIndex);
        }
    }

    private PartData<K, D> findIndexData(int globalIndex) {
        int partIndex = globalIndex;

        K partKey = null;

        List<D> partList = null;

        for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
            partKey = entry.getKey();
            partList = entry.getValue();

            if (partIndex < partList.size()) {
                break;
            }

            partIndex = partIndex - partList.size();
        }

        return new PartData<>(globalIndex, partIndex, partKey, partList);
    }

    @Override
    public D get(int index) {
        PartData<K, D> partData = findIndexData(index);

        if (partData.partList == null) {
            throw new RuntimeException();
        }

        return partData.get();
    }

    @Override
    public D set(int index, D element) {
        PartData<K, D> partData = findIndexData(index);

        if (partData.partList == null) {
            throw new RuntimeException();
        }

        return partData.set(element);
    }

    @Override
    public void add(int index, D element) {
        PartData<K, D> partData = findIndexData(index);

        if (partData.partList == null) {
            throw new RuntimeException();
        }

        partData.add(element);
    }

    @Override
    public D remove(int index) {
        PartData<K, D> partData = findIndexData(index);

        if (partData.partList == null) {
            throw new RuntimeException();
        }

        return partData.remove();
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<D> listIterator() {
        return new ListItr();
    }

    @Override
    public ListIterator<D> listIterator(int index) {
        return new ListItr();
    }

    @Override
    public List<D> subList(int fromIndex, int toIndex) {
        return null;
    }

    public class ListItr implements ListIterator<D> {
        private List<ListIterator<D>> iterators;

        private ListIterator<ListIterator<D>> iterator;

        private ListIterator<D> subIterator;

        private ListItr() {
            iterators = new LinkedList<>();
            for (Map.Entry<K, List<D>> entry : allData.entrySet()) {
                iterators.add(entry.getValue().listIterator());
            }
            iterator = iterators.listIterator();

            if (iterator.hasNext()) {
                subIterator = iterator.next();
            }
        }

        @Override
        public boolean hasNext() {
            if (subIterator == null) {
                return false;
            }

            boolean hasNext = subIterator.hasNext();

            if (hasNext) {
                return true;
            }

            if (iterator.hasNext()) {
                subIterator = iterator.next();
                return hasNext();
            }

            return false;
        }

        @Override
        public D next() {
            if (subIterator != null) {
                return subIterator.next();
            }
            throw new RuntimeException();
        }

        @Override
        public boolean hasPrevious() {
            if (subIterator == null) {
                return false;
            }

            boolean hasPrevious = subIterator.hasPrevious();

            if (hasPrevious) {
                return true;
            }

            if (iterator.hasPrevious()) {
                subIterator = iterator.previous();
                return hasPrevious();
            }

            return false;
        }

        @Override
        public D previous() {
            if (subIterator != null) {
                return subIterator.previous();
            }
            throw new RuntimeException();
        }

        @Override
        public int nextIndex() {
            if (subIterator != null) {
                return subIterator.nextIndex();
            }
            throw new RuntimeException();
        }

        @Override
        public int previousIndex() {
            if (subIterator != null) {
                return subIterator.previousIndex();
            }
            throw new RuntimeException();
        }

        @Override
        public void remove() {
            if (subIterator != null) {
                subIterator.remove();
            }
            throw new RuntimeException();
        }

        @Override
        public void set(D d) {
            if (subIterator != null) {
                subIterator.set(d);
            }
            throw new RuntimeException();
        }

        @Override
        public void add(D d) {
            if (subIterator != null) {
                subIterator.add(d);
            }
            throw new RuntimeException();
        }
    }
}
