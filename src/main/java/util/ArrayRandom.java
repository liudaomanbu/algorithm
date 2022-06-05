package util;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.function.Supplier;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@SuperBuilder(toBuilder = true)
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public abstract class ArrayRandom {
    @Builder.Default
    Random random=new Random();
    @Builder.Default
    int minLength = 0;
    @Builder.Default
    int maxLength = 20;

    protected int nextLength() {
        return random.nextInt(minLength,maxLength);
    }
}
