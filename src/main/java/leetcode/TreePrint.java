package leetcode;

/**
 * @author caotc
 * @date 2021-04-27
 * @since 1.0.0
 */
public class TreePrint {
    public static class TreeNode<T>{
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> head=new TreeNode<>(10);
        head.left=new TreeNode<>(8);
        head.left.left=new TreeNode<>(7);
        head.left.right=new TreeNode<>(9);
        head.right=new TreeNode<>(15);
        head.right.left=new TreeNode<>(13);
        head.right.right=new TreeNode<>(18);

        print1(head);
    }

    public static <T> void print(TreeNode<T> node){
        if(node==null){
            return;
        }
        System.out.println(node.value);
        print(node.left);
        print(node.right);
    }

    public static <T> void print1(TreeNode<T> head){
        if (head == null) {
            return;
        }
        TreeNode<T> now=head;
        TreeNode<T> left=null;
        while (now != null) {
            left = now.left;
            if (left != null) {
                while (left.right != null && left.right != now) {
                    left = left.right;
                }
                if (left.right == null) {
                    left.right = now;
                    System.out.println(now.value);
                    now = now.left;
                    continue;
                } else {
                    left.right = null;
                }
            } else {
                System.out.println(now.value);
            }
            now = now.right;
        }
    }
}
