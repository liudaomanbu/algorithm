package base.tree;

import base.tree.Bfs1;
import base.tree.Bfs2;
import base.tree.Node;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@Slf4j
public class BfsTest {
    @Test
    public void test() {
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(2);
        head.right = new Node<>(3);
        head.left.left = new Node<>(4);
        head.left.right = new Node<>(5);
        head.right.left = new Node<>(6);
        head.right.right = new Node<>(7);

        List<Node<Integer>> expected = new ArrayList<>();
        new Bfs1().bfs(head, System.out::println);
        System.out.println();
        new Bfs2().bfs(head, System.out::println);
    }
}
