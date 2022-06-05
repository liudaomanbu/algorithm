package base.dp;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class RecursionFibonacci implements Fibonacci {
    @Override
    public int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
