package base.sort;

/**
 * @author caotc
 * @date 2022-05-16
 * @since 1.0.0
 */
public class ReversePairCountDefault implements ReversePairCount {
    @Override
    public int reversePairCount(int[] array) {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    result++;
                }
            }
        }
        return result;
    }
}
