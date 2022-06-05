package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class PowerOfTwo2 implements PowerOfTwo {
    @Override
    public boolean isPowerOfTwo(int num) {
        //正数2的幂在二进制上只有一个1
        int mostRightOne = num & (~num + 1);
        return mostRightOne == num;
    }
}
