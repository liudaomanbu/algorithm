package base.tree;

import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-04
 * @since 1.0.0
 */
public interface Bfs {
    <T> void bfs(Node<T> node, Consumer<Node<T>> visitor);
}
