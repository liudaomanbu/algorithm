package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public class ArrayLeftSumDefault implements ArrayLeftSum {
    @Override
    public int arrayLeftSum(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[j] < array[i]) {
                    result += array[j];
                }
            }
        }
        return result;
    }
}
