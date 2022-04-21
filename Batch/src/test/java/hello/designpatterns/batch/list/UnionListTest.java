package hello.designpatterns.batch.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnionListTest {
    private UnionList<String, String> unionList;

    @Before
    public void before() {
        List<String> list1 = new LinkedList<>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");

        List<String> list2 = new LinkedList<>();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("4");

        unionList = new UnionList(10);
        unionList.put("list1", list1);
        unionList.put("list2", list2);
    }

    @Test
    public void test1() {
        assertEquals("a", unionList.get(0));
        assertEquals("b", unionList.get(1));
        assertEquals("c", unionList.get(2));
        assertEquals("d", unionList.get(3));
        assertEquals("1", unionList.get(4));
        assertEquals("2", unionList.get(5));
        assertEquals("3", unionList.get(6));
        assertEquals("4", unionList.get(7));
    }

    @Test
    public void test2() {
        for (String data : unionList) {
            System.out.println(data);
        }
    }

    @Test
    public void test3() {
        assertEquals(8, unionList.size());
    }

    @Test
    public void test4() {
        assertEquals("a", unionList.set(0, "aa"));
        assertEquals("b", unionList.set(1, "bb"));
        assertEquals("c", unionList.set(2, "cc"));
        assertEquals("d", unionList.set(3, "dd"));
        assertEquals("1", unionList.set(4, "11"));
        assertEquals("2", unionList.set(5, "22"));
        assertEquals("3", unionList.set(6, "33"));
        assertEquals("4", unionList.set(7, "44"));

        assertEquals("aa", unionList.get(0));
        assertEquals("bb", unionList.get(1));
        assertEquals("cc", unionList.get(2));
        assertEquals("dd", unionList.get(3));
        assertEquals("11", unionList.get(4));
        assertEquals("22", unionList.get(5));
        assertEquals("33", unionList.get(6));
        assertEquals("44", unionList.get(7));
    }

    @Test
    public void test5() {
        assertEquals("c", unionList.get(2));

        unionList.add(2, "cc");

        assertEquals("cc", unionList.get(2));
        assertEquals("c", unionList.get(3));

        assertEquals("3", unionList.get(7));

        unionList.add(7, "33");

        assertEquals("33", unionList.get(7));
        assertEquals("3", unionList.get(8));
    }

    @Test
    public void test6() {
        List<String> list1 = new LinkedList<>();
        List<String> list2 = new LinkedList<>();

        UnionList<String, String> unionList = this.unionList = new UnionList(10);
        unionList.put("list1", list1);
        unionList.put("list2", list2);

        for (String data : this.unionList) {
            System.out.println(data);
        }

        assertEquals(0, unionList.size());

        list1.add("a");
        list2.add("1");

        for (String data : this.unionList) {
            System.out.println(data);
        }

        assertEquals(2, unionList.size());

    }

}
