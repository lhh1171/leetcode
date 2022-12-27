package Bycategory.二叉树;

import java.util.Stack;

public class Solution617 {

        // 递归
        public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
            if (root1 == null) return root2;
            if (root2 == null) return root1;

            root1.val += root2.val;
            root1.left = mergeTrees1(root1.left,root2.left);
            root1.right = mergeTrees1(root1.right,root2.right);
            return root1;
        }


        // 使用栈迭代
        public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
            if (root1 == null) {
                return root2;
            }
            if (root2 == null) {
                return root1;
            }
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root2);
            stack.push(root1);
            while (!stack.isEmpty()) {
                TreeNode node1 = stack.pop();
                TreeNode node2 = stack.pop();
                node1.val += node2.val;
                if (node2.right != null && node1.right != null) {
                    stack.push(node2.right);
                    stack.push(node1.right);
                } else {
                    if (node1.right == null) {
                        node1.right = node2.right;
                    }
                }
                if (node2.left != null && node1.left != null) {
                    stack.push(node2.left);
                    stack.push(node1.left);
                } else {
                    if (node1.left == null) {
                        node1.left = node2.left;
                    }
                }
            }
            return root1;
        }

}
