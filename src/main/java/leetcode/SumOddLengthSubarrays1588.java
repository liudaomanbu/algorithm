package leetcode;

/**
 * @author caotc
 * @date 2021-04-20
 * @since 1.0.0
 */
public class SumOddLengthSubarrays1588 {

    public static void main(String[] args) {
        int[] arr=new int[]{1,4,2,5,3};
        System.out.println(sumOddLengthSubarrays2(arr));
    }

    /**
     * 暴力解法
     * @param arr
     * @return
     */
    public static int sumOddLengthSubarrays2(int[] arr) {
        int total=0;
        for(int i=0;i<arr.length;i++){
            int left=i+1;
            int right=arr.length-i;
            int leftEven=(left+1)/2;
            int leftOdd=left/2;
            int rightEven=(right+1)/2;
            int rightOdd=right/2;
            total+=arr[i] * (leftEven * rightEven + leftOdd * rightOdd);
        }
        return total;
    }

    /**
     * 暴力解法
     * @param arr
     * @return
     */
    public static int sumOddLengthSubarrays1(int[] arr) {
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
