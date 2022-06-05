package util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

@Slf4j
class IntArrayRandomTest {
    @RepeatedTest(5000)
    public void next() {
        Random random = new Random();
        int length1 = random.nextPositiveInt(100);
        int length2 = random.nextPositiveInt(100);
        int maxLength = Math.max(length1, length2);
        int minLength = Math.min(length1, length2);
        int value1 = random.nextInt();
        int value2 = random.nextInt();
        int maxValue = Math.max(value1, value2);
        int minValue = Math.min(value1, value2);
        IntArrayRandom intArrayRandom = IntArrayRandom.builder()
                .minValue(minValue)
                .maxValue(maxValue)
                .minLength(minLength)
                .maxLength(maxLength)
                .build();

        int[] next = intArrayRandom.next();
        log.debug("minLength:{},maxLength:{},next.length:{}", minLength, maxLength, next.length);
        log.debug("minValue:{},maxValue:{},next:{}", minValue, maxValue, next);
        Assertions.assertTrue(next.length >= minLength && next.length <= maxLength);
        for (int i : next) {
            Assertions.assertTrue(i >= minValue && i <= maxValue);
        }
    }
}