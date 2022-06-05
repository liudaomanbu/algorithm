package base.other;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
@Slf4j
public class Shuffle1 implements Shuffle {
    @Override
    public <T> void shuffle(T[] array) {
        shuffle(array, 0, array.length - 1);
    }

    public <T> void shuffle(T[] array, int startIndex, int endIndex) {
        int length = (endIndex - startIndex + 1);
        int n = length / 2;
        int match = 1;
        while ((match * 3 - 1) <= length) {
            match *= 3;
        }
        //满足m<n && 2m =（3^k-1）的最大值
        int m = (match - 1) / 2;
        if (m != n) {
            move(array, startIndex + m, startIndex + n + m - 1, n - m);
            prefectShuffle(array, startIndex, 2 * m);
            shuffle(array, startIndex + 2 * m, endIndex);
        } else {
            prefectShuffle(array, startIndex, 2 * m);
        }

    }

    /**
     * 将数组[startIndex,endIndex]区间中,左部分[startIndex,startIndex+leftLength-1]和右部分[startIndex+leftLength,endIndex]内部元素相对顺序不变的情况下
     * 将左部分移到右部分右边
     *
     * @param array      数组
     * @param startIndex 起始下标
     * @param endIndex   结束下标
     * @param leftLength 左部分长度
     */
    private <T> void move(T[] array, int startIndex, int endIndex, int leftLength) {
        reverse(array, startIndex, startIndex + leftLength - 1);
        reverse(array, startIndex + leftLength, endIndex);
        reverse(array, startIndex, endIndex);
    }

    private <T> void reverse(T[] array, int startIndex, int endIndex) {
        while (startIndex < endIndex) {
            T temp = array[endIndex];
            array[endIndex] = array[startIndex];
            array[startIndex] = temp;
            startIndex++;
            endIndex--;
        }
    }

    /**
     * 在array[startIndex,startIndex+length-1]将所有元素放到正确位置
     *
     * @param array      数组
     * @param startIndex 起始坐标
     * @param length     有效长度,必定为3^k-1
     */
    private <T> void prefectShuffle(T[] array, int startIndex, int length) {
        //环起始点序号,从1开始
        for (int cycleStartNumber = 1; cycleStartNumber <= length; cycleStartNumber *= 3) {
            cycle(array, startIndex, length, cycleStartNumber);
        }
    }

    /**
     * 在array[startIndex,startIndex+length-1]上对以第startNumber个元素为起始点的环的所有元素放到正确位置
     *
     * @param array       数组
     * @param startIndex  起始坐标
     * @param length      有效长度,必定为3^k-1
     * @param startNumber 从起始坐标开始数,序号为startNumber的元素为环的起始触发点
     */
    private <T> void cycle(T[] array, int startIndex, int length, int startNumber) {
        T currentValue = array[startIndex + startNumber - 1];
        int nextNumber = (2 * startNumber) % (length + 1);
        while (nextNumber != startNumber) {
            T temp = array[startIndex + nextNumber - 1];
            //序号-1=index
            array[startIndex + nextNumber - 1] = currentValue;
            currentValue = temp;

            nextNumber = (2 * nextNumber) % (length + 1);
        }
        array[startIndex + nextNumber - 1] = currentValue;
    }

}
