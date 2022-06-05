package leetcode.offer;

import java.util.Objects;

/**
 * @author caotc
 * @date 2021-04-23
 * @since 1.0.0
 */
public class DeleteNode18 {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }

    public ListNode deleteNode(ListNode head, int val) {
        if(head.val==val){
            return head.next;
        }
        ListNode pre=head;
        ListNode now=head.next;
        while (Objects.nonNull(now)){
            if(now.val==val){
                pre.next=now.next;
                break;
            }
            pre=now;
            now=now.next;
        }
        return head;
    }
}
