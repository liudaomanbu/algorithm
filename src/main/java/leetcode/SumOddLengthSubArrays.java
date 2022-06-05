package leetcode;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author caotc
 * @date 2021-04-09
 * @since 1.0.0
 */
@Slf4j
public class SumOddLengthSubArrays {
    public static int solution(int[] arr) {
        int total=0;
        for(int length=1;length<=arr.length;length++){
            if(length%2!=0){
                for(int start=0;start+length<=arr.length;start++){
                    for(int j=start;j<start+length;j++){
                        total+=arr[j];
                    }
                }
            }
        }
        return total;
    }
}
