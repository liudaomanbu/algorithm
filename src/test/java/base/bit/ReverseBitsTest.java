package base.bit;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.Random;

@Slf4j
public abstract class ReverseBitsTest {

    @RepeatedTest(5000)
    public void reverse() {
        Random random = random();
        int value = random.nextInt();
        log.debug("value:{}",value);
        ReverseBits reverseBits = reverseBits();
        int reversed = reverseBits.reverse(value);
        log.debug("reversed:{}",reversed);
        Assertions.assertTrue(reversed(value, reversed));
    }

    protected boolean reversed(int num1, int num2) {
        char[] chars1 = binaryString(num1);
        char[] chars2 = binaryString(num2);
        log.debug("value binaryString:{}",chars1);
        log.debug("reversed binaryString:{}",chars2);
        return reversed(chars1, chars2);
    }

    protected boolean reversed(char[] num1, char[] num2) {
        if (num1.length != num2.length) {
            return false;
        }
        for (int i = 0; i < num1.length; i++) {
            if (num1[i] != num2[num2.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    protected char[] binaryString(int num) {
        char[] chars = new char[32];
        for (int i = chars.length - 1; i >= 0; i--) {
            chars[i] = (num & 1) == 1 ? '1' : '0';
            num >>= 1;
        }
        return chars;
    }

    protected abstract ReverseBits reverseBits();

    protected Random random() {
        return new Random();
    }
}