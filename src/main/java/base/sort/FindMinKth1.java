package base.sort;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.Random;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
@Slf4j
public class FindMinKth1 implements FindMinKth {
    @Override
    public <T> T minKth(T[] arr, int k, Comparator<T> comparator) {
        return minKth(arr, k, comparator, 0, arr.length - 1);
    }

    public <T> T minKth(T[] arr, int k, Comparator<T> comparator, int leftIndex, int rightIndex) {
        int randomIndex = new Random().nextInt(rightIndex - leftIndex + 1) + leftIndex;
        T partitionValue = arr[randomIndex];
        int lessValueIndex = leftIndex - 1;//[leftIndex,lessValueIndex]为当前小于区
        int graterValueIndex = rightIndex + 1;//[graterValueIndex,rightIndex]为当前大于区
        int index = leftIndex;
        while (index < graterValueIndex) {
            int compare = comparator.compare(arr[index], partitionValue);
            if (compare < 0) {//小于
                swap(arr, index, ++lessValueIndex);
                index++;
            } else if (compare > 0) {//大于
                swap(arr, index, --graterValueIndex);
            } else {//等于
                index++;
            }
        }
        if ((leftIndex + k - 1) <= lessValueIndex) {
            return minKth(arr, k, comparator, leftIndex, lessValueIndex);
        }
        if ((leftIndex + k - 1) >= graterValueIndex) {
            return minKth(arr, k - (graterValueIndex - leftIndex), comparator, graterValueIndex, rightIndex);
        }
        return partitionValue;
    }

    protected <T> void swap(T[] a, int i, int j) {
        T t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
