package hello.designpatterns.batch.list;

import com.alibaba.fastjson.JSON;
import hello.designpatterns.batch.tuple.ThreeTuple;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
		AttributeList<Person, Long> list1 = AttributeList.build(persons1, Person::getId, Person::setId);
		AttributeList<Person, Long> list2 = AttributeList.build(persons2, Person::getId, Person::setId);

		List<ThreeTuple<Long, Person, Person>> result = ListUtil.leftJoin(list1, list2);

		System.out.println(JSON.toJSONString(result));
	}
}
