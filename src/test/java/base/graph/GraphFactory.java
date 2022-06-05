package base.graph;

import com.google.common.collect.Sets;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import util.Random;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * @author caotc
 * @date 2022-05-07
 * @since 1.0.0
 */
@Value
@Builder(toBuilder = true)
public class GraphFactory<T> {
    Random random = new Random();
    @Builder.Default
    int minPoints = 5;
    @Builder.Default
    int maxPoints = 100;
    @Builder.Default
    int pointMinEdges = 0;
    @Builder.Default
    int pointMaxEdges = 5;
    int cycleProbability = 50;
    Supplier<T> pointValueFactory;
    @Getter(lazy = true)
    Supplier<Node<T>> pointFactory = () -> new Node<>(pointValueFactory.get());
    EdgeFactory<T> edgeFactory;

    @Value
    public static class EdgeFactory<T> {
        Random random = new Random();
        int minWeight;
        int maxWeight;

        public EdgeFactory() {
            this(0, 0);
        }

        public EdgeFactory(int minWeight, int maxWeight) {
            this.minWeight = minWeight;
            this.maxWeight = maxWeight;
        }

        public Edge<T> next(Node<T> from, Node<T> to) {
            return new Edge<>(from, to, random.nextInt(minWeight, maxWeight));
        }
    }

    public Graph<T> next() {
        int pointSize = random.nextInt(minPoints,maxPoints);
        Set<Node<T>> nodes = Sets.newHashSet();
        while (nodes.size() < pointSize) {
            nodes.add(pointFactory().get());
        }

        Set<Edge<T>> edges = Sets.newHashSet();
        Queue<Node<T>> queue = new LinkedList<>(nodes);
        while (!queue.isEmpty()) {
            Node<T> from = queue.poll();
            int pointEdgeSize = nextPointEdgeSize(nodes);
            List<Node<T>> toRange = nodes.stream().filter((p) -> !from.equals(p)).collect(Collectors.toList());
            Set<Edge<T>> nextEdges = random.nextElements(toRange, pointEdgeSize)
                    .map(to -> edgeFactory.next(from, to))
                    .collect(Collectors.toSet());
            edges.addAll(nextEdges);
        }

        return new Graph<>(edges);
    }

    private int nextPointEdgeSize(Set<? extends Node<?>> points) {
        int pointMinEdges = Math.min(points.size() - 1, this.pointMinEdges);
        int pointMaxEdges = Math.min(points.size() - 1, this.pointMaxEdges);
        return random.nextInt(pointMinEdges, pointMaxEdges);
    }
}
