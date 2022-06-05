package base.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.IntArrayRandom;

@Slf4j
public abstract class ReversePairCountTest {
    @RepeatedTest(5000)
    public void reversePairCount() {
        IntArrayRandom intArrayRandom = intRandom();
        int[] array = intArrayRandom.next();
        log.debug("array:{}", array);
        int expected = new ReversePairCountDefault().reversePairCount(array);
        log.debug("expected:{}", expected);
        ReversePairCount instance = instance();
        int actual = instance.reversePairCount(array);
        log.debug("actual:{}", actual);
        Assertions.assertEquals(expected, actual);
    }

    public IntArrayRandom intRandom() {
        return IntArrayRandom.builder().minLength(1).maxLength(20).build();
    }

    protected abstract ReversePairCount instance();
}