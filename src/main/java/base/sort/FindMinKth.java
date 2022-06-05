package base.sort;

import java.util.Comparator;

/**
 * 无序数组选第k小的数
 *
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public interface FindMinKth {
    default <T extends Comparable<T>> T minKth(T[] arr, int k) {
        return minKth(arr, k, Comparable::compareTo);
    }

    <T> T minKth(T[] arr, int k, Comparator<T> comparator);
}
