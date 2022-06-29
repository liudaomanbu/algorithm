package base.sort;

/**
 * @author caotc
 * @date 2022-04-08
 * @since 1.0.0
 */
public class InsertionSort extends Sort {

    @Override
    public void sortInt(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = 1; i < nums.length; i++) { // 0 ~ i 做到有序
            for (int j = i - 1; j >= 0 && nums[j] > nums[j + 1]; j--) {
                swap(nums, j, j + 1);
            }
        }
    }
}
