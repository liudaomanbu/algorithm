package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author caotc
 * @date 2021-04-29
 * @since 1.0.0
 */
public class HasCycle {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
        ListNode head=new ListNode(1);
        ListNode now=head;
        ListNode cycleStart=null;
        while (now.next==null && now.val<5){
            now.next=new ListNode(now.val+1);
            if(now.val==3){
                cycleStart=now.next;
            }
            now=now.next;
        }
        now.next=cycleStart;
        print(head);
        System.out.println();
        print(reverseList(head));
    }

    public static ListNode reverseList(ListNode head) {
        //新链表
        ListNode newHead = null;
        while (head != null) {
            //先保存访问的节点的下一个节点，保存起来
            //留着下一步访问的
            ListNode temp = head.next;
            //每次访问的原链表节点都会成为新链表的头结点，
            //其实就是把新链表挂到访问的原链表节点的
            //后面就行了
            head.next = newHead;
            //更新新链表
            newHead = head;
            //重新赋值，继续访问
            head = temp;
        }
        //返回新链表
        return newHead;
    }

    public static void print(ListNode head){
        Set<Integer> visited=new HashSet<>();
        ListNode now=head;
        while (now!=null){
            if(visited.contains(now.val)){
                System.out.println("cycle to "+now.val);
                break;
            }else{
                System.out.println(now.val);
                visited.add(now.val);
                now=now.next;
            }
        }
    }
}
