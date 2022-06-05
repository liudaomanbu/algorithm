package leetcode.offer;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * @author caotc
 * @date 2021-04-23
 * @since 1.0.0
 */
public class PrintNumbers17 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers2(6)));
    }
    public static int[] printNumbers(int n) {
        int[] result=new int[(int) Math.pow(10,n)-1];
        for(int i=0;i<result.length;i++){
            result[i]=i+1;
        }
        return result;
    }

    public static int[] printNumbers2(int n) {
        int[] result=new int[(int) Math.pow(10,n)-1];
        IntStream.range(0,result.length).parallel()
                .forEach(i->result[i]=i+1);
        return result;
    }
}
