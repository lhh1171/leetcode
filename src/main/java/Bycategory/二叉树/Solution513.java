package Bycategory.二叉树;

import java.util.LinkedList;
import java.util.Queue;

public class Solution513 {
    //递归方法
    private int Deep = -1;
    private int value = 0;
    public int findBottomLeftValue1(TreeNode root) {
        value = root.val;
        findLeftValue1(root,0);
        return value;
    }

    private void findLeftValue1 (TreeNode root,int deep) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            //当deep更新时候，value更新一下，就能保证拿到最终结果
            if (deep > Deep) {
                value = root.val;
                Deep = deep;
            }
        }

//        if (root.left != null) findLeftValue1(root.left,deep + 1);
//        if (root.right != null) findLeftValue1(root.right,deep + 1);

        //与上面效果相同
        if (root.left != null){
            deep++;
            findLeftValue1(root.left,deep);
            //回溯
            deep--;
        }
        if (root.right != null){
            deep++;
            findLeftValue1(root.right,deep);
            //回溯
            deep--;
        }
    }



    public int findBottomLeftValue2(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }


}
