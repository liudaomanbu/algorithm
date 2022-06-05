package base.sort;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
 * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。
 *
 * @author caotc
 * @date 2022-06-01
 * @since 1.0.0
 */
public interface SortedArrDistanceLessK {
    void sort(int[] nums, int k);
}
