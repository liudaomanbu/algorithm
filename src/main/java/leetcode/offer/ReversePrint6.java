package leetcode.offer;

/**
 * @author caotc
 * @date 2021-04-21
 * @since 1.0.0
 */
public class ReversePrint6 {
    public static void main(String[] args) {

    }

    public static int[] reversePrint(ListNode head) {
        int size=0;
        ListNode now=head;
        while (now!=null){
            size++;
            now=now.next;
        }
        int[] result=new int[size];
        now=head;
        for(int i=size-1;i>=0;i--){
            result[i]=now.val;
            now=now.next;
        }
        return result;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}
