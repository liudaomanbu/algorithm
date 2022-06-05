package base.linked;

/**
 * @author caotc
 * @date 2022-04-10
 * @since 1.0.0
 */
public interface Reverse {
    <T> Node<T> reverse(Node<T> node);

    <T> DoubleNode<T> reverse(DoubleNode<T> node);
}
