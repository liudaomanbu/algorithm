package base.graph.bfs;

import base.graph.Node;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public class BfsRecursion implements Bfs {
    @Override
    public <T> void traversal(Node<T> node, Consumer<Node<T>> visitor) {
        List<Node<T>> nodes = new LinkedList<>();
        nodes.add(node);
        traversal(nodes, visitor, null);
    }

    private <T> void traversal(Collection<Node<T>> nodes, Consumer<Node<T>> visitor, Set<Node<T>> visited) {
        if (nodes == null || nodes.isEmpty()) {
            return;
        }
        visited = visited == null ? new HashSet<>() : visited;
        List<Node<T>> nextNodes = new LinkedList<>();
        for (Node<T> node : nodes) {
            visitor.accept(node);

            visited.add(node);
            for (Node<T> nextNode : node.nextNodes()) {
                if (!visited.contains(nextNode)) {
                    nextNodes.add(nextNode);
                    /*
                      必须在加入nextNodes集合时就认为访问过,否则nodes中的下一层节点如果重复则会被重复加入nextNodes,导致重复访问
                      不能通过用set装nextNodes解决,因为nextNodes只是当前层节点的下一层节点,不同层节点的nextNodes之间依然可能存在重复访问
                     */
                    visited.add(nextNode);
                }
            }
        }
        traversal(nextNodes, visitor, visited);
    }
}
