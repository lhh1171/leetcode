package Bycategory.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class Solution222 {
    // 通用递归解法
    public int countNodes1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return countNodes1(root.left) + countNodes1(root.right) + 1;
    }

    // 迭代法
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                result++;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return result;
    }

    /**
     * 针对完全二叉树的解法
     * <p>
     * 满二叉树的结点数为：2^depth - 1
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode left = root.left;
        TreeNode right = root.right;
        int leftDepth = 0, rightDepth = 0; // 这里初始为0是有目的的，为了下面求指数方便
        while (left != null) {  // 求左子树深度
            left = left.left;
            leftDepth++;
        }
        while (right != null) { // 求右子树深度
            right = right.right;
            rightDepth++;
        }
        if (leftDepth == rightDepth) {
            // 注意(2<<1) 相当于2^2，所以leftDepth初始为0
            return (2 << leftDepth) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
