package base.linked;

import base.linked.Node;

import java.util.function.Supplier;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
public class NodeFactory {
    public static Node<Integer> generateIntegerNode(int maxLength, int maxValue){
        return generateIntegerNode((int) (Math.random() * (maxLength + 1)),()->(int) (Math.random() * (maxValue + 1)));
    }

    public static <T> Node<T> generateIntegerNode(int length, Supplier<T> supplier){
        int size = (int) (Math.random() * (length + 1));
        if (size == 0) {
            return null;
        }
        size--;
        Node<T> head = new Node<>(supplier.get());
        Node<T> pre = head;
        while (size != 0) {
            Node<T> cur = new Node<>(supplier.get());
            pre.next = cur;
            pre = cur;
            size--;
        }
        return head;
    }
}
