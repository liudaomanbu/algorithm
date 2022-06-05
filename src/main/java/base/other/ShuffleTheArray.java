package base.other;

/**
 * https://leetcode-cn.com/problems/shuffle-the-array/
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * <p>
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 *
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public interface ShuffleTheArray {
    int[] shuffle(int[] nums, int n);
}
