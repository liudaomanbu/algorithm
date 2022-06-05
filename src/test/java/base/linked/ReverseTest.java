package base.linked;

import base.linked.DoubleNode;
import base.linked.Node;
import base.linked.Reverse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author caotc
 * @date 2022-04-10
 * @since 1.0.0
 */
@Slf4j
public abstract class ReverseTest {
    DoubleNode<Integer> generateIntegerDoubleNode(int maxLength, int maxValue){
        return generateIntegerDoubleNode((int) (Math.random() * (maxLength + 1)),()->(int) (Math.random() * (maxValue + 1)));
    }

    <T> DoubleNode<T> generateIntegerDoubleNode(int length, Supplier<T> supplier){
        int size = (int) (Math.random() * (length + 1));
        if (size == 0) {
            return null;
        }
        size--;
        DoubleNode<T> head = new DoubleNode<>(supplier.get());
        DoubleNode<T> pre = head;
        while (size != 0) {
            DoubleNode<T> cur = new DoubleNode<>(supplier.get());
            pre.next = cur;
            cur.pre = pre;
            pre = cur;
            size--;
        }
        return head;
    }

     Node<Integer> generateIntegerNode(int maxLength, int maxValue){
         return generateIntegerNode((int) (Math.random() * (maxLength + 1)),()->(int) (Math.random() * (maxValue + 1)));
     }

    <T> Node<T> generateIntegerNode(int length, Supplier<T> supplier){
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

     <T> List<T> getOriginOrderList(Node<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    <T> List<T> getOriginOrderList(DoubleNode<T> head) {
        List<T> ans = new ArrayList<>();
        while (head != null) {
            ans.add(head.value);
            head = head.next;
        }
        return ans;
    }

    <T> boolean checkLinkedListReverse(List<T> origin, Node<T> head) {
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    <T> boolean checkDoubleListReverse(List<T> origin, DoubleNode<T> head) {
        DoubleNode<T> end = null;
        for (int i = origin.size() - 1; i >= 0; i--) {
            if (!origin.get(i).equals(head.value)) {
                return false;
            }
            end = head;
            head = head.next;
        }
        for (int i = 0; i < origin.size(); i++) {
            if (!origin.get(i).equals(end.value)) {
                return false;
            }
            end = end.pre;
        }
        return true;
    }

    protected abstract Reverse getReverse();

    @RepeatedTest(1000)
    public void test(){
        Node<Integer> linkedListHead = generateIntegerNode(50, 100);
        List<Integer> originList = getOriginOrderList(linkedListHead);
        linkedListHead = getReverse().reverse(linkedListHead);
        if (!checkLinkedListReverse(originList, linkedListHead)) {
            log.error("originList:{}",originList);
            log.error("linkedList:{}", getOriginOrderList(linkedListHead));
        }
    }

    @RepeatedTest(1000)
    public void testDoubleNode(){
        DoubleNode<Integer> linkedListHead = generateIntegerDoubleNode(50, 100);
        List<Integer> originList = getOriginOrderList(linkedListHead);
        linkedListHead = getReverse().reverse(linkedListHead);
        if (!checkDoubleListReverse(originList, linkedListHead)) {
            log.error("originList:{}",originList);
            log.error("linkedList:{}", getOriginOrderList(linkedListHead));
        }
    }
}
