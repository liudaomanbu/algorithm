package leetcode.offer;

import java.util.HashMap;

/**
 * @author caotc
 * @date 2021-04-22
 * @since 1.0.0
 */
public class BuildTree7 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        int[] preorder={3,9,20,15,7};
        int[] inorder={9,3,15,20,7};
        buildTree(preorder,inorder);
    }
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> valueToInorderIndex = new HashMap<>();
        for(int i = 0; i < inorder.length; i++) {
            valueToInorderIndex.put(inorder[i], i);
        }
        return recur(0, 0, inorder.length - 1,preorder,valueToInorderIndex);
    }

    public static TreeNode recur(int root, int left, int right,int[] preorder, HashMap<Integer, Integer> valueToInorderIndex) {
        if(left > right) {
            return null;                          // 递归终止
        }
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int rootInorderIndex = valueToInorderIndex.get(preorder[root]);                       // 划分根节点、左子树、右子树
        node.left = recur(root + 1, left, rootInorderIndex - 1,preorder,valueToInorderIndex);              // 开启左子树递归
        node.right = recur(root + rootInorderIndex - left + 1, rootInorderIndex + 1, right,preorder,valueToInorderIndex); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }
}
