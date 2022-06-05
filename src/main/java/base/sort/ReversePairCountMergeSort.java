package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public class ReversePairCountMergeSort implements ReversePairCount {
    @Override
    public int reversePairCount(int[] array) {
        return reversePairCount(array, 0, array.length - 1);
    }

    private int reversePairCount(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = startIndex + ((endIndex - startIndex) >> 1);
            int result = 0;
            result += reversePairCount(array, startIndex, middleIndex);
            result += reversePairCount(array, middleIndex + 1, endIndex);
            result += merge(array, startIndex, middleIndex, endIndex);
            return result;
        }
        return 0;
    }

    private int merge(int[] array, int startIndex, int middleIndex, int endIndex) {
        int[] help = new int[endIndex - startIndex + 1];
        int i = help.length - 1;
        int leftIndex = middleIndex;
        int rightIndex = endIndex;
        int result = 0;
        while (leftIndex >= startIndex && rightIndex > middleIndex) {
            //两边都是有序数组,所以[middleIndex+1,rightIndex]此时都小于array[leftIndex]
            if (array[leftIndex] > array[rightIndex]) {
                result += rightIndex - middleIndex;
            }
            help[i--] = array[leftIndex] > array[rightIndex] ? array[leftIndex--] : array[rightIndex--];
        }
        while (leftIndex >= startIndex) {
            help[i--] = array[leftIndex--];
        }
        while (rightIndex > middleIndex) {
            help[i--] = array[rightIndex--];
        }
        for (i = 0; i < help.length; i++) {
            array[startIndex + i] = help[i];
        }
        return result;
    }
}
