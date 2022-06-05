package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class TwoOddTimesNum1 implements TwoOddTimesNum {
    @Override
    public int[] findOddTimesNum(int[] arr) {
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }

        int mostRightOne = eor & (~eor + 1);
        int result1 = 0;
        for (int num : arr) {
            if ((num & mostRightOne) != 0) {
                result1 ^= num;
            }
        }
        return new int[]{result1, eor ^ result1};
    }
}
