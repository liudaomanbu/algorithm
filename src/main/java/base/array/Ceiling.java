package base.array;

/**
 * @author caotc
 * @date 2022-05-15
 * @since 1.0.0
 */
public interface Ceiling {
    /**
     * 给定有序数组和一个数,在数组中找到大于等于这个数的最小数
     *
     * @param array 有序数组
     * @param num   比较的数
     * @return 数组中大于等于这个数的最小数的下标
     */
    int search(int[] array, int num);
}
