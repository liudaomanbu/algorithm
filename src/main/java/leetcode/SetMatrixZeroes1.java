package leetcode;

/**
 * @author caotc
 * @date 2022-05-11
 * @since 1.0.0
 */
public class SetMatrixZeroes1 implements SetMatrixZeroes {
    @Override
    public void setZeroes(int[][] matrix) {
        //第0列是否设置为0
        boolean zeroColumnSet = false;

        for (int row = 0; row < matrix.length; row++) {
            for (int cloumn = 0; cloumn < matrix[row].length; cloumn++) {
                if (matrix[row][cloumn] == 0) {
                    //使用第0列标记哪些行需要设为0
                    matrix[row][0] = 0;
                    if (cloumn == 0) {
                        //[0,0]归属0列标记0行是否需要设为0,所以0列是否需要设为0使用zeroColumnSet记录
                        zeroColumnSet = true;
                    } else {
                        //使用第0行标记哪些列需要设为0
                        matrix[0][cloumn] = 0;
                    }
                }
            }
        }

        //扫描0行,设置对应列的0.
        //[0,0]归属0列标记0行是否需要设为0
        for (int column = 1; column < matrix[0].length; column++) {
            if (matrix[0][column] == 0) {
                for (int row = 1; row < matrix.length; row++) {
                    matrix[row][column] = 0;
                }
            }
        }

        //扫描0列,设置对应行的0
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][0] == 0) {
                for (int column = 1; column < matrix[row].length; column++) {
                    matrix[row][column] = 0;
                }
            }
        }

        if (zeroColumnSet) {
            for (int row = 0; row < matrix.length; row++) {
                matrix[row][0] = 0;
            }
        }
    }
}
