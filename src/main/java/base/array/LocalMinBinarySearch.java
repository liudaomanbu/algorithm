package base.array;

/**
 * @author caotc
 * @date 2022-05-15
 * @since 1.0.0
 */
public class LocalMinBinarySearch implements LocalMin {
    @Override
    public int localMinIndex(int[] array) {
        if (array == null || array.length == 0) {
            return -1;
        }
        if (array.length == 1 || array[0] < array[1]) {
            return 0;
        }
        if (array[array.length - 1] < array[array.length - 2]) {
            return array.length - 1;
        }
        int leftIndex = 1;
        int rightIndex = array.length - 2;
        while (leftIndex < rightIndex) {
            int middle = (leftIndex + rightIndex) / 2;
            if (array[middle] > array[middle - 1]) {
                rightIndex = middle - 1;
            } else if (array[middle] > array[middle + 1]) {
                leftIndex = middle + 1;
            } else {
                return middle;
            }
        }
        return leftIndex;
    }
}
