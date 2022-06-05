package base.linked;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
@Slf4j
public class ReverseRecursionTest extends ReverseTest {

    @Override
    protected Reverse getReverse() {
        return ReverseRecursion.INSTANCE;
    }
}
