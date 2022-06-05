package base.linked;

/**
 * @author caotc
 * @date 2022-04-10
 * @since 1.0.0
 */
public class Node<T> {
    public T value;
    public Node<T> next;

    public Node(T data) {
        value = data;
    }
}
