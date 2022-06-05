package base.tree;

import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
public interface Traversal {
    <T> void pre(Node<T> node, Consumer<Node<T>> visitor);
    <T> void in(Node<T> node, Consumer<Node<T>> visitor);
    <T> void post(Node<T> node, Consumer<Node<T>> visitor);
    default <T> void dfs(Node<T> node, Consumer<Node<T>> visitor){
        pre(node,visitor);
    }
}
