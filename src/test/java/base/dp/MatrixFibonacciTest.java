package base.dp;

import base.dp.MatrixFibonacci;
import base.dp.RecursionFibonacci;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.Random;

@Slf4j
public class MatrixFibonacciTest {

    @RepeatedTest(1000)
    public void test() {
        Random random = new Random();
        int n = random.nextInt(40);
        MatrixFibonacci matrixFibonacci = new MatrixFibonacci();
        int actual = matrixFibonacci.fibonacci(n);
        log.debug("n:{},actual:{}", n, actual);
        int expected = new RecursionFibonacci().fibonacci(n);
        log.debug("n:{},expected:{}", n, expected);
        Assertions.assertEquals(expected, actual, String.format("n:%s,expected:%s,actual:%s", n, expected, actual));
    }

}