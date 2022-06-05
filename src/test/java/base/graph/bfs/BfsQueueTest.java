package base.graph.bfs;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class BfsQueueTest extends BfsTest{

    @Override
    protected Bfs bfs() {
        return new BfsQueue();
    }
}