package base.graph;

import java.util.Map;

/**
 * @author caotc
 * @date 2022-06-08
 * @since 1.0.0
 */
public interface MinDistance {
    <E> Map<Node<E>, Integer> targetNodeToMinDistance(Node<E> head, int size);
}
