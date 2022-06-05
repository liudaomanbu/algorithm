package base.graph;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author caotc
 * @date 2022-05-06
 * @since 1.0.0
 */
@EqualsAndHashCode
@ToString
public class Edge<T> {
    @EqualsAndHashCode.Exclude
    public int weight;
    public Node<T> from;
    public Node<T> to;

    public Edge(Node<T> from, Node<T> to) {
        this.weight = 0;
        this.from = from;
        this.to = to;
    }

    public Edge(Node<T> from, Node<T> to, int weight) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
