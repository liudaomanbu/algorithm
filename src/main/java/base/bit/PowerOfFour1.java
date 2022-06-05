package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class PowerOfFour1 implements PowerOfFour {
    @Override
    public boolean isPowerOfFour(int num) {
        //正数2的幂在二进制上只有一个1,而且在偶数位上
        int mostRightOne = num & (~num + 1);
        return mostRightOne == num
                && (mostRightOne & 0b01010101010101010101010101010101) != 0;
    }
}
