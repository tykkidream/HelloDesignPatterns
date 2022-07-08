package hello.designpatterns.batch.list;

import hello.designpatterns.batch.function.TeConsumer;
import hello.designpatterns.batch.tuple.ThreeTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.*;

public class ListUtil {
	private static final Logger logger = LoggerFactory.getLogger(ListUtil.class);

	public static <T, P> List<P> convertWithLinkedList(List<T> datas, Function<T, P> function) {
		if (datas == null || datas.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List list = new LinkedList();

		for (T data : datas) {
			if (data == null) {
				continue;
			}

			P newData = null;

			try {
				newData = function.apply(data);
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
			}

			if (newData != null) {
				list.add(newData);
			}
		}

		return list;
	}

	public static <T, P> List<P> filterWithLinkedList(List<T> datas, Predicate<T> predicate) {
		if (datas == null || datas.isEmpty()) {
			return Collections.EMPTY_LIST;
		}

		List list = new LinkedList();

		for (T data : datas) {
			if (data == null) {
				continue;
			}

			boolean test;

			try {
				test = predicate.test(data);
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
				test = false;
			}

			if (test) {
				list.add(data);
			}
		}

		return list;
	}

	public static <T> void iterate(List<T> datas, BiConsumer<T, Iterator<T>> biConsumer) {
		Iterator<T> iterator = datas.iterator();

		while (iterator.hasNext()) {
			T data = iterator.next();

			if (data == null) {
				continue;
			}

			try {
				biConsumer.accept(data, iterator);
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
			}
		}
	}

	public static <T> void iterate(List<T> datas, BiPredicate<T, Iterator<T>> biPredicate) {
		Iterator<T> iterator = datas.iterator();

		while (iterator.hasNext()) {
			T data = iterator.next();

			if (data == null) {
				continue;
			}

			try {
				boolean test = biPredicate.test(data, iterator);
				if (!test) {
					return;
				}
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
			}
		}
	}

	public static <T, R> R iterateResult(List<T> datas, BiFunction<T, Iterator<T>, R> biFunction) {
		Iterator<T> iterator = datas.iterator();

		while (iterator.hasNext()) {
			T data = iterator.next();

			if (data == null) {
				continue;
			}

			try {
				R result = biFunction.apply(data, iterator);
				if (result != null) {
					return result;
				}
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
			}
		}

		return null;
	}

	public static <T> void iterateRemove(List<T> datas, Predicate<T> predicate, BiConsumer<T, Throwable> throwableConsumer) {
		iterateRemove(datas, predicate, throwableConsumer, null);
	}

	public static <T> List<T> iterateRemove(List<T> datas, Predicate<T> predicate, BiConsumer<T, Throwable> throwableConsumer, List<T> removeList) {
		Iterator<T> iterator = datas.iterator();

		while (iterator.hasNext()) {
			T data = iterator.next();

			if (data == null) {
				iterator.remove();
				continue;
			}

			try {
				if (!predicate.test(data)) {
					iterator.remove();

					if (removeList != null) {
						removeList.add(data);
					}
				}
			} catch (Throwable throwable) {
				iterator.remove();

				if (removeList != null) {
					removeList.add(data);
				}

				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}

				try {
					if (throwableConsumer != null) {
						throwableConsumer.accept(data, throwable);
					}
				} catch (Throwable __throwable) {
					if (logger.isErrorEnabled()) {
						logger.error(__throwable.getMessage(), __throwable);
					}
				}
			}
		}

		return removeList;
	}

	public static <A, E, D> List<ThreeTuple<A, E, D>> leftJoin(WrapList<E, A> leftList, WrapList<D, A> rightList) {
		return leftJoin(leftList, rightList, new LinkedList<>());
	}

	public static <A, E, D> List<ThreeTuple<A, E, D>> leftJoin(WrapList<E, A> leftList, WrapList<D, A> rightList, List<ThreeTuple<A, E, D>> result) {
		leftJoin(leftList, rightList, (o1, o2) -> o1.equals(o2) ? 0 : 1, (a, e, d) -> result.add(new ThreeTuple<>(a, e, d)));
		return result;
	}

