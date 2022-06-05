package base.tree;

import lombok.extern.slf4j.Slf4j;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@Slf4j
public class TraversalRecursionTest extends TraversalTest{

    @Override
    protected Traversal get() {
        return TraversalRecursion.INSTANCE;
    }

}
