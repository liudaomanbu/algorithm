package base.dp;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public class MatrixFibonacci implements Fibonacci {
    @Override
    public int fibonacci(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = new int[][]{
                {1, 1},
                {1, 0}
        };
        int[][] matrix = pow(base, n - 2);

        return fibonacci(2) * matrix[0][0] + fibonacci(1) * matrix[1][0];
    }

    private int[][] pow(int[][] matrix, int pow) {
        //矩阵的1
        int[][] result = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < result.length; i++) {
            result[i][i] = 1;
        }

        int[][] temp = matrix;
        for (int p = pow; p != 0; p >>= 1) {
            //该二进制位上为1
            if ((p & 1) == 1) {
                result = multiply(result, temp);
            }
            temp = multiply(temp, temp);
        }
        return result;
    }

    private int[][] multiply(int[][] matrix1, int[][] matrix2) {
        int[][] result = new int[matrix1.length][matrix1[0].length];
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2[0].length; j++) {
                for (int k = 0; k < matrix1[0].length; k++) {
                    //乘积C的第m行第n列的元素等于矩阵A的第m行的元素与矩阵B的第n列对应元素乘积之和。
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
}
