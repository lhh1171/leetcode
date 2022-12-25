package Bycategory.二叉树;

import java.util.HashMap;
import java.util.Map;

//中序和后序
public class Solution106 {
        Map<Integer, Integer> inorderMap;  // 方便根据数值查找位置
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            inorderMap = new HashMap<>();
            // 用map保存中序序列的数值对应位置
            for (int i = 0; i < inorder.length; i++) {
                inorderMap.put(inorder[i], i);
            }

            return findNode(inorder,  0, inorder.length,
                    postorder,0, postorder.length);  // 前闭后开
        }

        public TreeNode findNode(int[] inorder, int inBegin, int inEnd,
                                 int[] postorder, int postBegin, int postEnd) {
            // 参数里的范围都是前闭后开
            if (inBegin >= inEnd || postBegin >= postEnd) {  // 不满足左闭右开，说明没有元素，返回空树
                return null;
            }
            // 找到后序遍历的最后一个元素在中序遍历中的位置
            int rootIndex = inorderMap.get(postorder[postEnd - 1]);
            TreeNode root = new TreeNode(inorder[rootIndex]);  // 构造结点
            int lenOfLeft = rootIndex - inBegin;  // 保存中序左子树个数，用来确定后序数列的个数
            root.left = findNode(inorder, inBegin, rootIndex,
                    postorder, postBegin, postBegin + lenOfLeft);
            root.right = findNode(inorder, rootIndex + 1, inEnd,
                    postorder, postBegin + lenOfLeft, postEnd - 1);

            return root;
        }
}
