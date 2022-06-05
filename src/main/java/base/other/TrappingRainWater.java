package base.other;

/**
 * https://leetcode-cn.com/problems/trapping-rain-water/
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public interface TrappingRainWater {
    /**
     * @param height 高度数组
     * @return 总共接的雨水量
     */
    int trap(int[] height);
}
