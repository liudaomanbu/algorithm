package base.linked;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
public class ReverseCirculationTest extends ReverseTest{
    @Override
    protected Reverse getReverse() {
        return ReverseCirculation.INSTANCE;
    }
}
