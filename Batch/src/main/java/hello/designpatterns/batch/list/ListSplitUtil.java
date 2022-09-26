package hello.designpatterns.batch.list;

import java.util.LinkedList;
import java.util.List;

public class ListSplitUtil {

    public interface SplitBatchProcessingHandler<T> {
        void doHandle(List<T> data);
    }

    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new LinkedList<>();

        // 若原 list 大小为0，则直接返回
        if(source.size() == 0){
            return result;
        }

        int totalSize = source.size();
        // 先计算出余数
        int remaider = totalSize % n;
        // 然后是商
        int number = totalSize / n;
        // 偏移量
        int offset = 0;

        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }

    /**
     * 将一个list分成多个子list，每个了list的最多subSize个数据。
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> capacityAssign(List<T> source, int subSize) {
        List<List<T>> result = new LinkedList<>();

        // 若原 list 大小为0，则直接返回
        if(source.size() <= subSize){
            result.add(source);
            return result;
        }

        int totalSize = source.size();
        int offset = 0;
        int end = 0;

        while (true) {
            end = offset + subSize;

            if (end > totalSize) {
                end = totalSize;
            }

            List<T> subList = source.subList(offset, end);

            result.add(subList);

            if (end >= totalSize) {
                break;
            }

            offset = end + 1;
        }

        return result;
    }

    public static <T> List<List<T>> balanceAssign(List<T> source, int subSize) {
        List<List<T>> result = new LinkedList<>();

        // 若原 list 大小为0，则直接返回
        if(source.size() <= subSize){
            result.add(source);
            return result;
        }

        int totalSize = source.size();

        if (totalSize % subSize > 0) {
            int partitions = totalSize / subSize + 1;
            subSize = totalSize / partitions + 1;
        }

        int offset = 0;
        int end = 0;

        while (true) {
            end = offset + subSize;

            if (end > totalSize) {
                end = totalSize;
            }

            List<T> subList = source.subList(offset, end);

            result.add(subList);

            if (end >= totalSize) {
                break;
            }

            offset = end + 1;
        }

        return result;
    }


    /**
     * 将一个 List 分割成多个小的 List ，并指定小 List 的最多数量为 subDataSize 。
     * @param data 完整数据
     * @param subDataSize 分割后每个 List 最多数量
     * @param splitBatchProcessingHandler 处理小 List 的处理器
     * @param <T> List 的数据类型
     * @throws Throwable
     */
    public static <T> void splitBatchProcessing(List<T> data, int subDataSize, SplitBatchProcessingHandler<T> splitBatchProcessingHandler) {
        if (data.size() == 0) {
            return;
        }

        for (List<T> subData : averageAssign(data, data.size() / subDataSize + 1)) {
            splitBatchProcessingHandler.doHandle(subData);
        }
    }

    public static <T> void averageAssignBatchProcessing(List<T> data, int conut, SplitBatchProcessingHandler<T> splitBatchProcessingHandler) {
        if (data.size() == 0) {
            return;
        }

        for (List<T> subData : averageAssign(data, conut)) {
            splitBatchProcessingHandler.doHandle(subData);
        }
    }

    public static <T> void capacityAssignBatchProcessing(List<T> data, int subSize, SplitBatchProcessingHandler<T> splitBatchProcessingHandler) {
        if (data.size() == 0) {
            return;
        }

        for (List<T> subData : capacityAssign(data, subSize)) {
            splitBatchProcessingHandler.doHandle(subData);
        }
    }

    public static <T> void balanceAssignBatchProcessing(List<T> data, int subSize, SplitBatchProcessingHandler<T> splitBatchProcessingHandler) {
        if (data.size() == 0) {
            return;
        }

        for (List<T> subData : balanceAssign(data, subSize)) {
            splitBatchProcessingHandler.doHandle(subData);
        }
    }
}
