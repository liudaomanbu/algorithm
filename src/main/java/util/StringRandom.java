package util;

import com.google.common.collect.Sets;
import lombok.Builder;
import lombok.Value;

import java.math.BigInteger;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@Value
@Builder(toBuilder = true)
public class StringRandom {
    Random random = new Random();
    @Builder.Default
    int minLength = 0;
    @Builder.Default
    int maxLength = 10;
//    CharRandom charRandom;
    Supplier<Character> charSupplier;

    public String next() {
        int length = nextLength();
        char[] result = new char[length];
        for (int i = 0; i < result.length; i++) {
            result[i] = charSupplier.get();
        }
        return String.valueOf(result);
    }

//    public Set<String> nextSet(int size) {
//        BigInteger maxSize = BigInteger.valueOf(charRandom().chars().size()).pow(maxLength);
//        if (maxSize.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0
//                || maxSize.intValue() > size) {
//            throw new IllegalArgumentException("size " + size + " must less or equals " + maxSize);
//        }
//        Set<String> result = Sets.newHashSet();
//        while (result.size() < size) {
//            result.add(next());
//        }
//        return result;
//    }

    private int nextLength() {
        if (minLength == maxLength) {
            return minLength;
        }
        return random.nextInt(minLength, maxLength);
    }
}
