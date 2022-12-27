package Bycategory.二叉树;

import java.util.Stack;

public class Solution98 {
        // 递归
        TreeNode max;
        public boolean isValidBST1(TreeNode root) {
            if (root == null) {
                return true;
            }
            // 左
            boolean left = isValidBST1(root.left);
            if (!left) {
                return false;
            }
            // 中
            if (max != null && root.val <= max.val) {
                return false;
            }
            max = root;
            // 右
            boolean right = isValidBST1(root.right);
            return right;
        }


        // 迭代
        public boolean isValidBST2(TreeNode root) {
            if (root == null) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;// 左
                }
                // 中，处理
                TreeNode pop = stack.pop();
                if (pre != null && pop.val <= pre.val) {
                    return false;
                }
                pre = pop;

                root = pop.right;// 右
            }
            return true;
        }


        // 简洁实现·递归解法
        public boolean isValidBST3(TreeNode root) {
            return validBST(Long.MIN_VALUE, Long.MAX_VALUE, root);
        }

        boolean validBST(long lower, long upper, TreeNode root) {
            if (root == null) return true;
            if (root.val <= lower || root.val >= upper) return false;
            return validBST(lower, root.val, root.left) && validBST(root.val, upper, root.right);
        }

        // 简洁实现·中序遍历
        private long prev = Long.MIN_VALUE;
        public boolean isValidBST4(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!isValidBST4(root.left)) {
                return false;
            }
            if (root.val <= prev) { // 不满足二叉搜索树条件
                return false;
            }
            prev = root.val;
            return isValidBST4(root.right);
        }
}
