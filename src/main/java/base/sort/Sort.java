package base.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2021-03-23
 * @since 1.0.0
 */
@Slf4j
public abstract class Sort {

    public abstract <T extends Comparable<T>> void sort(T[] nums);

    public abstract void sortInt(int[] nums);

    protected <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    protected <T extends Comparable<T>> boolean lessOrEqual(T v, T w) {
        return v.compareTo(w) <= 0;
    }

    protected <T extends Comparable<T>> boolean equal(T v, T w) {
        return v.compareTo(w) == 0;
    }

    protected <T extends Comparable<T>> boolean greater(T v, T w) {
        return v.compareTo(w) > 0;
    }

    protected <T extends Comparable<T>> boolean greaterOrEqual(T v, T w) {
        return v.compareTo(w) >= 0;
    }

    protected <T> void swap(T[] a, int i, int j) {
        if (i != j) {
            T t = a[i];
            a[i] = a[j];
            a[j] = t;
        }
    }

    protected <T> void swap(int[] a, int i, int j) {
        if (i != j) {
            a[i] = a[i] ^ a[j];
            a[j] = a[i] ^ a[j];
            a[i] = a[i] ^ a[j];
        }
    }
}