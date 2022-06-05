package base.bit;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

import java.util.List;
import java.util.Set;

@Slf4j
public abstract class PowerOfTwoTest {
    @RepeatedTest(5000)
    public void isPowerOfTwo() {
        List<Integer> powerOfTwos = Lists.newArrayList();
        int powerOfTwo = 1;
        for (int i = 0; i < 31; i++) {
            powerOfTwos.add(powerOfTwo << i);
        }
        Random random = random();
        int num = random.nextInt();
        num = num >= 0 ? num : random.nextElement(powerOfTwos);
        log.debug("num:{}", num);
        boolean expected = powerOfTwos.contains(num);
        log.debug("expected:{}", expected);

        PowerOfTwo instance = instance();
        boolean actual = instance.isPowerOfTwo(num);
        log.debug("actual:{}", actual);

        Assertions.assertEquals(expected, actual);
    }

    public Random random() {
        return new Random();
    }

    protected abstract PowerOfTwo instance();
}