package base.dp;

/**
 * 暴力递归解
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public class RecursionBag implements Bag{
    /**
     * 时间复杂度O(N^2)
     * @param weights 重量
     * @param values 价值
     * @param bagSize 背包大小
     * @return 最大价值
     */
    @Override
    public int maxValue(int[] weights, int[] values, int bagSize) {
        return maxValue(weights,values,bagSize,0);
    }

    /**
     *
     * @param weights 重量
     * @param values 价值
     * @param bagSize 背包容量 >=0
     * @param startIndex [startIndex,length)的物品可用即weights和values可用范围
     * @return 最大价值
     */
    int maxValue(int[] weights, int[] values, int bagSize,int startIndex){
        if (startIndex >= weights.length) {
            return 0;
        }
        if (bagSize == 0) {
            return 0;
        }

        int noValue = maxValue(weights, values, bagSize, startIndex + 1);
        //当前背包容量大于当前下标重量时才能选择放入当前物品,避免该方法传入bagSize<0的无效解情况
        if (bagSize >= weights[startIndex]) {
            return Math.max(noValue, values[startIndex] + maxValue(weights, values, bagSize - weights[startIndex], startIndex + 1));
        }
        return noValue;
    }

}
