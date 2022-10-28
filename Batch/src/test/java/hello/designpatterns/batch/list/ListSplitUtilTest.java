package hello.designpatterns.batch.list;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ListSplitUtilTest {
    @Test
    public void test01() {
        List<Integer> all = new LinkedList<>();

        for (int i = 0; i < 998; i++) {
            all.add(i);
        }

        System.out.println("S：" + all.size());
        System.out.println("F：" + all.get(0));
        System.out.println("L：" + all.get(all.size() - 1));

        System.out.println("==========================");

        List<List<Integer>> lists = ListSplitUtil.balanceAssign(all, 500);

        for (List<Integer> list : lists) {
            System.out.println("S：" + list.size());
            System.out.println("B：" + list.get(0));
            System.out.println("E：" + list.get(list.size() - 1));
            System.out.println("==========================");
        }
    }
}
