package Bycategory.二叉树;

import java.util.Stack;

public class Solution530 {
    TreeNode pre1;// 记录上一个遍历的结点
    int result = Integer.MAX_VALUE;
    public int getMinimumDifference1(TreeNode root) {
        if(root==null)return 0;
        traversal(root);
        return result;
    }
    public void traversal(TreeNode root){
        if(root==null)return;
        //左
        traversal(root.left);
        //中
        if(pre1 !=null){
            result = Math.min(result,root.val- pre1.val);
        }
        pre1 = root;
        //右
        traversal(root.right);
    }

    /*=======================================================*/

    TreeNode pre2;
    Stack<TreeNode> stack;
    public int getMinimumDifference2(TreeNode root) {
        if (root == null) return 0;
        stack = new Stack<>();
        TreeNode cur = root;
        int result = Integer.MAX_VALUE;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur); // 将访问的节点放进栈
                cur = cur.left;  // 左
            }else {
                cur = stack.pop();
                if (pre2 != null) { // 中
                    result = Math.min(result, cur.val - pre2.val);
                }
                pre2 = cur;
                cur = cur.right; // 右
            }
        }
        return result;
    }
}
