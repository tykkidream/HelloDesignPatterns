package hello.designpatterns.batch.list;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class WrapList<E, A> extends AbstractList<A> implements List<A>, Cloneable, java.io.Serializable {

	private List<E> list;

	private Function<E, A> attributeGetter;

	private BiConsumer<E, A> attributeSetter;

	public WrapList(List<E> list, Function<E, A> attributeGetter, BiConsumer<E, A> attributeSetter) {
		this.list = list;
		this.attributeGetter = attributeGetter;
		this.attributeSetter = attributeSetter;
	}

	public static <E> WrapList<E, E> wrap(List<E> list) {
		return new WrapList<>(list,  e -> e, null);
	}

	public static <E, A> WrapList<E, A> wrap(List<E> list, Function<E, A> attributeFunction) {
		return new WrapList<>(list, attributeFunction, null);
	}

	public static <E, A> WrapList<E, A> wrap(List<E> list, Function<E, A> attributeFunction, BiConsumer<E, A> attributeSetter) {
		return new WrapList<>(list, attributeFunction, attributeSetter);
	}

	public <B> WrapList<E, B> attributeMode(Function<E, B> attributeFunction, BiConsumer<E, B> attributeSetter) {
		return new WrapList<>(list, attributeFunction, attributeSetter);
	}


	@Override
	public A get(int index) {
		return get(index, this.attributeGetter);
	}

	public A get(int index, Function<E, A> attributeGetter) {
		E item = list.get(index);

		A attribute = attributeGetter.apply(item);

		return attribute;
	}

	public E getRoot(int index) {
		return list.get(index);
	}

	@Override
	public A set(int index, A element) {
		return set(index, element, this.attributeGetter, this.attributeSetter);
	}

	public A set(int index, A element, Function<E, A> attributeGetter, BiConsumer<E, A> attributeSetter) {
		if (attributeSetter == null) {
			throw new UnsupportedOperationException("非法操作，不存在 setter 接口实例，不可以设置新值！");
		} else {
			E item = list.get(index);

			A old = attributeGetter.apply(item);

			attributeSetter.accept(item, element);

			return old;
		}
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public A remove(int index) {
		E remove = list.remove(index);
		return attributeGetter.apply(remove);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public void sort(Comparator<? super A> c) {
		Objects.requireNonNull(c);
		list.sort((o1, o2) -> c.compare(this.attributeGetter.apply(o1), this.attributeGetter.apply(o2)));
	}

	@Override
	public ListItr iterator() {
		return new ListItr();
	}


	public class ListItr extends ForwardIterator<A> {
		private ListItr() {
			super(WrapList.this);
		}

		public E root() {
			return list.get(lastRet());
		}
	}
}
