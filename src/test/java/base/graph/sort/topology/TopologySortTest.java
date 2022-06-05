package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.GraphFactory;
import base.graph.Node;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.CharRandom;

import java.util.List;

@Slf4j
public abstract class TopologySortTest {

    @RepeatedTest(5000)
    public void sortedTopology() {
        GraphFactory<Character> graphFactory = graphFactory();
        Graph<Character> graph = graphFactory.next();
        log.debug("graph nodes:{}", graph.nodes);
        log.debug("graph edge:{}", graph.edges);

        TopologySort topologySort = topologySort();
        List<Node<Character>> result = topologySort.sortedTopology(graph);
        boolean hasCycle = topologySort.hasCycle(graph);
        log.debug("result:{}", result);
        log.debug("result hasCycle:{}", hasCycle);
        Assertions.assertEquals(Sets.newHashSet(result).size(), result.size(), "node duplicate traversal");

        for (Node<Character> node : result) {
            Assertions.assertEquals(0, node.in, "sort error");
            node.nextNodes().forEach(n -> n.in--);
            graph.nodes.remove(node);
        }
        for (Node<Character> node : graph.nodes) {
            log.debug("node:{}",node);
            Assertions.assertNotEquals(0, node.in, "node traversal leave out");
        }
        Assertions.assertEquals(!graph.nodes.isEmpty(), hasCycle, "hasCycle check error");
    }

    protected abstract TopologySort topologySort();

    protected GraphFactory<Character> graphFactory() {
        return GraphFactory.<Character>builder()
                .maxPoints(26)
                .pointValueFactory(CharRandom.builder().charType(CharRandom.CharType.CAPITAL_LETTER).build()::next)
                .edgeFactory(new GraphFactory.EdgeFactory<>())
                .build();
    }

}