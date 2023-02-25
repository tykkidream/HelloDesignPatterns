package hello.designpatterns.batch.list;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ForwardIteratorTest {

    private List<Person> persons;

    @Before
    public void before() {
        persons = new ArrayList<>();

        persons.add(new Person(3L, "ali"));
        persons.add(new Person(2L, "tengxun"));
        persons.add(new Person(4L, "huawei"));
        persons.add(new Person(1L, "baidu"));
    }

    public List<Integer> dataArray(int total) {
        List<Integer> list = new ArrayList<>(total);

        for (int i = 0; i < total; i++) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> dataLinked(int total) {
        List<Integer> list = new LinkedList<>();

        for (int i = 0; i < total; i++) {
            list.add(i);
        }
        return list;
    }

    @Test
    public void test01() {
        ForwardIterator<Person> iterator = new ForwardIterator<>(persons);

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }
    }

    @Test
    public void test02() {
        ForwardIterator<Person> iterator = new ForwardIterator<>(persons);

        iterator.next();
        iterator.next();
        iterator.markRingStartingPoint();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }
    }

    @Test
    public void test03() {
        ForwardIterator<Person> iterator = new ForwardIterator<>(persons);

        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.markRingStartingPoint();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }


        System.out.println("===============================");

        try {
            System.out.println(iterator.hasNext());
            iterator.next();

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }



        System.out.println("===============================");

        iterator.markRingStartingPoint();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }
    }

    @Test
    public void test04() {
        List<Integer> list = Arrays.asList(0);

        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        System.out.println("======================");

        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);

            iterator.markRingStartingPoint();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    @Test
    public void test05() {
        List<Integer> list = Arrays.asList(0, 1);

        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        System.out.println("======================");

        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);
            iterator.markRingStartingPoint();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    @Test
    public void test06() {
        List<Integer> list = Arrays.asList(0, 1, 2);


        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }

        System.out.println("======================");

        {
            ForwardIterator<Integer> iterator = new ForwardIterator<>(list);
            iterator.markRingStartingPoint();

            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }

    @Test
    public void test7(){
        List<Integer> dataArray = WrapList.wrap(dataArray(2000)) ;
        List<Integer> dataLinked = WrapList.wrap(dataLinked(2000));
//        List<Integer> dataArray = dataArray(2000) ;
//        List<Integer> dataLinked = dataLinked(2000);

        Iterator<Integer> iterator1 = dataArray.iterator();
        Iterator<Integer> iterator2 = dataLinked.iterator();

        long time1 = System.nanoTime();

        while (iterator1.hasNext()) {
            Integer next = iterator1.next();
            iterator1.remove();
        }

        long time2 = System.nanoTime();

        while (iterator2.hasNext()) {
            Integer next = iterator2.next();
            iterator2.remove();
        }

        long time3 = System.nanoTime();

        System.out.println("第一次耗时： " + (time2 - time1));
        System.out.println("第二次耗时： " + (time3 - time2));
    }
}
