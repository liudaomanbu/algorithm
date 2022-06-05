package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public class ArrayLeftSumMergeSort implements ArrayLeftSum {
    @Override
    public int arrayLeftSum(int[] array) {
        return arrayLeftSum(array, 0, array.length - 1);
    }

    private int arrayLeftSum(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = startIndex + ((endIndex - startIndex) >> 1);
            int result = 0;
            result += arrayLeftSum(array, startIndex, middleIndex);
            result += arrayLeftSum(array, middleIndex + 1, endIndex);
            result += merge(array, startIndex, middleIndex, endIndex);
            return result;
        }
        return 0;
    }

    private int merge(int[] array, int startIndex, int middleIndex, int endIndex) {
        int[] help = new int[endIndex - startIndex + 1];
        int i = 0;
        int leftIndex = startIndex;
        int rightIndex = middleIndex + 1;
        int result = 0;
        while (leftIndex <= middleIndex && rightIndex <= endIndex) {
            //以middle隔开的两边都是有序数组,所以右侧数组[rightIndex,endIndex]都大于arr[leftIndex],计算小和时arr[leftIndex]乘以该倍数
            if (array[leftIndex] < array[rightIndex]) {
                result += (endIndex - rightIndex + 1) * array[leftIndex];
            }
            help[i++] = array[leftIndex] < array[rightIndex] ? array[leftIndex++] : array[rightIndex++];
        }
        while (leftIndex <= middleIndex) {
            help[i++] = array[leftIndex++];
        }
        while (rightIndex <= endIndex) {
            help[i++] = array[rightIndex++];
        }
        for (i = 0; i < help.length; i++) {
            array[startIndex + i] = help[i];
        }
        return result;
    }
}
