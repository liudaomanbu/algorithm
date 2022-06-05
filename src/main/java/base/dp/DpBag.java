package base.dp;

/**
 * @author caotc
 * @date 2022-05-01
 * @since 1.0.0
 */
public class DpBag implements Bag {
    /**
     * 时间复杂度O(weights.length * bagSize)
     * @param weights 重量
     * @param values 价值
     * @param bagSize 背包大小
     * @return 最大价值
     */
    @Override
    public int maxValue(int[] weights, int[] values, int bagSize) {
        /*
          row:[startIndex,length)的物品可用即weights和values可用范围
          column:背包容量 >=0
          value:最大价值
          空间压缩,startIndex用变量虚拟
          当前代表最后一行startIndex == weights.length,value=0
         */
        int[] result = new int[bagSize + 1];

        //由于[i][j]位置依赖[i+1][0,j]
        for (int startIndex = weights.length - 1; startIndex >= 0; startIndex--) {//从下往上
            for (int curBagSize = bagSize; curBagSize > 0; curBagSize--) {//从右往左
                int noValue = result[curBagSize];
                int maxValue = noValue;
                //当前背包容量大于当前下标重量时才能选择放入当前物品,避免该方法传入bagSize<0的无效解情况
                if (curBagSize >= weights[startIndex]) {
                    maxValue = Math.max(noValue, values[startIndex] + result[curBagSize - weights[startIndex]]);
                }
                result[curBagSize] = maxValue;
            }
        }

        return result[bagSize];
    }
}
