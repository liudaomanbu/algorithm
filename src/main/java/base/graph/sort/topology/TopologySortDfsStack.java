package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public class TopologySortDfsStack implements TopologySort {
    @Override
    public <T> List<Node<T>> sortedTopology(Graph<T> graph) {
        Stack<Node<T>> zeroInStack = new Stack<>();
        Map<Node<T>, Integer> nodeToIn = new HashMap<>();
        for (Node<T> node : graph.nodes) {
            nodeToIn.put(node, node.in);
            if (node.in == 0) {
                zeroInStack.push(node);
            }
        }

        List<Node<T>> result = new ArrayList<>();
        while (!zeroInStack.isEmpty()) {
            Node<T> node = zeroInStack.pop();
            result.add(node);
            for (Node<T> nextNode : node.nextNodes()) {
                int curIn = nodeToIn.get(nextNode) - 1;
                nodeToIn.put(nextNode, curIn);
                if (curIn == 0) {
                    zeroInStack.push(nextNode);
                }
            }
        }
        return result;
    }
}
