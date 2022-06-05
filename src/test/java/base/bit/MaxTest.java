package base.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

@Slf4j
public abstract class MaxTest {
    @RepeatedTest(5000)
    public void max() {
        Random random = random();
        int a = random.nextInt();
        int b = random.nextInt();
        log.debug("a:{}", a);
        log.debug("b:{}", b);
        int expected = Math.max(a, b);
        log.debug("expected:{}", expected);

        Max instance = instance();
        int actual = instance.max(a, b);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract Max instance();
}