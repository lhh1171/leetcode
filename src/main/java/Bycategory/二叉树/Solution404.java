package Bycategory.二叉树;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution404 {
    //递归
    public int sumOfLeftLeaves1(TreeNode root) {
        if (root == null) return 0;
        int leftValue = sumOfLeftLeaves1(root.left);    // 左
        int rightValue = sumOfLeftLeaves1(root.right);  // 右

        int midValue = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            midValue = root.left.val;
        }
        int sum = midValue + leftValue + rightValue;  // 中
        return sum;
    }


    //迭代
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        int result = 0;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.left != null && node.left.left == null
                    && node.left.right == null) {
                result += node.left.val;
            }
            if (node.right != null) stack.add(node.right);
            if (node.left != null) stack.add(node.left);
        }
        return result;
    }


    // 层序遍历迭代法
    public int sumOfLeftLeaves(TreeNode root) {
        int sum = 0;
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) { // 左节点不为空
                    queue.offer(node.left);
                    if (node.left.left == null && node.left.right == null) { // 左叶子节点
                        sum += node.left.val;
                    }
                }
                if (node.right != null) queue.offer(node.right);
            }
        }
        return sum;
    }

}
