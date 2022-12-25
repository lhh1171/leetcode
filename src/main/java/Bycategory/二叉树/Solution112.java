package Bycategory.二叉树;

import java.util.Stack;

public class Solution112 {
    //递归法
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        // 叶子结点,正确的返回逻辑
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        if (root.left != null) {
            boolean left = hasPathSum1(root.left, targetSum);
            if (left) {      // 已经找到
                return true;
            }
        }
        if (root.right != null) {
            boolean right = hasPathSum1(root.right, targetSum);
            if (right) {     // 已经找到
                return true;
            }
        }
        return false;
    }


    // lc112 简洁方法
    public boolean hasPathSum2(TreeNode root, int targetSum) {

        if (root == null) return false; // 为空退出

        // 叶子节点判断是否符合
        if (root.left == null && root.right == null) return root.val == targetSum;

        // 求两侧分支的路径和，两侧同时遍历
        return hasPathSum2(root.left, targetSum - root.val) ||
                hasPathSum2(root.right, targetSum - root.val);
    }

    //迭代法
    public boolean hasPathSum3(TreeNode root, int targetSum) {
        if(root == null) return false;
        //迭代遍历数组
        Stack<TreeNode> stack1 = new Stack<>();
        //记录一条路径，回溯在这里进行
        Stack<Integer> stack2 = new Stack<>();
        stack1.push(root);
        stack2.push(root.val);
        while(!stack1.empty()) {
            int size = stack1.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = stack1.pop();
                int sum = stack2.pop();
                // 如果该节点是叶子节点了，同时该节点的路径数值等于sum，那么就返回true
                if(node.left == null && node.right == null && sum == targetSum) {
                    return true;
                }
                // 右节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if(node.right != null){
                    stack1.push(node.right);
                    //累积和
                    stack2.push(sum + node.right.val);
                }
                // 左节点，压进去一个节点的时候，将该节点的路径数值也记录下来
                if(node.left != null) {
                    stack1.push(node.left);
                    //累积和
                    stack2.push(sum + node.left.val);
                }
            }
        }
        return false;
    }
}
