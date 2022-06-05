package base.string;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.CharRandom;
import util.Random;
import util.StringRandom;

@Slf4j
public abstract class IndexOfTest {

    @RepeatedTest(5000)
    public void indexOf() {
        Random random=new Random();
        StringRandom stringRandom = stringRandom();
        String str = stringRandom.next();
        String childStr = str.substring(random.nextPositiveInt(str.length()-1));
        log.debug("str:{}", str);
        log.debug("childStr:{}", childStr);
        int expected = str.indexOf(childStr);
        log.debug("expected:{}", expected);
        IndexOf indexOf = instance();
        int result = indexOf.indexOf(str, childStr);
        log.debug("result:{}", result);
        Assertions.assertEquals(expected, result);
    }

    protected StringRandom stringRandom() {
        return StringRandom.builder().minLength(20)
                .maxLength(50)
                .charSupplier(CharRandom.builder().charType(CharRandom.CharType.SMALL_LETTER).build()::next)
                .build();
    }

    protected abstract IndexOf instance();
}