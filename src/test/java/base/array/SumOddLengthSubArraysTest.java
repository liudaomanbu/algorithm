package base.array;

import leetcode.SumOddLengthSubArrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class SumOddLengthSubArraysTest {

    @Test
    void solution() {
        int result = SumOddLengthSubArrays.solution(new int[]{1, 4, 2, 5, 3});
        log.info("result:{}",result);
    }
}