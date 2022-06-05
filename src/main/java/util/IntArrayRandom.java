package util;

import lombok.Builder;
import lombok.experimental.SuperBuilder;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
public class IntArrayRandom extends ArrayRandom {
    @Builder.Default
    int minValue = 0;
    @Builder.Default
    int maxValue = 100;
//    @Builder.Default
//    Supplier<Integer> valueSupplier = () -> new Random().nextInt(0, 100);

    protected int nextValue() {
        return random().nextInt(minValue, maxValue);
    }

    public int[] next() {
        int[] result = new int[nextLength()];
        for (int i = 0; i < result.length; i++) {
            result[i] = nextValue();
        }
        return result;
    }
}
