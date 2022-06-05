package base.tree;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraversalRecursion implements Traversal {

    public static final TraversalRecursion INSTANCE = new TraversalRecursion();

    @Override
    public <T> void pre(Node<T> node, Consumer<Node<T>> visitor) {
        if (node == null) {
            return;
        }
        visitor.accept(node);
        pre(node.left, visitor);
        pre(node.right, visitor);
    }

    @Override
    public <T> void in(Node<T> node, Consumer<Node<T>> visitor) {
        if (node == null) {
            return;
        }
        in(node.left, visitor);
        visitor.accept(node);
        in(node.right, visitor);
    }

    @Override
    public <T> void post(Node<T> node, Consumer<Node<T>> visitor) {
        if (node == null) {
            return;
        }
        post(node.left, visitor);
        post(node.right, visitor);
        visitor.accept(node);
    }

}
