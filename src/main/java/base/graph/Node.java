package base.graph;

import lombok.Getter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author caotc
 * @date 2022-05-06
 * @since 1.0.0
 */
@Getter
public class Node<T> {
    public T value;
    public int in;
    public int out;
    public Set<Node<T>> nextNodes;
    public Set<Edge<T>> nextEdges;

    public Node(T value) {
        this.value = value;
        this.in = 0;
        this.out = 0;
        this.nextNodes = new HashSet<>();
        this.nextEdges = new HashSet<>();
    }

    public void addEdge(Edge<T> edge) {
        if (edge.from == this) {
            nextEdges.add(edge);
            nextNodes.add(edge.to);
            out++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Point{" +
                "value=" + value +
                '}';
    }
}
