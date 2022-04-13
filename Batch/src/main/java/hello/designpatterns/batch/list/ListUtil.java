package hello.designpatterns.batch.list;

import hello.designpatterns.batch.tuple.ThreeTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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

	public static <T> void iterateRemove(List<T> datas, Predicate<T> predicate) {
		Iterator<T> iterator = datas.iterator();

		while (iterator.hasNext()) {
			T data = iterator.next();

			if (data == null) {
				iterator.remove();
				continue;
			}

			boolean test;

			try {
				test = predicate.test(data);
			} catch (Throwable throwable) {
				if (logger.isErrorEnabled()) {
					logger.error(throwable.getMessage(), throwable);
				}
				test = true;
			}

			if (test) {
				iterator.remove();
			}
		}
	}

	public static <A, E, D> List<ThreeTuple<A, E, D>> leftJoin(AttributeList<E, A> leftList, AttributeList<D, A> rightList) {
		return leftJoin(leftList, rightList, new LinkedList<>());
	}

	public static <A, E, D> List<ThreeTuple<A, E, D>> leftJoin(AttributeList<E, A> leftList, AttributeList<D, A> rightList, List<ThreeTuple<A, E, D>> result) {
		if (leftList == null || leftList.isEmpty() || rightList == null || rightList.isEmpty()) {
			return result;
		}

		AttributeList<E, A>.ListItr leftIterator = leftList.iterator();
		AttributeList<D, A>.ListItr rightIterator = rightList.iterator();

		int rightIndex = 0;

		while (leftIterator.hasNext()) {

			A leftItemKey = leftIterator.next();

			E leftItemRoot = leftIterator.root();

			D rightItemRoot = null;

			R:
			{
				while (rightIterator.hasNext()) {

					A rightItemKey = rightIterator.next();

					if (leftItemKey.equals(rightItemKey)) {
						rightItemRoot = rightIterator.root();
						rightIndex = rightIterator.nextIndex();
						break;
					}
				}


			}



			result.add(new ThreeTuple<>(leftItemKey, leftItemRoot, rightItemRoot));
		}

		return result;
	}
}
