package util;

import lombok.Value;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@Value
public class Random {
    java.util.Random delegate = new java.util.Random();

    public int nextPositiveInt() {
        return nextPositiveInt(Integer.MAX_VALUE);
    }

    public int nextPositiveInt(int bound) {
        return nextInt(0, bound);
    }

    public int nextInt() {
        return delegate.nextInt();
    }

    public int nextInt(int randomNumberOrigin, int randomNumberBound) {
        if (randomNumberBound == Integer.MAX_VALUE) {
            if (randomNumberOrigin == Integer.MIN_VALUE) {
                return delegate.nextInt();
            }
            return delegate.ints(1, randomNumberOrigin - 1, randomNumberBound).sum() + 1;
        }
        return delegate.ints(1, randomNumberOrigin, randomNumberBound + 1).sum();
    }

    public Stream<Integer> nextInts(int size, int randomNumberOrigin, int randomNumberBound) {
        return nextIntSet(size, randomNumberOrigin, randomNumberBound).stream();
    }

    public int[] nextIntArray(int size) {
        Set<Integer> ints = nextIntSet(size);
        int[] array = new int[ints.size()];
        int i = 0;
        for (int num : ints) {
            array[i++] = num;
        }
        return array;
    }

    public Set<Integer> nextIntSet(int size) {
        return nextIntSet(size, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public Set<Integer> nextIntSet(int size, int randomNumberOrigin, int randomNumberBound) {
        if (size < 0) {
            throw new IllegalArgumentException("size " + size + " must grater or equals 0");
        }
        int needBound = randomNumberOrigin + (size - 1);
        if (needBound < randomNumberOrigin || needBound > randomNumberBound) {
            throw new IllegalArgumentException("size " + size + " too large for[" + randomNumberOrigin + "," + randomNumberBound + "]");
        }

        long rangeSize = 1L + randomNumberBound - randomNumberOrigin;
        if (size > rangeSize) {
            throw new IllegalArgumentException("size " + size + " must less or equals " + rangeSize);
        }

        TreeSet<Integer> result = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            int value = nextInt(randomNumberOrigin, randomNumberBound);
            for (int selected : result) {
                if (value < selected) {
                    break;
                }
                value++;
            }
            result.add(value);
            randomNumberBound--;
        }
        return result;
    }

    public char nextElement(char[] chars) {
        return chars[nextPositiveInt(chars.length - 1)];
    }

    public int nextElement(int[] ints) {
        return ints[nextPositiveInt(ints.length - 1)];
    }

    public <T> T nextElement(List<T> list) {
        return list.get(nextPositiveInt(list.size() - 1));
    }

    public Stream<Character> nextElements(char[] chars, int size) {
        return nextInts(size, 0, chars.length - 1)
                .map(i -> chars[i]);
    }

    public <T> Stream<T> nextElements(T[] array, int size) {
        return nextInts(size, 0, array.length - 1)
                .map(i -> array[i]);
    }

    public <T> Stream<T> nextElements(List<T> list, int size) {
        return nextInts(size, 0, size - 1)
                .map(list::get);
    }
}
