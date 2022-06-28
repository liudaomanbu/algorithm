package base.sort;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2021-03-23
 * @since 1.0.0
 */
@Slf4j
public abstract class Sort {
    public abstract void sortInt(int[] nums);

    protected <T> void swap(int[] a, int i, int j) {
        if (i != j) {
            a[i] = a[i] ^ a[j];
            a[j] = a[i] ^ a[j];
            a[i] = a[i] ^ a[j];
        }
    }
}