package base.other;

/**
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public class Pow1 implements Pow {
    @Override
    public long pow(int n, int k) {
        //1.将k表示为二进制形式时,从右往左每一位数分别表示n^1,n^2,n^4,n^8,n^16....
        //2.二进制每位上为1的位数代表的2的幂值相加等于k,而(n^a)*(n^b)=n^(a+b)
        //所以n^k总可以拆解为(n^1)*(n^2)*(n^4)*(n^8)*(n^16).....的形式.
        //每个二进制位上为1的位数代表的2的幂值作为n的指数,其结果相乘等于n^k
        int result = 1;
        int temp = n;
        for(int pow=k;pow!=0;pow>>=1){
            //该二进制位上为1
            if ((pow & 1) == 1) {
                result *= temp;
            }
            temp *= temp;
        }
        return result;
    }
}
