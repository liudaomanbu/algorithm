package base.graph.dfs;

import base.graph.Node;
import base.graph.dfs.Dfs;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public class DfsRecursion implements Dfs {
    @Override
    public <T> void traversal(Node<T> node, Consumer<Node<T>> visitor) {
        traversal(node, visitor, new HashSet<>());
    }

    private <T> void traversal(Node<T> node, Consumer<Node<T>> visitor, Set<Node<T>> visited) {
        if (!visited.contains(node)) {
            visitor.accept(node);

            visited.add(node);
            for (Node<T> curNode : node.nextNodes()) {
                traversal(curNode, visitor, visited);
            }
        }
    }
}
