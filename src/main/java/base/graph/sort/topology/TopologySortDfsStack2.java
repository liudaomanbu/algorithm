package base.graph.sort.topology;

import base.graph.Graph;
import base.graph.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
@Slf4j
public class TopologySortDfsStack2 implements TopologySort {
    @Override
    public <T> List<Node<T>> sortedTopology(Graph<T> graph) {
        List<Node<T>> result = new ArrayList<>();
        Map<Node<T>, Integer> nodeToIn = graph.nodes.stream().collect(Collectors.toMap(Function.identity(), Node::in));
        Stack<Node<T>> stack = new Stack<>();
        for (Node<T> node : graph.nodes) {
            if (node.in == 0) {
                stack.push(node);
            }
        }

        //模拟递归
        while (!stack.isEmpty()) {
            Node<T> curNode = stack.pop();
            if (curNode != null) {
                //参数压栈模拟递归调用方法
                for (Node<T> nextNode : curNode.nextNodes()) {
                    int curIn = nodeToIn.get(nextNode) - 1;
                    nodeToIn.put(nextNode, curIn);
                    if (nodeToIn.get(nextNode) == 0) {
                        stack.push(nextNode);
                    }
                }
                //模拟本次递归时对当前节点的方法调用
                stack.push(curNode);
                //标记本次递归当前节点入栈,弹出为null时下一个即为本次递归当前节点节点,需要进行操作
                stack.push(null);
            } else {
                result.add(stack.pop());
            }
        }
        return result;
    }
}
