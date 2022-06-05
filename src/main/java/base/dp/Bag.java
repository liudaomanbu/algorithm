package base.dp;

/**
 * 有n件物品和一个最多能背重量为w 的背包。
 * 第i件物品的重量是weight[i]，得到的价值是value[i] 。
 * 每件物品只能用一次，求解物品装入背包里物品价值总和最大。
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public interface Bag {
    /**
     * 求解物品装入背包里物品价值总和最大
     * @param weights 重量
     * @param values 价值
     * @param bagSize 背包大小
     * @return 最大价值
     */
    int maxValue(int[] weights,int[] values,int bagSize);
}
