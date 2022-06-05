package base.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.CharRandom;
import util.StringRandom;

@Slf4j
public abstract class MaxPalindromeTest {

    @RepeatedTest(5000)
    public void maxPalindromeLength() {
        StringRandom stringRandom = stringRandom();
        String str = stringRandom.next();
        log.debug("str:{}", str);
        int expected = manacher(str);
        log.debug("expected:{}", expected);
        MaxPalindrome instance = instance();
        int result = instance.maxPalindromeLength(str);
        log.debug("result:{}", result);
        Assertions.assertEquals(expected, result);
    }

    protected StringRandom stringRandom() {
        return StringRandom.builder().minLength(20)
                .maxLength(50)
                .charSupplier(CharRandom.builder().charType(CharRandom.CharType.SMALL_LETTER).build()::next)
                .build();
    }

    protected abstract MaxPalindrome instance();

    protected static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];
        int C = -1;
        // 讲述中：R代表最右的扩成功的位置
        // coding：最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) { // 0 1 2
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    protected static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
}