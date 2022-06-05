package base.bit;

/**
 * @author caotc
 * @date 2022-05-11
 * @since 1.0.0
 */
public class OneOddTimesNum1 implements OneOddTimesNum {
    @Override
    public int findOddTimesNum(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
}
