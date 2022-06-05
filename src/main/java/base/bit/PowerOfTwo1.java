package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class PowerOfTwo1 implements PowerOfTwo {
    @Override
    public boolean isPowerOfTwo(int num) {
        //正数2的幂在二进制上只有一个1
        int bitCount = 0;
        for (int i = 0; i < 31; i++) {
            bitCount += (num >> i) & 1;
        }
        return bitCount == 1;
    }
}
