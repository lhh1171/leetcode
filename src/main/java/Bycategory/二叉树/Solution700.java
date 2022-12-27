package Bycategory.二叉树;

import java.util.Stack;

public class Solution700 {
    // 递归，普通二叉树
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode left = searchBST1(root.left, val);
        if (left != null) {
            return left;
        }
        return searchBST1(root.right, val);
    }


    // 递归，利用二叉搜索树特点，优化
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST2(root.left, val);
        } else {
            return searchBST2(root.right, val);
        }
    }

    // 迭代，普通二叉树
    public TreeNode searchBST3(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.val == val) {
                return pop;
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return null;
    }

    // 迭代，利用二叉搜索树特点，优化，可以不需要栈
    public TreeNode searchBST4(TreeNode root, int val) {
        while (root != null)
            if (val < root.val) root = root.left;
            else if (val > root.val) root = root.right;
            else return root;
        return null;
    }
}
