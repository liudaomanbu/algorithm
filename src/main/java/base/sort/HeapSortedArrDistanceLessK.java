package base.sort;

import java.util.PriorityQueue;

/**
 * @author caotc
 * @date 2022-06-01
 * @since 1.0.0
 */
public class HeapSortedArrDistanceLessK implements SortedArrDistanceLessK {
    @Override
    public void sort(int[] nums, int k) {
        if (k == 0) {
            return;
        }
        //用k大小的堆辅助排序,[i,i+k-1]必然有i的排序结果,所以把堆顶弹出填入i,遍历操作完成排序
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int i = 0;
        for (; i < Math.min(k, nums.length); i++) {
            heap.add(nums[i]);
        }

        int j = 0;
        for (; i < nums.length; i++, j++) {
            heap.add(nums[i]);
            nums[j] = heap.poll();
        }

        while (!heap.isEmpty()) {
            nums[j++] = heap.poll();
        }
    }
}
