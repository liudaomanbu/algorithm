package base.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.IntArrayRandom;

@Slf4j
public abstract class ArrayLeftSumTest {
    @RepeatedTest(5000)
    public void arrayLeftSum() {
        IntArrayRandom intArrayRandom = intRandom();
        int[] array = intArrayRandom.next();
        log.debug("array:{}", array);
        int expected = new ArrayLeftSumDefault().arrayLeftSum(array);
        log.debug("expected:{}", expected);
        ArrayLeftSum instance = instance();
        int actual = instance.arrayLeftSum(array);
        log.debug("actual:{}", actual);
        Assertions.assertEquals(expected, actual);
    }

    public IntArrayRandom intRandom() {
        return IntArrayRandom.builder().minLength(1).maxLength(20).build();
    }

    protected abstract ArrayLeftSum instance();
}