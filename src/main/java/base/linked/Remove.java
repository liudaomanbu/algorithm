package base.linked;

import java.util.Objects;

/**
 * @author caotc
 * @date 2022-04-11
 * @since 1.0.0
 */
public class Remove {
    public <T> Node<T> remove(Node<T> node,T value){
        Node<T> head=node;
        // head来到第一个不需要删的位置
        while (head != null) {
            if (!Objects.equals(head.value,value)) {
                break;
            }
            head = head.next;
        }

        //node的pre节点
        Node<T> pre=null;
        Node<T> cur=node;
        while (cur!=null){
            if(Objects.equals(cur.value,value)){
                if(pre!=null){
                    pre.next=cur.next;
                }
            }

            pre=cur;
            cur=cur.next;
        }
        return head;
    }

    public <T> DoubleNode<T> remove(DoubleNode<T> node,T value){
        DoubleNode<T> head=node;
        // head来到第一个不需要删的位置
        while (head != null) {
            if (!Objects.equals(head.value,value)) {
                break;
            }
            head = head.next;
        }

        DoubleNode<T> cur=node;
        while (cur!=null){
            if(Objects.equals(cur.value,value)){
                if(cur.pre!=null){
                    cur.pre.next=cur.next;
                }
                if(cur.next!=null){
                    cur.next.pre=cur.pre;
                }
            }

            cur=cur.next;
        }
        return head;
    }
}
