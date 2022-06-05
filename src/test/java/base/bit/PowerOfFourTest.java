package base.bit;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

import java.util.List;

@Slf4j
public abstract class PowerOfFourTest {
    @RepeatedTest(5000)
    public void isPowerOfFour() {
        List<Integer> powerOfFours = Lists.newArrayList();
        int powerOfTwo = 1;
        for (int i = 0; i < 31; i += 2) {
            powerOfFours.add(powerOfTwo << i);
        }
        Random random = random();
        int num = random.nextInt();
        num = num >= 0 ? num : random.nextElement(powerOfFours);
        log.debug("num:{}", num);
        boolean expected = powerOfFours.contains(num);
        log.debug("expected:{}", expected);

        PowerOfFour instance = instance();
        boolean actual = instance.isPowerOfFour(num);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract PowerOfFour instance();
}