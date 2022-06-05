package base.bit;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

@Slf4j
public abstract class TwoOddTimesNumTest {
    @RepeatedTest(5000)
    public void findOddTimesNum() {
        Random random = random();
        int[] array = new int[random.nextInt(1, 50) * 2];
        int[] timesArray = new int[]{2, 4, 6, 8};
        int i = 0;
        int oddTimesNum1 = 0;
        int oddTimesNum2 = 0;
        while (i < array.length) {
            int num = random.nextInt();
            int times = random.nextElement(timesArray);
            if (times >= array.length - i) {
                times = array.length - i;
                oddTimesNum1 = num;
                oddTimesNum2 = random.nextInt();
                array[i++] = oddTimesNum2;
                times--;
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
        HashSet<Integer> expected = Sets.newHashSet(oddTimesNum1, oddTimesNum2);
        log.debug("array:{}", array);
        log.debug("expected:{}", expected);

        TwoOddTimesNum instance = instance();
        int[] actual = instance.findOddTimesNum(array);
        log.debug("actual:{}", actual);
        Assertions.assertEquals(expected, Arrays.stream(actual).boxed().collect(Collectors.toSet()));
    }

    public Random random() {
        return new Random();
    }

    protected abstract TwoOddTimesNum instance();
}