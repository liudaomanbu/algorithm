package base.stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.IntArrayRandom;

import java.util.Collections;
import java.util.LinkedList;

@Slf4j
public abstract class GetMinStackTest {
    @RepeatedTest(5000)
    public void test() {
        IntArrayRandom random = random();
        int[] array = random.next();
        LinkedList<Integer> list = new LinkedList<>();
        GetMinStack<Integer> instance = instance();
        for (int num : array) {
            list.push(num);
            instance.push(num);
        }
        log.debug("array:{}", array);
        while (!instance.isEmpty()) {
            Integer getMinExpected = Collections.min(list);
            Integer getMinActual = instance.getMin();
            log.debug("getMinExpected:{}", getMinExpected);
            log.debug("getMinActual:{}", getMinActual);
            Assertions.assertEquals(getMinExpected, getMinActual);

            Integer expected = list.pop();
            Integer actual = instance.pop();
            log.debug("expected:{}", expected);
            log.debug("actual:{}", actual);
            Assertions.assertEquals(expected, actual);
        }
    }

    public IntArrayRandom random() {
        return IntArrayRandom.builder().build();
    }

    protected abstract GetMinStack<Integer> instance();
}