package base.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

@Slf4j
public abstract class CalculatorTest {
    @RepeatedTest(5000)
    public void add() {
        Random random = random();
        int a = random.nextInt();
        int b = random.nextInt();
        log.debug("a:{}", a);
        log.debug("b:{}", b);
        int expected = a + b;
        log.debug("expected:{}", expected);

        Calculator instance = instance();
        int actual = instance.add(a, b);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }

    @RepeatedTest(5000)
    public void subtract() {
        Random random = random();
        int a = random.nextInt();
        int b = random.nextInt();
        log.debug("a:{}", a);
        log.debug("b:{}", b);
        int expected = a - b;
        log.debug("expected:{}", expected);

        Calculator instance = instance();
        int actual = instance.subtract(a, b);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }


    @RepeatedTest(5000)
    public void multiply() {
        Random random = random();
        int a = random.nextInt();
        int b = random.nextInt();
        log.debug("a:{}", a);
        log.debug("b:{}", b);
        int expected = a * b;
        log.debug("expected:{}", expected);

        Calculator instance = instance();
        int actual = instance.multiply(a, b);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }


    @RepeatedTest(5000)
    public void divide() {
        Random random = random();
        int a = 2022349698;
        int b = -420390944;
        log.debug("a:{}", a);
        log.debug("b:{}", b);
        int expected = a / b;
        log.debug("expected:{}", expected);

        Calculator instance = instance();
        int actual = instance.divide(a, b);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract Calculator instance();
}