package base.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

import java.util.Set;

@Slf4j
public abstract class KTimesNumTest {

    @RepeatedTest(5000)
    public void findKTimesNum() {
        Random random = random();
        int m = random.nextInt(2, 10);
        int k = random.nextInt(1, m - 1);
        int size = random.nextInt(2, 20);
        int[] array = new int[(size - 1) * m + k];
        int i = 0;
        int kTimesNum = 0;
        Set<Integer> ints = random.nextIntSet(size);
        for (int num : ints) {
            if (i == 0) {
                kTimesNum = num;
            }
            int times = i == 0 ? k : m;
            for (int j = 1; j <= times; j++) {
                array[i++] = num;
            }
        }
        for (int j = 0; j < array.length; j++) {
            int rand = random.nextInt(j, array.length - 1);
            int temp = array[j];
            array[j] = array[rand];
            array[rand] = temp;
        }
        log.debug("m:{}", m);
        log.debug("k:{}", k);
        log.debug("array:{}", array);
        log.debug("expected:{}", kTimesNum);

        KTimesNum instance = instance();
        int actual = instance.findKTimesNum(array, k, m);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(kTimesNum, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract KTimesNum instance();
}