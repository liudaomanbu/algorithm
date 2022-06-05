package leetcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;

@Slf4j
public class LFUCacheTest {
    @Test
    public void test() {
//["LFUCache","put","put","get","put","get","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]
        int size = 2;
        LFUCache cache = new LFUCache(size);
        Random random = new Random();
        cache.put(1, 1);
        cache.put(2, 2);
        log.debug("key:{},value:{}", 1, cache.get(1));
        cache.put(3, 3);
        log.debug("key:{},value:{}", 2, cache.get(2));
        log.debug("key:{},value:{}", 3, cache.get(3));
        cache.put(4, 4);
        log.debug("key:{},value:{}", 1, cache.get(1));
        log.debug("key:{},value:{}", 3, cache.get(3));
        log.debug("key:{},value:{}", 4, cache.get(4));
    }
}