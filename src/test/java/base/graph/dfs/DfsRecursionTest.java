package base.graph.dfs;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class DfsRecursionTest extends DfsTest {

    @Override
    protected Dfs dfs() {
        return new DfsRecursion();
    }
}