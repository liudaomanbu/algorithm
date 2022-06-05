package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-23
 * @since 1.0.0
 */
public class HammingWeight15 {
    public static void main(String[] args) {

    }
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int count=0;
        while (n!=0){
            n = n & (n-1);
            count++;
        }
        return count;
    }
}
