package base.graph.bfs;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class BfsRecursionTest extends BfsTest {

    @Override
    protected Bfs bfs() {
        return new BfsRecursion();
    }
}