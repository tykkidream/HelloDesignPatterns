package hello.designpatterns.batch.list;

import com.alibaba.fastjson.JSON;
import hello.designpatterns.batch.tuple.TwoTuple;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class WrapListTest {

	private List<Person> persons;

	@Before
	public void before() {
		persons = new ArrayList<>();

		persons.add(new Person(3L, "ali"));
		persons.add(new Person(2L, "tengxun"));
		persons.add(new Person(4L, "huawei"));
		persons.add(new Person(1L, "baidu"));
	}

	/**
	 * 应用于 java 循环语法的测试
	 */
	@Test
	public void test1() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		long[] expects = {3, 2, 4, 1};
		int i = 0;

		for (Long obj : list) {
			System.out.println(obj);
			assertEquals(Long.valueOf(expects[i]), obj);
			i++;
		}
	}


	/**
	 * 应用于 java 循环语法的测试
	 */
	@Test
	public void test2() {
		WrapList<Person, String> list = WrapList.wrap(persons, Person::getName, Person::setName);

		String[] expects = {"ali", "tengxun", "huawei", "baidu"};
		int i = 0;

		for (String obj : list) {
			System.out.println(obj);
			assertEquals(expects[i], obj);
			i++;
		}
	}

	/**
	 * 使用迭代器的单向循环测试
	 * 迭代时，通过 set 设值
	 */
	@Test
	public void test3() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		{
			long[] expects = {3, 2, 4, 1};
			int i = 0;

			ListIterator<Long> iterator = list.iterator();

			while (iterator.hasNext()) {
				// 测试迭代器
				Long next = iterator.next();

				assertEquals(Long.valueOf(expects[i]), next);
				i++;

				System.out.print(next);
				System.out.print(" => ");

				next = 100 + next;

				System.out.println(next);
				// 测试 set
				iterator.set(next);
			}
		}

		System.out.println("======================================================");

		{
			long[] expects = {103, 102, 104, 101};
			int i = 0;

			for (Object obj : list) {
				System.out.println(obj);

				assertEquals(Long.valueOf(expects[i]), obj);
				i++;
			}
		}
	}

	/**
	 * 使用迭代器的单向循环测试
	 * 迭代时，通过 set 设值
	 */
	@Test
	public void test4() {
		WrapList<Person, String> list = WrapList.wrap(persons, Person::getName, Person::setName);

		ListIterator<String> iterator = list.iterator();

		{
			String[] expects = {"ali", "tengxun", "huawei", "baidu"};
			int i = 0;

			while (iterator.hasNext()) {
				String next = iterator.next();

				assertEquals(expects[i], next);
				i++;

				System.out.print(next);
				System.out.print(" => ");

				next = next + "-New";

				System.out.println(next);
				// 测试 set
				iterator.set(next);
			}
		}

		System.out.println("======================================================");

		{

			String[] expects = {"ali-New", "tengxun-New", "huawei-New", "baidu-New"};
			int i = 0;

			for (Object obj : list) {
				System.out.println(obj);

				assertEquals(expects[i], obj);
				i++;
			}
		}
	}

	@Test
	public void test5() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		for (Object obj : list) {
			System.out.println(obj);
		}

		System.out.println("======================================================");

		int i = 0;

		ListIterator<Long> iterator = list.iterator();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			i++;
			if (i % 2 == 0) {
				System.out.println("Remove " + next);
				iterator.remove();
			}
		}

		System.out.println("======================================================");

		for (Object obj : list) {
			System.out.println(obj);
		}

	}

	@Test
	public void test6() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);
		}

		System.out.println("=================================================================");

		Long next = iterator.next();

		System.out.println(next);
	}

	@Test
	public void test7() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		iterator.next();
		iterator.next();
		iterator.markRingStartingPoint();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);
		}

		System.out.println("=================================================================");

		Long next = iterator.next();

		System.out.println(next);
	}

	@Test
	public void test8() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		iterator.hasNext();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.next();
		iterator.hasNext();
		iterator.next();
		iterator.markRingStartingPoint();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);
		}

		System.out.println("=================================================================");

		try {
			Long next = iterator.next();
			System.out.println(next);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		System.out.println("=================================================================");

		iterator.markRingStartingPoint();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);
		}

		System.out.println("=================================================================");

		Long next = iterator.next();

		System.out.println(next);
	}

	@Test
	public void test9() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		iterator.next();
		iterator.next();
		iterator.markRingStartingPoint();

		int i = 0;

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);

			i++;

			if (i == 1) {
				System.out.println("=================================================================");

				iterator.markRingStartingPoint();
			}
		}

		System.out.println("=================================================================");

		try {
			Long next = iterator.next();
			System.out.println(next);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		System.out.println("=================================================================");

		iterator.markRingStartingPoint();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.println(next);
		}

		System.out.println("=================================================================");

		Long next = iterator.next();
		System.out.println(next);
	}

	@Test
	public void test10() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		Long previous = iterator.previous();

		System.out.println(previous);
	}

	@Test
	public void test11() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		Long previous = iterator.previous();

		System.out.println(previous);
	}

	@Test
	public void test12() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}

		iterator.previous();
		iterator.previous();
		iterator.markRingStartingPoint();

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		Long previous = iterator.previous();

		System.out.println(previous);
	}

	@Test
	public void test13() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		iterator.next();
		iterator.next();
		iterator.markRingStartingPoint();

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		iterator.markRingStartingPoint();

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		Long previous = iterator.previous();

		System.out.println(previous);
	}

	@Test
	public void test14() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		WrapList<Person, Long>.ListItr iterator = list.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}

		iterator.previous();
		iterator.previous();
		iterator.markRingStartingPoint();

		int i = 0;

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);

			i++;

			if (i == 1) {
				System.out.println("=================================================================");

				iterator.markRingStartingPoint();
			}
		}

		System.out.println("=================================================================");

		try {
			Long previous = iterator.previous();
			System.out.println(previous);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		System.out.println("=================================================================");

		iterator.markRingStartingPoint();

		while (iterator.hasPrevious()) {
			Long previous = iterator.previous();

			System.out.println(previous);
		}

		System.out.println("=================================================================");

		Long previous = iterator.previous();
		System.out.println(previous);
	}

	@Test
	public void test15() {
		WrapList<Person, TwoTuple<Long, String>> list = WrapList.wrap(persons, person ->
			new TwoTuple<Long, String>() {
				@Override
				public Long getA() {
					return person.getId();
				}

				@Override
				public void setA(Long o) {
					person.setId(o);
				}

				@Override
				public String getB() {
					return person.getName();
				}

				@Override
				public void setB(String o) {
					person.setName(o);
				}
			}
		);

		String f1 = JSON.toJSONString(persons);

		System.out.println("初始数据：" + f1);

		System.out.println("=================================================================");

		WrapList<Person, TwoTuple<Long, String>>.ListItr iterator = list.iterator();

		String f2 = JSON.toJSONString(list);

		System.out.println("包装数据：" + f2);

		System.out.println("=================================================================");

		while (iterator.hasNext()) {
			TwoTuple<Long, String> tuple = iterator.next();

			tuple.setA(tuple.getA() * 6);
			tuple.setB(tuple.getB() + "-new");
		}

		String f3 = JSON.toJSONString(list);

		System.out.println("更新数据：" + f3);

		System.out.println("=================================================================");

		String f4 = JSON.toJSONString(persons);

		System.out.println("最终数据：" + f4);
	}

	@Test
	public void test16() {
		WrapList<Person, TwoTuple<Long, String>> list = WrapList.wrap(persons, person ->
				new TwoTuple<Long, String>() {
					@Override
					public Long getA() {
						return person.getId();
					}

					@Override
					public void setA(Long o) {
						person.setId(o);
					}

					@Override
					public String getB() {
						return person.getName();
					}

					@Override
					public void setB(String o) {
						person.setName(o);
					}
				}
		);

		String f1 = JSON.toJSONString(persons);

		System.out.println("初始数据：" + f1);

		System.out.println("=================================================================");

		WrapList<Person, TwoTuple<Long, String>>.ListItr iterator = list.iterator();

		TwoTuple<Long, String> next = iterator.next();

		TwoTuple<Long, String> tuple = new TwoTuple<>();
		tuple.setA(4L);
		tuple.setB("<<abc>>");

		iterator.set(tuple);

		String f3 = JSON.toJSONString(list);

		System.out.println("更新数据：" + f3);

		System.out.println("=================================================================");
	}

	@Test
	public void test17() {
		WrapList<Person, TwoTuple<Long, String>> list = WrapList.wrap(persons, person ->
				new TwoTuple<Long, String>() {
					@Override
					public Long getA() {
						return person.getId();
					}

					@Override
					public void setA(Long o) {
						person.setId(o);
					}

					@Override
					public String getB() {
						return person.getName();
					}

					@Override
					public void setB(String o) {
						person.setName(o);
					}
				}
		);

		String f1 = JSON.toJSONString(persons);

		System.out.println("初始数据：" + f1);

		System.out.println("=================================================================");


		String f2 = JSON.toJSONString(list);

		System.out.println("包装数据：" + f2);

		System.out.println("=================================================================");

		// TODO 需要实现排序
		list.sort(new Comparator<TwoTuple<Long, String>>() {
			@Override
			public int compare(TwoTuple<Long, String> o1, TwoTuple<Long, String> o2) {
				return o1.getA().compareTo(o2.getA());
			}
		});

		String f3 = JSON.toJSONString(list);

		System.out.println("更新数据：" + f3);

		System.out.println("=================================================================");

		String f4 = JSON.toJSONString(persons);

		System.out.println("最终数据：" + f4);
	}

	@Test
	public void test18() {
		WrapList<Person, Long> list = WrapList.wrap(persons, Person::getId, Person::setId);

		for (Long obj : list) {
			System.out.println(obj);
		}

		list.sort(Long::compareTo);

		System.out.println("========================================");

		long[] expects = {1, 2, 3, 4};
		int i = 0;

		for (Long obj : list) {
			System.out.println(obj);
			assertEquals(Long.valueOf(expects[i]), obj);
			i++;
		}

	}
}
