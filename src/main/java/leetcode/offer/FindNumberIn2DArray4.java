package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-21
 * @since 1.0.0
 */
public class FindNumberIn2DArray4 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 4, 7, 11, 15},
                new int[]{2, 5, 8, 12, 19},
                new int[]{3, 6, 9, 16, 22},
                new int[]{10, 13, 14, 17, 24},
                new int[]{18, 20, 23, 26, 30}
        };
        System.out.println(findNumberIn2DArray1(matrix,20));
    }

    public static boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if(matrix==null || matrix.length==0 || matrix[0].length==0){
            return false;
        }
        int row=0;
        int col=matrix[0].length-1;
        while (row<matrix.length && col>=0){
            int value=matrix[row][col];
            System.out.println(row+","+col);
            if(value==target){
                return true;
            }
            if(value>target){
                col--;
            }else{
                row++;
            }
        }
        return false;
    }
}
