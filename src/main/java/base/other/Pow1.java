package base.other;

/**
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public class Pow1 implements Pow {
    @Override
    public long pow(int n, int k) {
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
