package base.graph.bfs;

import base.graph.Node;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
@Slf4j
public class BfsQueue implements Bfs {
    @Override
    public <T> void traversal(Node<T> node, Consumer<Node<T>> visitor) {
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(node);

        Set<Node<T>> visited = new HashSet<>();
        while (!queue.isEmpty()) {
            Node<T> curNode = queue.poll();
            if (!visited.contains(curNode)) {
                visitor.accept(curNode);

                visited.add(curNode);
                queue.addAll(curNode.nextNodes);
            }
        }
    }
}
