package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public interface ReversePairCount {
    /**
     * 在一个数组中，任何一个前面的数a，和任何一个后面的数b，如果(a,b)是降序的，就称为降序对
     * 给定一个数组arr，求数组的降序对总数量
     * @param array 数组
     * @return 降序对总数量
     */
    int reversePairCount(int[] array);
}
