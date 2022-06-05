package base.tree;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
public class Node<T> {
    public T value;
    public Node<T> left;
    public Node<T> right;

    public Node(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
