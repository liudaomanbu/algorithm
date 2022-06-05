package base.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

@Slf4j
public abstract class OneOddTimesNumTest {

    @RepeatedTest(5000)
    public void findOddTimesNum() {
        Random random = random();
        int[] array = new int[random.nextInt(1, 50) * 2 + 1];
        int i = 0;
        int oddTimesNum = 0;
        while (i < array.length) {
            int num = random.nextInt();
            int times = random.nextInt(1, 4) * 2;
            if (times > array.length - i) {
                times = array.length - i;
                oddTimesNum = num;
            }
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
        log.debug("array:{}", array);
        log.debug("expected:{}", oddTimesNum);

        OneOddTimesNum instance = instance();
        int actual = instance.findOddTimesNum(array);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(oddTimesNum, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract OneOddTimesNum instance();
}