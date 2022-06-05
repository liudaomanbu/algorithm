package base.sort;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.IntArrayRandom;

import java.util.Arrays;

@Slf4j
public abstract class SortTest {

    @RepeatedTest(5000)
    public void sortInt() {
        IntArrayRandom intArrayRandom = intRandom();
        int[] array = intArrayRandom.next();
        int[] expected = Arrays.copyOf(array, array.length);
        Arrays.sort(expected);
        log.debug("expected:{}", expected);
        Sort instance = instance();
        instance.sortInt(array);
        log.debug("actual:{}", Arrays.toString(array));
        Assertions.assertArrayEquals(expected, array);
    }

    public IntArrayRandom intRandom() {
        return IntArrayRandom.builder().minLength(1).maxLength(20).build();
    }

    protected abstract Sort instance();
}