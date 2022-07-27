package hello.designpatterns.quota.queue;

import java.util.LinkedList;
import java.util.List;

public class Data {
    public String name;

    public String data;

    public Data(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public static List<Data> data1(int size, List<String> names) {
        List<Data> data = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (String name : names) {
                data.add(new Data(name, name + "-" + i));
            }
        }

        return data;
    }
}
