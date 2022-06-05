package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class Fib10 {
    public static void main(String[] args) {
        System.out.println(fib1(45));
    }

    public static int fib1(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int a = 0;
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
