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
    public <T extends Comparable<T>> void sort(T[] nums) {
        sort(nums, 0, nums.length - 1);
    }

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

    private <T extends Comparable<T>> void sort(T[] nums, int start, int end) {
        if (end <= start) {
            return;
        }
        Range equalRange = partition(nums, start, end);
        sort(nums, start, equalRange.left() - 1);
        sort(nums, equalRange.right() + 1, end);
    }

    private <T extends Comparable<T>> Range partition(T[] a, int leftIndex, int rightIndex) {
        //随机划分值
        T baseValue = a[new Random().nextInt(rightIndex - leftIndex + 1) + leftIndex];
        int lessIndex = leftIndex - 1;//<部分右边界,闭区间
        int greaterIndex = rightIndex + 1;//>部分左边界,闭区间
        int index = leftIndex;//当前指针
        while (index < greaterIndex) {
            if (equal(a[index], baseValue)) {
                index++;
            } else if (less(a[index], baseValue)) {
                swap(a, index, ++lessIndex);
                index++;
            } else {
                swap(a, index, --greaterIndex);
            }
        }
        //[leftIndex,lessIndex][lessIndex+1,greaterIndex-1][greaterIndex,rightIndex]
        return new Range(lessIndex + 1, greaterIndex - 1);
    }

    @Value
    static class Range {
        int left;
        int right;
    }
}
