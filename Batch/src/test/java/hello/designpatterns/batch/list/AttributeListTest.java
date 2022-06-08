package hello.designpatterns.batch.list;

import com.alibaba.fastjson.JSON;
import hello.designpatterns.batch.tuple.TwoTuple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AttributeListTest {

	private List<Person> persons;

	@Before
	public void before() {
		persons = new ArrayList<>();

		persons.add(new Person(1L, "ali"));
		persons.add(new Person(2L, "tengxun"));
		persons.add(new Person(3L, "huawei"));
		persons.add(new Person(4L, "baidu"));
	}


	@Test
	public void test1() {
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		for (Long obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	public void test2() {
		AttributeList<Person, String> list = AttributeList.wrap(persons, Person::getName, Person::setName);

		for (String obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	public void test3() {
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		ListIterator<Long> iterator = list.iterator();

		while (iterator.hasNext()) {
			Long next = iterator.next();

			System.out.print(next);
			System.out.print(" => ");

			next = 100 + next;

			System.out.println(next);
			iterator.set(next);
		}

		System.out.println("======================================================");

		for (Object obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	public void test4() {
		AttributeList<Person, String> list = AttributeList.wrap(persons, Person::getName, Person::setName);

		ListIterator<String> iterator = list.iterator();

		while (iterator.hasNext()) {
			String next = iterator.next();

			System.out.print(next);
			System.out.print(" => ");

			next = next + "-New";

			System.out.println(next);
			iterator.set(next);
		}

		System.out.println("======================================================");

		for (Object obj : list) {
			System.out.println(obj);
		}
	}

	@Test
	public void test5() {
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

		iterator.next();
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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, Long> list = AttributeList.wrap(persons, Person::getId, Person::setId);

		AttributeList<Person, Long>.ListItr iterator = list.iterator();

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
		AttributeList<Person, TwoTuple<Long, String>> list = AttributeList.wrap(persons, person ->
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

		AttributeList<Person, TwoTuple<Long, String>>.ListItr iterator = list.iterator();

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
		AttributeList<Person, TwoTuple<Long, String>> list = AttributeList.wrap(persons, person ->
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

		AttributeList<Person, TwoTuple<Long, String>>.ListItr iterator = list.iterator();

		TwoTuple<Long, String> next = iterator.next();

		TwoTuple<Long, String> tuple = new TwoTuple<>();
		tuple.setA(4L);
		tuple.setB("<<abc>>");

		iterator.set(tuple);

		String f3 = JSON.toJSONString(list);

		System.out.println("更新数据：" + f3);

		System.out.println("=================================================================");
	}
}
