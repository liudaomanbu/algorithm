package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class KTimesNum1 implements KTimesNum {
    @Override
    public int findKTimesNum(int[] arr, int k, int m) {
        //代表二进制中该位为1出现了的次数
        int[] bitCounts = new int[32];
        //记录为1的每位数出现次数
        for (int num : arr) {
            for (int i = 0; i < 32; i++) {
                bitCounts[i] += (num >> i) & 1;
            }
        }
        /*
          1.如果该位只有出现k次的数为1,则次数为k
          2.如果该位只有出现M次的数为1,为1的数有X个,则次数为M*X
          3.如果该位两种数都有为1,为1的出现M次的数有X个,则次数为M*X+K
         */
        int result = 0;
        for (int i = 0; i < bitCounts.length; i++) {
            if (bitCounts[i] % m != 0) {
                //次数取余M不为0则该位数上K次数的数为1
                result |= 1 << i;
            }
        }
        return result;
    }
}
