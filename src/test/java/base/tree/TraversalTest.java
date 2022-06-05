package base.tree;

import base.tree.Node;
import base.tree.Traversal;
import base.tree.TraversalRecursion;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caotc
 * @date 2022-04-12
 * @since 1.0.0
 */
@Slf4j
public abstract class TraversalTest {
    private static final Traversal DEFAULT= TraversalRecursion.INSTANCE;

    protected abstract Traversal get();

    @Test
    public void pre() {
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(2);
        head.right = new Node<>(3);
        head.left.left = new Node<>(4);
        head.left.right = new Node<>(5);
        head.right.left = new Node<>(6);
        head.right.right = new Node<>(7);

        List<Node<Integer>> expected=new ArrayList<>();
        DEFAULT.pre(head,expected::add);
        log.debug("expected:{}",expected);

        List<Node<Integer>> actual=new ArrayList<>();
        get().pre(head,actual::add);
        log.debug("actual:{}",actual);
        Assertions.assertEquals(expected,actual,String.format("expected:%s,actual:%s",expected,actual));
    }

    @Test
    public void in() {
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(2);
        head.right = new Node<>(3);
        head.left.left = new Node<>(4);
        head.left.right = new Node<>(5);
        head.right.left = new Node<>(6);
        head.right.right = new Node<>(7);

        List<Node<Integer>> expected=new ArrayList<>();
        DEFAULT.in(head,expected::add);
        log.debug("expected:{}",expected);

        List<Node<Integer>> actual=new ArrayList<>();
        get().in(head,actual::add);
        log.debug("actual:{}",actual);
        Assertions.assertEquals(expected,actual,String.format("expected:%s,actual:%s",expected,actual));
    }

    @Test
    public void pos() {
        Node<Integer> head = new Node<>(1);
        head.left = new Node<>(2);
        head.right = new Node<>(3);
        head.left.left = new Node<>(4);
        head.left.right = new Node<>(5);
        head.right.left = new Node<>(6);
        head.right.right = new Node<>(7);

        List<Node<Integer>> expected=new ArrayList<>();
        DEFAULT.post(head,expected::add);
        log.debug("expected:{}",expected);

        List<Node<Integer>> actual=new ArrayList<>();
        get().post(head,actual::add);
        log.debug("actual:{}",actual);
        Assertions.assertEquals(expected,actual,String.format("expected:%s,actual:%s",expected,actual));
    }
}
