package hello.designpatterns.batch.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class AttributeListTest {

	public static class Person {
		private Long id;

		private String name;

		public Person() {

		}

		public Person(Long id, String name) {
			setId(id);
			setName(name);
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			if (id != null) {
				this.id = id;
			}
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			if (name != null) {
				this.name = name;
			}
		}
	}

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

		for (Long obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	public void test2() {
		AttributeList<Person, String> list = AttributeList.build(persons, Person::getName, Person::setName);

		for (String obj : list) {
			System.out.println(obj);
		}
	}


	@Test
	public void test3() {
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, String> list = AttributeList.build(persons, Person::getName, Person::setName);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
		AttributeList<Person, Long> list = AttributeList.build(persons, Person::getId, Person::setId);

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
}
