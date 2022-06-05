package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-23
 * @since 1.0.0
 */
public class MyPow16 {
    public static void main(String[] args) {
        System.out.println(myPow2(2.00000, 10));
    }

    public static double myPow(double x, int n) {
        if (x == 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 1 / myPow(x, -n);
        }
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result *= x;
        }
        return result;
    }

    public static double myPow2(double x, int n) {
        if (x == 0) {
            throw new IllegalArgumentException();
        }
        if (n == 0) {
            return 1;
        }
        long e=n;
        if (n < 0) {
            x=1/x;
            e=-e;
        }
        double result = 1;
        while (e > 0) {
            if ((e & 1) == 1) {
                result *= x;
            }
            x *= x;
            e >>= 1;
        }
        return result;
    }
}
