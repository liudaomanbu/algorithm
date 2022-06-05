package base.dp;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class DpFibonacci implements Fibonacci {
    @Override
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        int pre=0;
        int cur=1;
        for(int i=2;i<=n;i++){
            int temp=cur;
            cur=pre+cur;
            pre=temp;
        }
        return cur;
    }
}
