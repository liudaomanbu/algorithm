package base.other;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public class TrappingRainWater1 implements TrappingRainWater {
    @Override
    public int trap(int[] height) {
        //i所在列的雨水量=Math.max(Math.min(leftMaxHeight,rightMaxHeight)-height[i],0)
        int leftIndex = 0;
        int rightIndex = height.length - 1;
        //height[0,leftIndex-1]范围内最大值
        int leftMaxHeight = 0;
        //height[rightIndex,length-1]范围内最大值
        int rightMaxHeight = 0;

        int water = 0;
        //[leftIndex,rightIndex]是当前未结算区间,相等时也需要结算一次
        while (leftIndex <= rightIndex) {
            //即使[leftIndex+1,rightIndex-1]中有更大的右边最大值,对于leftIndex也不影响Math.min(leftMaxHeight,rightMaxHeight)结果
            //因此可以结算leftIndex上的雨水量
            if (leftMaxHeight <= rightMaxHeight) {
                water += Math.max(leftMaxHeight - height[leftIndex], 0);
                leftMaxHeight = Math.max(leftMaxHeight, height[leftIndex++]);
            }else {
                //即使[leftIndex+1,rightIndex-1]中有更大的左边最大值,对于rightIndex也不影响Math.min(leftMaxHeight,rightMaxHeight)结果
                //因此可以结算rightIndex上的雨水量
                water += Math.max(rightMaxHeight - height[rightIndex], 0);
                rightMaxHeight = Math.max(rightMaxHeight, height[rightIndex--]);
            }
        }
        return water;
    }
}

