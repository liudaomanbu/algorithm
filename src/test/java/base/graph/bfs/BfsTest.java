package base.graph.bfs;

import base.graph.Graph;
import base.graph.GraphFactory;
import base.graph.Node;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import util.CharRandom;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Slf4j
public abstract class BfsTest {

    @RepeatedTest(5000)
    public void traversal() {
        GraphFactory<Character> graphFactory = graphFactory();
        Graph<Character> graph = graphFactory.next();
        log.debug("graph nodes:{}", graph.nodes);
        log.debug("graph edge:{}", graph.edges);
        Bfs bfs = bfs();
        for (Node<Character> node : graph.nodes) {
            log.debug("node:{}", node);
            List<Node<Character>> result = Lists.newLinkedList();
            bfs.traversal(node, result::add);
            log.debug("result:{}", result);
            Assertions.assertEquals(Sets.newHashSet(result).size(), result.size(), "node duplicate traversal");
            List<Node<Character>> expected = bfs(node);
            log.debug("expected:{}", expected);
            Assertions.assertEquals(expected.size(), result.size(), "node traversal leave out");
            Assertions.assertIterableEquals(expected, result, "node traversal sequence error");
        }
    }

    protected abstract Bfs bfs();

    protected GraphFactory<Character> graphFactory() {
        return GraphFactory.<Character>builder()
                .maxPoints(26)
                .pointValueFactory(CharRandom.builder().charType(CharRandom.CharType.CAPITAL_LETTER).build()::next)
                .edgeFactory(new GraphFactory.EdgeFactory<>())
                .build();
    }

    private <T> List<Node<T>> bfs(Node<T> start) {
        if (start == null) {
            return null;
        }

        List<Node<T>> result = Lists.newLinkedList();
        Queue<Node<T>> queue = new LinkedList<>();
        HashSet<Node<T>> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node<T> cur = queue.poll();
            result.add(cur);
            for (Node<T> next : cur.nextNodes()) {
                if (!set.contains(next)) {
                    set.add(next);
                    queue.add(next);
                }
            }
        }

        return result;
    }
}