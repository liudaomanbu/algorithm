package base.cache;

import base.cache.Cache;
import base.cache.Lru;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
public class LfuTest {
    @Test
    public void test() {
        int size = 3;
        Cache<Integer, Integer> lru = new Lru<>(size);
        Random random = new Random();
        lru.put(1, random.nextInt());
        lru.put(2, random.nextInt());
        lru.put(3, random.nextInt());
        log.debug("key:{},value:{}", 1, lru.get(1));
        log.debug("key:{},value:{}", 2, lru.get(2));
        log.debug("key:{},value:{}", 3, lru.get(3));
        lru.put(4, random.nextInt());
        log.debug("key:{},value:{}", 1, lru.get(1));
        log.debug("key:{},value:{}", 4, lru.get(4));
        lru.get(2);
        lru.put(5,random.nextInt());
        log.debug("key:{},value:{}", 2, lru.get(2));
        log.debug("key:{},value:{}", 3, lru.get(3));
        log.debug("key:{},value:{}", 5, lru.get(5));
    }
}