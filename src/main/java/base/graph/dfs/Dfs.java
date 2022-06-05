package base.graph.dfs;

import base.graph.Node;

import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-05-09
 * @since 1.0.0
 */
public interface Dfs {
    /**
     * 从node出发深度遍历
     *
     * @param node    出发点
     * @param visitor 遍历需要执行的操作
     */
    <T> void traversal(Node<T> node, Consumer<Node<T>> visitor);
}
