package base.bit;

/**
 * @author caotc
 * @date 2022-05-13
 * @since 1.0.0
 */
public class Calculator1 implements Calculator {
    @Override
    public int add(int num1, int num2) {
        //无进位相加结果
        int result = num1 ^ num2;
        //进位结果
        int b = (num1 & num2) << 1;
        while (b != 0) {
            num1 = result;
            num2 = b;
            result = num1 ^ num2;
            b = (num1 & num2) << 1;
        }
        return result;
    }

    @Override
    public int subtract(int num1, int num2) {
        return add(num1, add(~num2, 1));
    }

    @Override
    public int multiply(int num1, int num2) {
        int result = 0;
        for (int i = 0; i < 32; i = add(i, 1)) {
            if (((num2 >> i) & 1) != 0) {
                result = add(result, num1 << i);
            }
        }
        return result;
    }

    @Override
    public int divide(int num1, int num2) {
        int dividend = num1 > 0 ? num1 : add(~num1, 1);
        int divisor = num2 > 0 ? num2 : add(~num2, 1);
        int result = 0;
        for (int i = 31; i >= 0; i = subtract(i, 1)) {
            if ((dividend >> i) >= divisor) {
                result = add(result, 1 << i);

                dividend = subtract(dividend, divisor << i);
            }
        }
        return (num1 ^ num2) < 0 ? add(~result, 1) : result;
    }
}
