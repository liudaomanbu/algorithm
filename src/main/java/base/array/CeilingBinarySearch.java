package base.array;

/**
 * @author caotc
 * @date 2022-05-15
 * @since 1.0.0
 */
public class CeilingBinarySearch implements Ceiling {
    @Override
    public int search(int[] array, int num) {
        if (array == null || array.length == 0 || array[array.length - 1] < num) {
            return -1;
        }
        if (array[0] >= num) {
            return 0;
        }
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        while (leftIndex < rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            if ((array[middleIndex] > num && array[middleIndex - 1] >= num)
                    || (array[middleIndex] == num && array[middleIndex - 1] == array[middleIndex])) {
                rightIndex = middleIndex - 1;
            } else if (array[middleIndex] < num) {
                leftIndex = middleIndex + 1;
            } else {
                return middleIndex;
            }
        }
        return leftIndex;
    }
}
