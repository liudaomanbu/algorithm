package base.array;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

@Slf4j
public abstract class LocalMinTest {
    @RepeatedTest(5000)
    public void localMinIndex() {
        Random random = random();
        int[] array = random.nextIntArray(random.nextPositiveInt(50));
        for (int j = 0; j < array.length; j++) {
            int rand = random.nextInt(j, array.length - 1);
            int temp = array[j];
            array[j] = array[rand];
            array[rand] = temp;
        }
        log.debug("array:{}", array);

        LocalMin instance = instance();
        int actual = instance.localMinIndex(array);
        log.debug("actual:{}", actual);
        if (actual == -1) {
            Assertions.assertEquals(0, array.length);
        } else {
            if (actual != 0) {
                Assertions.assertTrue(array[actual] < array[actual - 1]);
            }
            if (actual != array.length - 1) {
                Assertions.assertTrue(array[actual] < array[actual + 1]);
            }
        }
    }

    public Random random() {
        return new Random();
    }

    protected abstract LocalMin instance();
}