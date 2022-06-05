package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class CuttingRope14I {
    public static void main(String[] args) {
        System.out.println(cuttingRope2(4));
    }

    public static int cuttingRope(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException();
        }
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i < dp.length; i++) {
            for (int j = 2; j < i; j++) {
                int cuttingAllResult = j * dp[i - j];
                int cuttingOnceResult = j * (i - j);
                int cuttingResult = Math.max(cuttingAllResult, cuttingOnceResult);
                dp[i] = Math.max(cuttingResult, dp[i]);
            }
        }
        return dp[n];
    }

    public static int cuttingRope2(int n) {
        if (n <= 1) {
            throw new IllegalArgumentException();
        }
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        int a = n % 3;
        int exponent = n/3;
        if(a==1){
            exponent-=1;
        }
        int result = (int) Math.pow(3, exponent);
        if(a==1){
            result*=4;
        }
        if(a==2){
            result*=2;
        }
        return result;
    }
}
