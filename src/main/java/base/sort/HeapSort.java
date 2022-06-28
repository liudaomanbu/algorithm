package base.sort;

/**
 * @author caotc
 * @date 2022-05-29
 * @since 1.0.0
 */
public class HeapSort extends Sort {

    @Override
    public void sortInt(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            heapify(nums, nums.length, i);
        }
        int heapSize=nums.length;
        for (int i = heapSize - 1; i >= 0; i--) {
            swap(nums,0,i);
            heapSize--;
            heapify(nums, heapSize, 0);
        }
    }

    private void heapify(int[] nums, int heapSize, int index) {
        int leftChildrenIndex = index * 2 + 1;
        int rightChildrenIndex = index * 2 + 2;
        int maxIndex = index;
        if (leftChildrenIndex < heapSize && nums[leftChildrenIndex] > nums[maxIndex]) {
            maxIndex = leftChildrenIndex;
        }
        if (rightChildrenIndex < heapSize && nums[rightChildrenIndex] > nums[maxIndex]) {
            maxIndex = rightChildrenIndex;
        }
        if (maxIndex != index) {
            swap(nums, maxIndex, index);
            heapify(nums, heapSize, maxIndex);
        }
    }
}
