package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class NumWays10 {
    public static void main(String[] args) {
        System.out.println(numWays1(45));
    }
    public static int numWays1(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int a = 1;
        int b = 1;
        int fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = (a + b) % 1000000007;
            a=b;
            b=fib;
        }
        return fib;
    }
}
