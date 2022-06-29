package base.sort;

/**
 * @author caotc
 * @date 2022-06-29
 * @since 1.0.0
 */
public class SelectSort extends Sort {
    @Override
    public void sortInt(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[minIndex]) {
                    minIndex = j;
                }
            }
            if (i != minIndex) {
                swap(nums, i, minIndex);
            }
        }
    }
}
