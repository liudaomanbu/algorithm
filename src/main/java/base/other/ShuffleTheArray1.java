package base.other;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public class ShuffleTheArray1 implements ShuffleTheArray {
    @Override
    public int[] shuffle(int[] nums, int n) {
        shuffle(nums, 0, nums.length - 1);
        for (int i = 0; i < nums.length; i += 2) {
            int temp = nums[i];
            nums[i] = nums[i + 1];
            nums[i + 1] = temp;
        }
        return nums;
    }

    private void shuffle(int[] nums, int startIndex, int endIndex) {
        int length = (endIndex - startIndex) + 1;
        int n = length / 2;
        int match = 1;
        while ((match * 3 - 1) <= length) {
            match *= 3;
        }

        int m = (match - 1) / 2;
        if (m != n) {
            move(nums, startIndex + m, startIndex + n + m - 1, n - m);
            prefectShuffle(nums, startIndex, 2 * m);
            shuffle(nums, startIndex + 2 * m, endIndex);
        } else {
            prefectShuffle(nums, startIndex, length);
        }
    }

    private void move(int[] nums, int startIndex, int endIndex, int leftLength) {
        reverse(nums, startIndex, startIndex + leftLength - 1);
        reverse(nums, startIndex + leftLength, endIndex);
        reverse(nums, startIndex, endIndex);
    }

    private void reverse(int[] nums, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    private void prefectShuffle(int[] nums, int startIndex, int length) {
        for (int cycleStartNumber = 1; cycleStartNumber <= length; cycleStartNumber *= 3) {
            cycle(nums, startIndex, length, cycleStartNumber);
        }
    }

    private void cycle(int[] nums, int startIndex, int length, int startNumber) {
        int currentValue = nums[startIndex + startNumber - 1];
        int nextNumber = (2 * startNumber) % (length + 1);
        while (nextNumber != startNumber) {
            int temp = nums[startIndex + nextNumber - 1];
            nums[startIndex + nextNumber - 1] = currentValue;
            currentValue = temp;

            nextNumber = (2 * nextNumber) % (length + 1);
        }
        nums[startIndex + nextNumber - 1] = currentValue;
    }
}
