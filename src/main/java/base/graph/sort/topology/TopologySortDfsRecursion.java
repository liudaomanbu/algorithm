package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public class TopologySortDfsRecursion implements TopologySort {
    @Override
    public <T> List<Node<T>> sortedTopology(Graph<T> graph) {
        List<Node<T>> result = new ArrayList<>();
        Map<Node<T>, Integer> nodeToIn = graph.nodes.stream().collect(Collectors.toMap(Function.identity(), Node::in));
        for (Node<T> node : graph.nodes) {
            if (node.in == 0) {
                sortedTopology(node, nodeToIn, result);
            }
        }
        return result;
    }

    public <T> void sortedTopology(Node<T> node, Map<Node<T>, Integer> nodeToIn, List<Node<T>> result) {
        result.add(node);
        for (Node<T> nextNode : node.nextNodes()) {
            nodeToIn.put(nextNode, nodeToIn.get(nextNode) - 1);
            if (nodeToIn.get(nextNode) == 0) {
                sortedTopology(nextNode, nodeToIn, result);
            }
        }
    }
}
