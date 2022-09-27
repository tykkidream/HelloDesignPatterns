package hello.designpatterns.batch.list;

import com.alibaba.fastjson.JSON;
import hello.designpatterns.batch.tuple.ThreeTuple;
import hello.designpatterns.batch.tuple.TwoTuple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListUtilTest {
	private List<Person> persons1;
	private List<Person> persons2;

	@Before
	public void before() {
		persons1 = new ArrayList<>();

		persons1.add(new Person(1L, "ali"));
		persons1.add(new Person(2L, "tengxun"));
		persons1.add(new Person(3L, "huawei"));
		persons1.add(new Person(4L, "baidu"));

		persons2 = new ArrayList<>();

		persons2.add(new Person(4L, "百度"));
		persons2.add(new Person(1L, "阿里"));
		persons2.add(new Person(3L, "华为"));
		persons2.add(new Person(2L, "腾讯"));
	}

	@Test
	public void test1() {
		WrapList<Person, Long> list1 = WrapList.wrap(persons1, Person::getId, Person::setId);
		WrapList<Person, Long> list2 = WrapList.wrap(persons2, Person::getId, Person::setId);

/*		List<ThreeTuple<Long, Person, Person>> result = ListUtil.leftJoin(list1, list2);

		System.out.println(JSON.toJSONString(list1));
		System.out.println(JSON.toJSONString(persons1));
		System.out.println(JSON.toJSONString(list2));
		System.out.println(JSON.toJSONString(persons2));
		System.out.println(JSON.toJSONString(result));*/
	}

	@Test
	public void test2() {
		WrapList<Person, Long> list1 = WrapList.wrap(persons1, Person::getId, Person::setId);
		WrapList<Person, Long> list2 = WrapList.wrap(persons2, Person::getId, Person::setId);

		ListUtil.leftJoin(list1, list2, Person::setRelation);

		System.out.println(JSON.toJSONString(persons1));
	}

	@Test
	public void test3() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(2, "b", null));
			list1.add(new ThreeTuple<>(2, "c", null));
			list1.add(new ThreeTuple<>(3, "a", null));
			list1.add(new ThreeTuple<>(4, "b", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(3, "a"));
			list2.add(new Data(4, "b"));
			list2.add(new Data(5, "a"));
			list2.add(new Data(6, "c"));
			list2.add(new Data(2, "c"));
		}

		ListUtil.leftJoin(list1, list2, (o1, o2) -> {
			Integer a = o1.getA();
			Integer v1 = o2.getV1();
			String b = o1.getB();
			String v2 = o2.getV2();

			int compared = 0;

			try {
				compared = a.compareTo(v1);

				if (compared != 0) {
					return compared;
				}

				compared = b.compareTo(v2);

				return compared;
			} finally {
				System.out.println(a + ":" + v1 + ", " + b + ":" + v2 + ", " + compared);
			}
		}, ThreeTuple::setC);

		System.out.println(JSON.toJSON(list1));
	}

	/**
	 * 测试一对多。
	 *
	 * list1 和 list2 都是已排序好的。
	 */
	@Test
	public void test4() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(1, "a", null));
			list1.add(new ThreeTuple<>(2, "b", null));
			list1.add(new ThreeTuple<>(3, "c", null));
			list1.add(new ThreeTuple<>(4, "d", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(2, "a"));
			list2.add(new Data(2, "b"));
			list2.add(new Data(4, "a"));
			list2.add(new Data(4, "b"));
			list2.add(new Data(4, "c"));
		}

		ListUtil.leftJoin(list1,  list2, (a, b) -> a.getA().compareTo(b.getV1()), (a, b, i) ->{
			System.out.println(">>> " + a.getB() + " : " + b.getV2());
		});
	}

	/**
	 * 测试一对多。
	 *
	 * list1 是已排序好的，list2 是未排序的。
	 */
	@Test
	public void test5() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(1, "a", null));
			list1.add(new ThreeTuple<>(2, "b", null));
			list1.add(new ThreeTuple<>(3, "c", null));
			list1.add(new ThreeTuple<>(4, "d", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(4, "c"));
			list2.add(new Data(2, "b"));
			list2.add(new Data(4, "a"));
			list2.add(new Data(2, "a"));
			list2.add(new Data(4, "b"));
		}

		ListUtil.leftJoin(list1,  list2, (a, b) -> a.getA().compareTo(b.getV1()), (a, b, i) ->{
			System.out.println(">>> " + a.getB() + " : " + b.getV2());
		});
	}

	/**
	 * 测试一对多。
	 *
	 * list1 是已排序好的，list2 是未排序的。
	 */
	@Test
	public void test6() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(1, "a", null));
			list1.add(new ThreeTuple<>(2, "b", null));
			list1.add(new ThreeTuple<>(3, "c", null));
			list1.add(new ThreeTuple<>(4, "d", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(4, "c"));
			list2.add(new Data(2, "b"));
			list2.add(new Data(4, "a"));
			list2.add(new Data(2, "a"));
			list2.add(new Data(4, "b"));
		}

		/*ListUtil.leftJoin(list1,  list2, ListUtil.n1tn2((a, b) -> a.getA().compareTo(b.getV1())), (a, b, i) ->{
			System.out.println(">>> " + a.getB() + " : " + b.getV2());
		});*/
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void test7() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(0, "a", null));
			list1.add(new ThreeTuple<>(2, "b", null));
			list1.add(new ThreeTuple<>(4, "c", null));
			list1.add(new ThreeTuple<>(5, "d", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(1, "c"));
			list2.add(new Data(2, "c"));
			list2.add(new Data(2, "b"));
			list2.add(new Data(3, "a"));
			list2.add(new Data(4, "a"));
			list2.add(new Data(4, "a"));
		}

		ListUtil.leftJoin(list1,  list2, (a, b) -> a.getA().compareTo(b.getV1()), (a, b, i) ->{
			System.out.println(">>> " + a.getA() + " : " + b.getV1());
		});
	}

	/**
	 *
	 *
	 *
	 */
	@Test
	public void test8() {
		List<ThreeTuple<Integer, String, Data>> list1 = new LinkedList();

		{
			list1.add(new ThreeTuple<>(8128573, "8128573", null));
			list1.add(new ThreeTuple<>(8128580, "8128580", null));
			list1.add(new ThreeTuple<>(8133587, "8133587", null));
			list1.add(new ThreeTuple<>(8134833, "8134833", null));
			list1.add(new ThreeTuple<>(8484043, "8484043", null));
			list1.add(new ThreeTuple<>(8484077, "8484077", null));
		}

		List<Data> list2 = new LinkedList<>();

		{
			list2.add(new Data(8128573, "8128573"));
			list2.add(new Data(8134833, "8134833-1"));
			list2.add(new Data(8134835, "8134833-2"));
		}

		ListUtil.leftJoin(list1,  list2, (a, b) -> a.getA().compareTo(b.getV1()), (a, b, i) ->{
			System.out.println(">>> " + a.getA() + " : " + b.getV1());
		});
	}
}
