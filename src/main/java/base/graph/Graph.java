package base.graph;

import com.google.common.collect.Sets;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;
import java.util.Set;

/**
 * @author caotc
 * @date 2022-05-06
 * @since 1.0.0
 */
@EqualsAndHashCode
@ToString
public class Graph<T> {
    public Set<Node<T>> nodes = Sets.newHashSet();
    public Set<Edge<T>> edges = Sets.newHashSet();

    public Graph(Collection<Edge<T>> edges) {
        addEdges(edges);
    }

    public void addEdges(Collection<Edge<T>> edges) {
        for (Edge<T> edge : edges) {
            addEdge(edge);
        }
    }

    public void addEdge(Edge<T> edge) {
        nodes.add(edge.from);
        nodes.add(edge.to);

        edges.add(edge);
        edge.from.addEdge(edge);
        edge.to.in++;
    }
}
