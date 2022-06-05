package base.other;

import java.util.Arrays;

/**
 * 完美洗牌算法
 * 给定一个数组a1，a2，a3， …， an， b1， b2， b3， ...， bn
 * 最终把它置换成b1， a1， b2， a2， a3， b3，…， bn， an
 * @author caotc
 * @date 2022-05-03
 * @since 1.0.0
 */
public interface Shuffle {
    /**
     * 给定一个数组a1，a2，a3， …， an， b1， b2， b3， ...， bn
     * 最终把它置换成b1， a1， b2， a2， a3， b3，…， bn， an
     * @param array 长度必定为偶数的数组
     */
    <T> void shuffle(T[] array);

    default int[] shuffle(int[] array){
        Integer[] results = Arrays.stream(array).boxed().toArray(Integer[]::new);
        shuffle(results);
        return Arrays.stream(results).mapToInt(Integer::intValue).toArray();
    }
}
