package base.array;

/**
 * 局部最小值问题
 * 定义何为局部最小值：
 * 当a[i]的前后两个元素都存在时，需要满足“a[i] < a[i-1]，且a[i] < a[i+1]”这个条件，但是如果a[i]是第一个元素或者是最后一个元素，那么只需要看一边。
 * @author caotc
 * @date 2022-05-15
 * @since 1.0.0
 */
public interface LocalMin {
    /**
     * 给定一个数组arr，已知任何两个相邻的数都不相等，找到随便一个局部最小位置返回
     * @param array 数组
     * @return 局部最小位置
     */
    int localMinIndex(int[] array);
}
