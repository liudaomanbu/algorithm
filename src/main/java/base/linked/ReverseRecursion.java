package base.linked;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author caotc
 * @date 2022-04-10
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReverseRecursion implements Reverse {

    public static final ReverseRecursion INSTANCE = new ReverseRecursion();

    @Override
    public <T> Node<T> reverse(Node<T> node) {
        if (node == null || node.next == null) {
            return node;
        }
        //返回值为后续节点反转后的头节点,即当前的尾节点
        Node<T> tail = reverse(node.next);
        //此时当前节点指向next没变,即反转后的尾节点,将其next指向自己,把自己设为尾节点
        node.next.next = node;
        //自己为尾节点时next指向null
        node.next = null;
        return tail;
    }

    @Override
    public <T> DoubleNode<T> reverse(DoubleNode<T> node) {
        if (node == null || node.next == null) {
            return node;
        }
        //返回值为后续节点反转后的头节点,即当前的尾节点
        DoubleNode<T> tail = reverse(node.next);

        //此时当前节点指向next没变,即反转后的尾节点,将其next指向自己,把自己设为尾节点
        node.next.next = node;
        node.pre=node.next;
        //自己为尾节点时next指向null
        node.next = null;
        return tail;
    }
}
