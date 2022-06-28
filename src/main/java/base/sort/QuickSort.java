package base.sort;

import lombok.Value;

import java.util.Random;

/**
 * @author caotc
 * @date 2021-03-23
 * @since 1.0.0
 */
public class QuickSort extends Sort {

    @Override
    public void sortInt(int[] nums) {
        sortInt(nums, 0, nums.length - 1);
    }

    private void sortInt(int[] nums, int startIndex, int endIndex) {
        if (endIndex > startIndex) {
            Range range = partition(nums, startIndex, endIndex);
            sortInt(nums, startIndex, range.left - 1);
            sortInt(nums, range.right + 1, endIndex);
        }
    }

    private Range partition(int[] nums, int startIndex, int endIndex) {
        int baseValue = nums[new Random().nextInt(endIndex - startIndex + 1) + startIndex];
        int leftIndex = startIndex - 1;
        int rightIndex = endIndex + 1;
        int index = startIndex;
        while (index < rightIndex) {
            if (nums[index] < baseValue) {
                swap(nums, index, ++leftIndex);
                index++;
            } else if (nums[index] > baseValue) {
                swap(nums, index, --rightIndex);
            } else {
                index++;
            }
        }
        return new Range(leftIndex + 1, rightIndex - 1);
    }

    @Value
    static class Range {
        int left;
        int right;
    }
}
