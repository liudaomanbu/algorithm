package leetcode.offer;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class CuttingRope14II {
    public static void main(String[] args) {
        System.out.println(cuttingRope2(120));
    }

    public static int cuttingRope(int n) {
        if (n < 2) {
            throw new IllegalArgumentException();
        }
        BigInteger[] dp = new BigInteger[n + 1];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ZERO;
        dp[2] = BigInteger.ONE;
        for (int i = 3; i < dp.length; i++) {
            for (int j = 2; j < i; j++) {
                if (Objects.isNull(dp[i])) {
                    dp[i] = BigInteger.ZERO;
                }
                BigInteger cuttingAllResult = dp[i - j].multiply(BigInteger.valueOf(j));
                BigInteger cuttingOnceResult = BigInteger.valueOf(j).multiply(BigInteger.valueOf((i - j)));
                BigInteger cuttingResult = cuttingAllResult.compareTo(cuttingOnceResult) > 0 ? cuttingAllResult : cuttingOnceResult;
                dp[i] = cuttingResult.compareTo(dp[i]) > 0 ? cuttingResult : dp[i];
            }
        }
        return dp[n].remainder(BigInteger.valueOf(1000000007)).intValue();
    }

    public static int cuttingRope2(int n) {
        if (n < 2) {
            throw new IllegalArgumentException();
        }
        if(n==2){
            return 1;
        }
        if(n==3){
            return 2;
        }
        int a = n % 3;
        int b = n / 3;
        if (a == 1) {
            b -= 1;
        }
        BigInteger result = BigInteger.valueOf(3).pow(b);
        if (a == 1) {
            result = result.multiply(BigInteger.valueOf(4));
        }
        if (a == 2) {
            result = result.multiply(BigInteger.valueOf(2));
        }
        return result.remainder(BigInteger.valueOf(1000000007)).intValue();
    }

    public static int cuttingRope3(int n) {
        if(n <= 3) {
            return n - 1;
        }
        int b = n % 3, p = 1000000007;
        //todo 快速幂求余
        long rem = 1, x = 3;
        for(int a = n / 3 - 1; a > 0; a /= 2) {
            if(a % 2 == 1) {
                rem = (rem * x) % p;
            }
            x = (x * x) % p;
        }
        if(b == 0) {
            return (int)(rem * 3 % p);
        }
        if(b == 1) {
            return (int)(rem * 4 % p);
        }
        return (int)(rem * 6 % p);
    }
}
