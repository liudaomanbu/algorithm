package base.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.IntArrayRandom;
import util.Random;

import java.util.Arrays;

@Slf4j
public abstract class CeilingTest {
    @RepeatedTest(5000)
    public void search() {
        IntArrayRandom intArrayRandom = intRandom();
        Random random = random();
        int[] array = intArrayRandom.next();
        Arrays.sort(array);
        log.debug("array:{}", array);
        int num = random.nextInt(0, 100);
        log.debug("num:{}", num);
        Ceiling instance = instance();
        int actual = instance.search(array, num);
        log.debug("actual:{}", actual);
        if (actual == -1) {
            Assertions.assertTrue(array[array.length - 1] < num);
        } else {
            Assertions.assertTrue(array[actual] >= num && (actual == 0 || array[actual - 1] < num));
        }
    }

    public IntArrayRandom intRandom() {
        return IntArrayRandom.builder().minLength(1).maxLength(20).build();
    }

    public Random random() {
        return new Random();
    }

    protected abstract Ceiling instance();
}