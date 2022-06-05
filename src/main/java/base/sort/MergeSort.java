package base.sort;

/**
 * @author caotc
 * @date 2022-05-15
 * @since 1.0.0
 */
public class MergeSort extends Sort {
    @Override
    public <T extends Comparable<T>> void sort(T[] nums) {
        sort(nums, 0, nums.length - 1);
    }

    @Override
    public void sortInt(int[] nums) {
        sortInt(nums, 0, nums.length - 1);
    }

    private void sortInt(int[] nums, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = startIndex + ((endIndex - startIndex) >> 1);
            sortInt(nums, startIndex, middleIndex);
            sortInt(nums, middleIndex + 1, endIndex);
            merge(nums, startIndex, middleIndex, endIndex);
        }
    }

    private void merge(int[] nums, int startIndex, int middleIndex, int endIndex) {
        int[] help = new int[endIndex - startIndex + 1];
        int i = 0;
        int leftIndex = startIndex;
        int rightIndex = middleIndex+1;
        while (leftIndex <= middleIndex && rightIndex <= endIndex) {
            help[i++] = nums[leftIndex] <= nums[rightIndex] ? nums[leftIndex++] : nums[rightIndex++];
        }
        while (leftIndex <= middleIndex) {
            help[i++] = nums[leftIndex++];
        }
        while (rightIndex <= endIndex) {
            help[i++] = nums[rightIndex++];
        }

        for (i = 0; i < help.length; i++) {
            nums[startIndex + i] = help[i];
        }
    }

    private <T extends Comparable<T>> void sort(T[] nums, int leftIndex, int rightIndex) {
        if (leftIndex != rightIndex) {
            int middleIndex = leftIndex + ((rightIndex - leftIndex) >> 1);
            sort(nums, leftIndex, middleIndex);
            sort(nums, middleIndex + 1, rightIndex);
            merge(nums, leftIndex, middleIndex, rightIndex);
        }
    }

    private <T extends Comparable<T>> void merge(T[] nums, int left, int middle, int right) {
        Object[] help = new Object[right - left + 1];
        int i = 0;
        int leftIndex = left;
        int rightIndex = middle + 1;
        while (leftIndex <= middle && rightIndex <= right) {
            help[i++] = lessOrEqual(nums[leftIndex], nums[rightIndex]) ? nums[leftIndex++] : nums[rightIndex++];
        }
        while (leftIndex <= middle) {
            help[i++] = nums[leftIndex++];
        }
        while (rightIndex <= right) {
            help[i++] = nums[rightIndex++];
        }

        for (i = 0; i < help.length; i++) {
            nums[left + i] = (T) help[i];
        }
    }
}
