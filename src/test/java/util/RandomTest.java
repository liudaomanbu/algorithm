package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

@Slf4j
class RandomTest {
    @RepeatedTest(5000)
    public void nextInt() {
        Random random = new Random();
        int max = random.nextPositiveInt();
        int min = random.nextPositiveInt(max);
        int value = random.nextInt(min, max);
        log.debug("min:{},max:{},value:{}", min, max, value);
        Assertions.assertTrue(value >= min && value <= max);
    }
}