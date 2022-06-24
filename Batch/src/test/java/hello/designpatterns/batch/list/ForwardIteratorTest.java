package hello.designpatterns.batch.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

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
        iterator.markRingStartingPoint();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }

        iterator.markRingStartingPoint();

        while (iterator.hasNext()) {
            Person person = iterator.next();
            System.out.println(person.getId());
        }
    }
}
