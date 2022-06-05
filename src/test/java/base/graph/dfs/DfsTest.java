package base.graph.dfs;

import base.graph.Graph;
import base.graph.GraphFactory;
import base.graph.Node;
import base.graph.bfs.Bfs;
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
import java.util.Stack;

@Slf4j
public abstract class DfsTest {

    @RepeatedTest(5000)
    public void traversal() {
        GraphFactory<Character> graphFactory = graphFactory();
        Graph<Character> graph = graphFactory.next();
        log.debug("graph nodes:{}", graph.nodes);
        log.debug("graph edge:{}", graph.edges);
        Dfs dfs = dfs();
        for (Node<Character> node : graph.nodes) {
            log.debug("node:{}", node);
            List<Node<Character>> result = Lists.newLinkedList();
            dfs.traversal(node, result::add);
            log.debug("result:{}", result);
            Assertions.assertEquals(Sets.newHashSet(result).size(), result.size(), "node duplicate traversal");
            List<Node<Character>> expected = dfs(node);
            log.debug("expected:{}", expected);
            Assertions.assertEquals(expected.size(), result.size(), "node traversal leave out");
            Assertions.assertIterableEquals(expected, result, "node traversal sequence error");
        }
    }


    protected abstract Dfs dfs();

    protected GraphFactory<Character> graphFactory() {
        return GraphFactory.<Character>builder()
                .maxPoints(26)
                .pointValueFactory(CharRandom.builder().charType(CharRandom.CharType.CAPITAL_LETTER).build()::next)
                .edgeFactory(new GraphFactory.EdgeFactory<>())
                .build();
    }

    private <T> List<Node<T>> dfs(Node<T> node) {
        if (node == null) {
            return null;
        }
        LinkedList<Node<T>> result = Lists.newLinkedList();

        Stack<Node<T>> stack = new Stack<>();
        HashSet<Node<T>> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        result.add(node);
        while (!stack.isEmpty()) {
            Node<T> cur = stack.pop();
            for (Node<T> next : cur.nextNodes()) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    result.add(next);
                    break;
                }
            }
        }
        return result;
    }
}