	public static <A, E, D> void leftJoin(WrapList<E, A> leftList, WrapList<D, A> rightList, BiConsumer<E, D> attributeSetter) {
		leftJoin(leftList, rightList, (o1, o2) -> o1.equals(o2) ? 0 : 1, (a,e,d) -> attributeSetter.accept(e,d));
	}

	public static <A, E, D> void leftJoin(WrapList<E, A> leftList, WrapList<D, A> rightList, Comparator<A> comparator, TeConsumer<A, E, D> attributeSetter) {
		if (leftList == null || leftList.isEmpty() || rightList == null || rightList.isEmpty() || comparator == null) {
			return;
		}

		WrapList<E, A>.ListItr leftIterator = leftList.iterator();
		WrapList<D, A>.ListItr rightIterator = rightList.iterator();

		A rightItemKey = null;

		A:
		while (leftIterator.hasNext()) {

			A leftItemKey = leftIterator.next();

			E leftItemRoot = leftIterator.root();

			B:
			if (rightItemKey != null) {
				int compare = comparator.compare(leftItemKey, rightItemKey);

				if (compare < 0) {
					continue A;
				} else if (compare > 0) {
					break B;
				} else {
					D rightItemRoot = rightIterator.root();

					attributeSetter.accept(leftItemKey, leftItemRoot, rightItemRoot);
					rightIterator.markRingStartingPoint();

					rightItemKey = null;
					continue A;
				}
			}

			C:
			while (rightIterator.hasNext()) {
				rightItemKey = rightIterator.next();

				int compare = comparator.compare(leftItemKey, rightItemKey);

				if (compare < 0) {
					continue A;
				} else if (compare > 0) {
					continue C;
				} else {
					D rightItemRoot = rightIterator.root();

					attributeSetter.accept(leftItemKey, leftItemRoot, rightItemRoot);

					break;
				}
			}

			rightIterator.markRingStartingPoint();
		}
	}

	public static <A, E, D> void leftJoin(List<E> leftList, List<D> rightList,
										  Function<E, A> leftGetter, Function<D, A> rightGetter,
										  BiConsumer<E, D> leftSetter, Comparator<A> comparator) {
		if (leftList == null || leftList.isEmpty()
				||rightList == null || rightList.isEmpty()
				|| leftGetter == null || rightGetter == null
				|| leftSetter == null || comparator == null) {
			return;
		}

		leftJoin(leftList, rightList, (a, b) -> {
			A k1 = leftGetter.apply(a);
			A k2 = rightGetter.apply(b);

			return comparator.compare(k1, k2);
		}, leftSetter);
	}

	public static <E, D> void leftJoin(List<E> leftList, List<D> rightList, DiffComparator<E, D> comparator, BiConsumer<E, D> leftSetter) {
		if (leftList == null || leftList.isEmpty() || rightList == null || rightList.isEmpty() || comparator == null) {
			return;
		}

		Iterator<E> leftIterator = leftList.iterator();

		ForwardIterator<D> rightIterator = new ForwardIterator<>(rightList);

		D rightItem = null;

		A:
		while (leftIterator.hasNext()) {

			E leftItem = leftIterator.next();

			B:
			if (rightItem != null) {
				int compare = comparator.compare(leftItem, rightItem);

				if (compare < 0) {
					continue A;
				} else if (compare > 0) {
					break B;
				} else {
					leftSetter.accept(leftItem, rightItem);
					rightIterator.markRingStartingPoint();
					rightItem = null;
					continue A;
				}
			}

			C:
			while (rightIterator.hasNext()) {
				rightItem = rightIterator.next();

				int compare = comparator.compare(leftItem, rightItem);

				if (compare < 0) {
					continue A;
				} else if (compare > 0) {
					continue C;
				} else {
					leftSetter.accept(leftItem, rightItem);
					break C;
				}
			}

			rightIterator.markRingStartingPoint();
		}
	}
}
