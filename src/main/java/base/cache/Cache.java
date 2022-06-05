package base.cache;

/**
 * @author caotc
 * @date 2022-05-05
 * @since 1.0.0
 */
public interface Cache<K, V> {
    V get(K key);

    V put(K key, V value);

    int maxSize();
}
