package base.bit;

/**
 * @author caotc
 * @date 2022-05-11
 * @since 1.0.0
 */
public interface KTimesNum {
    /**
     * 一个数组中有一种数出现K次，其他数都出现了M次，
     * 已知M > 1，K < M，找到出现了K次的数
     * 要求额外空间复杂度O(1)，时间复杂度O(N)
     *
     * @param arr 数组
     * @return 出现了K次的数
     */
    int findKTimesNum(int[] arr, int k, int m);
}
