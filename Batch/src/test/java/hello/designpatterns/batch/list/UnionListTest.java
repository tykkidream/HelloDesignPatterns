package hello.designpatterns.batch.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

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
        Assert.assertEquals("a", unionList.get(0));
        Assert.assertEquals("b", unionList.get(1));
        Assert.assertEquals("c", unionList.get(2));
        Assert.assertEquals("d", unionList.get(3));
        Assert.assertEquals("1", unionList.get(4));
        Assert.assertEquals("2", unionList.get(5));
        Assert.assertEquals("3", unionList.get(6));
        Assert.assertEquals("4", unionList.get(7));
    }

    @Test
    public void test2() {
        for (String data : unionList) {
            System.out.println(data);
        }
    }

    @Test
    public void test3() {
        Assert.assertEquals(8, unionList.size());
    }

    @Test
    public void test4() {
        Assert.assertEquals("a", unionList.set(0, "aa"));
        Assert.assertEquals("b", unionList.set(1, "bb"));
        Assert.assertEquals("c", unionList.set(2, "cc"));
        Assert.assertEquals("d", unionList.set(3, "dd"));
        Assert.assertEquals("1", unionList.set(4, "11"));
        Assert.assertEquals("2", unionList.set(5, "22"));
        Assert.assertEquals("3", unionList.set(6, "33"));
        Assert.assertEquals("4", unionList.set(7, "44"));

        Assert.assertEquals("aa", unionList.get(0));
        Assert.assertEquals("bb", unionList.get(1));
        Assert.assertEquals("cc", unionList.get(2));
        Assert.assertEquals("dd", unionList.get(3));
        Assert.assertEquals("11", unionList.get(4));
        Assert.assertEquals("22", unionList.get(5));
        Assert.assertEquals("33", unionList.get(6));
        Assert.assertEquals("44", unionList.get(7));
    }

    @Test
    public void test5() {
        Assert.assertEquals("c", unionList.get(2));

        unionList.add(2, "cc");

        Assert.assertEquals("cc", unionList.get(2));
        Assert.assertEquals("c", unionList.get(3));

        Assert.assertEquals("3", unionList.get(7));

        unionList.add(7, "33");

        Assert.assertEquals("33", unionList.get(7));
        Assert.assertEquals("3", unionList.get(8));
    }

}
