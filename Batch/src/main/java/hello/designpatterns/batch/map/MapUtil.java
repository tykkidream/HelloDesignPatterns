package hello.designpatterns.batch.map;

import java.util.*;
import java.util.function.Function;

public class MapUtil {
    public static <K, V> Map<K, V> dict(List<V> datas, Function<V, K> function) {
        if (datas == null || datas.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Map<K, V> dict = new HashMap<>(datas.size());

        for (V data : datas) {
            if (data == null) {
                continue;
            }

            dict.put(function.apply(data), data);
        }

        return dict;
    }

    public static <K, V> Map<K, List<V>> dictList(List<V> datas, Function<V, K> function) {
        if (datas == null || datas.isEmpty()) {
            return Collections.EMPTY_MAP;
        }

        Map<K, List<V>> dict = new HashMap<>(datas.size());

        for (V data : datas) {
            if (data == null) {
                continue;
            }

            K k = function.apply(data);

            List<V> vs = dict.get(k);

            if (vs == null) {
                vs = new LinkedList<>();
                dict.put(k, vs);
            }

            vs.add(data);
        }

        return dict;
    }
}
