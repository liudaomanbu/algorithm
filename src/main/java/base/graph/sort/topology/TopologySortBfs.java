package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * @author caotc
 * @date 2022-05-06
 * @since 1.0.0
 */
public class TopologySortBfs implements TopologySort {
    @Override
    public <T> List<Node<T>> sortedTopology(Graph<T> graph) {
        //为了不影响原数据中的入度
        Map<Node<T>, Integer> pointToCurrentIn = new HashMap<>();
        Queue<Node<T>> zeroInNodes = new LinkedList<>();
        for (Node<T> node : graph.nodes) {
            pointToCurrentIn.put(node, node.in);
            if (node.in == 0) {
                zeroInNodes.add(node);
            }
        }

        List<Node<T>> result = new ArrayList<>();
        while (!zeroInNodes.isEmpty()) {
            Node<T> node = zeroInNodes.poll();
            result.add(node);
            for (Node<T> nextNode : node.nextNodes) {
                int currentIn = pointToCurrentIn.get(nextNode) - 1;
                pointToCurrentIn.put(nextNode, currentIn);
                if (currentIn == 0) {
                    zeroInNodes.add(nextNode);
                }
            }
        }
        return result;
    }
}
