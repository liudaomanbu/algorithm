package base.bit;

/**
 * @author caotc
 * @date 2022-05-12
 * @since 1.0.0
 */
public class Max1 implements Max {
    @Override
    public int max(int a, int b) {
        int c = a - b;
        int signC = sign(c);
        int signA = sign(a);
        int signB = sign(b);
        int difSign = signA ^ signB;
        int sameSign = flip(difSign);
        /*
         *1.如果ab符号相同,则不会溢出,此时如果差值c为正数(符号为1)则a>b
         * 2.如果a为负数,b为正数则a<b
         * 3.如果a为正数,b为负数则a>b
         */
        int returnA = sameSign * signC + difSign * signA;
        int returnB = flip(returnA);
        return returnA * a + returnB * b;
    }

    private int sign(int num) {
        //负数第一位为1,正数第一位为0,取反后正数为1,负数为0
        return flip((num >>> 31) & 1);
    }

    //01取反
    private int flip(int num) {
        return num ^ 1;
    }
}
