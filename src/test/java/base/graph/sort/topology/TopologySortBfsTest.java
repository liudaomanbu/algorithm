package base.graph.sort.topology;

import lombok.extern.slf4j.Slf4j;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class TopologySortBfsTest extends TopologySortTest{

    @Override
    protected TopologySort topologySort() {
        return new TopologySortBfs();
    }
}