package leetcode.offer;

import java.util.Objects;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class MovingCount13 {
    public static void main(String[] args) {
        System.out.println(movingCount2(16, 8, 4));
    }

    public static int movingCount2(int m, int n, int k) {
        return dfs(m,n,k,0,0,new boolean[m][n]);
    }

    public static int dfs(int m, int n, int k,int row,int col,boolean[][] visited){
        if(row>=m || col>=n || (total(row)+total(col))>k || visited[row][col]){
            return 0;
        }
        visited[row][col]=true;
        return 1+dfs(m,n,k,row+1,col,visited)+dfs(m,n,k,row,col+1,visited);
    }

    public static int movingCount1(int m, int n, int k) {
        int count = 0;
        boolean[][] flag = new boolean[m][n];
        flag[0][0] = true;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if(total(row)+total(col)>k){
                    continue;
                }
                if(row-1>=0){
                    flag[row][col]|=flag[row-1][col];
                }
                if(col-1>=0){
                    flag[row][col]|=flag[row][col-1];
                }
                if(flag[row][col]){
                    count++;
                }
            }
        }
        return count;
    }


    public static int total(int num) {
        int total = 0;
        while (num!=0){
            total+=num%10;
            num/=10;
        }
        return total;
    }
}
