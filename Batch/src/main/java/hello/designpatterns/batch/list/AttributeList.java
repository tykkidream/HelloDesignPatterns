package hello.designpatterns.batch.list;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class AttributeList<E, A> extends AbstractList<A> implements List<A>, Cloneable, java.io.Serializable {

	private List<E> list;

	private Function<E, A> attributeGetter;

	private BiConsumer<E, A> attributeSetter;

	public AttributeList(List<E> list, Function<E, A> attributeGetter, BiConsumer<E, A> attributeSetter) {
		this.list = list;
		this.attributeGetter = attributeGetter;
		this.attributeSetter = attributeSetter;
	}

	public static <E, A> AttributeList<E, A> build(List<E> list, Function<E, A> attributeFunction, BiConsumer<E, A> attributeSetter) {
		return new AttributeList<>(list, attributeFunction, attributeSetter);
	}

	public <B> AttributeList<E, B> attributeMode(Function<E, B> attributeFunction, BiConsumer<E, B> attributeSetter) {
		return new AttributeList<>(list, attributeFunction, attributeSetter);
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
		E item = list.get(index);

		A old = attributeGetter.apply(item);

		attributeSetter.accept(item, element);

		return old;
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
	public ListItr iterator() {
		return new ListItr(0, this.attributeGetter, this.attributeSetter);
	}


	public class ListItr implements ListIterator<A> {
		private int cursor = 0;

		private int ringStartingPoint = -2;

		private int lastRet = -1;

		private int expectedModCount = modCount;

		private Function<E, A> attributeGetter;

		private BiConsumer<E, A> attributeSetter;

		private ListItr(int index, Function<E, A> attributeGetter, BiConsumer<E, A> attributeSetter) {
			this.cursor = index;
			this.attributeGetter = attributeGetter;
			this.attributeSetter = attributeSetter;
		}

		public void markRingStartingPoint() {
			if (cursor == size()) {
				this.cursor = 0;
				this.ringStartingPoint = 0;
			} else {
				this.ringStartingPoint = this.cursor;
			}
		}

		public boolean hasNext() {
			return (ringStartingPoint == -2 && cursor != size()) || ringStartingPoint > -1;
		}

		public A next() {
			if (ringStartingPoint == -1) {
				throw new NoSuchElementException();
			}

			checkForComodification();
			try {
				int i = cursor;
				A next = get(i, attributeGetter);
				lastRet = i;
				cursor = i + 1;

				if (ringStartingPoint > -1) {
					if (cursor == size()) {
						cursor = 0;
					} else if (cursor == ringStartingPoint){
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
				AttributeList.this.remove(lastRet);
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

		public A previous() {
			if (ringStartingPoint == -1) {
				throw new NoSuchElementException();
			}

			checkForComodification();
			try {
				int i = cursor - 1;
				A previous = get(i, attributeGetter);
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

		public void set(A e) {
			if (lastRet < 0)
				throw new IllegalStateException();
			checkForComodification();

			try {
				AttributeList.this.set(lastRet, e, this.attributeGetter, this.attributeSetter);
				expectedModCount = modCount;
			} catch (IndexOutOfBoundsException ex) {
				throw new ConcurrentModificationException();
			}
		}

		public void add(A e) {
			throw new UnsupportedOperationException();
		}
	}
}