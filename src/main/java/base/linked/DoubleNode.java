package base.linked;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
public class DoubleNode<T> {
    public T value;
    public DoubleNode<T> pre;
    public DoubleNode<T> next;

    public DoubleNode(T data) {
        value = data;
    }
}
