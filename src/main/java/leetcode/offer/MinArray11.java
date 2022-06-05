package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class MinArray11 {
    public static void main(String[] args) {
        System.out.println(minArray2(new int[]{4, 5, 6, 6, 6, 6, 1, 2, 3, 3}));
    }

    public static int minArray2(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (numbers[middle] < numbers[high]) {
                high = middle;
            } else if (numbers[middle] > numbers[high]) {
                low = middle + 1;
            } else {
                high -= 1;
            }
        }
        return numbers[low];
    }

    public static int minArray1(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException();
        }
        int i = numbers.length - 1;
        while (i > 0 && numbers[i] >= numbers[i - 1]) {
            i--;
        }
        return numbers[i];
    }
}
