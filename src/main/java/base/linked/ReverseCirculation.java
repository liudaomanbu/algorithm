package base.linked;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReverseCirculation implements Reverse {
    public static final ReverseCirculation INSTANCE = new ReverseCirculation();

    @Override
    public <T> Node<T> reverse(Node<T> node) {
        //node的pre节点
        Node<T> pre=null;
        //node的next节点
        Node<T> next;
        while (node!=null){
            //先记录原next节点
            next=node.next;
            //当前节点指向原pre节点,完成反转
            node.next=pre;

            //移动到下一个节点
            pre=node;
            node=next;
        }
        return pre;
    }

    @Override
    public <T> DoubleNode<T> reverse(DoubleNode<T> node) {
        //node的pre节点
        DoubleNode<T> pre=null;
        //node的next节点
        DoubleNode<T> next;
        while (node!=null){
            //先记录原next节点
            next=node.next;
            //当前节点next指向原pre节点,pre指向原next节点,完成反转
            node.next=pre;
            node.pre=next;

            //移动到下一个节点
            pre=node;
            node=next;
        }
        return pre;
    }
}
