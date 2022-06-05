package base.graph.dfs;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class DfsStackTest extends DfsTest{

    @Override
    protected Dfs dfs() {
        return new DfsStack();
    }
}