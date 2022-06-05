package base.graph.sort.topology;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class TopologySortDfsStack2Test extends TopologySortTest {

    @Override
    protected TopologySort topologySort() {
        return new TopologySortDfsStack2();
    }

}