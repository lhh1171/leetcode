package Bycategory.二叉树;

import java.util.*;
import java.util.stream.Collectors;

public class Solution501 {
    //暴力法
    public int[] findMode1(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        if (root == null) return list.stream().mapToInt(Integer::intValue).toArray();
        // 获得频率 Map
        searchBST(root, map);
        List<Map.Entry<Integer, Integer>> mapList = map.entrySet().stream()
                .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                .collect(Collectors.toList());
        list.add(mapList.get(0).getKey());
        // 把频率最高的加入 list
        for (int i = 1; i < mapList.size(); i++) {
            if (mapList.get(i).getValue() == mapList.get(i - 1).getValue()) {
                list.add(mapList.get(i).getKey());
            } else {
                break;
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    void searchBST(TreeNode curr, Map<Integer, Integer> map) {
        if (curr == null) return;
        map.put(curr.val, map.getOrDefault(curr.val, 0) + 1);
        searchBST(curr.left, map);
        searchBST(curr.right, map);
    }



    //中序遍历-不使用额外空间，利用二叉搜索树特性
    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;
    public int[] findMode2(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode3(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public void findMode3(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode3(root.left);

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新结果以及maxCount
        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }
        pre = root;

        findMode3(root.right);
    }

    //迭代法
    public int[] findMode(TreeNode root) {
        TreeNode pre = null;
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        int maxCount = 0;
        int count = 0;
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur =cur.left;
            }else {
                cur = stack.pop();
                // 计数
                if (pre == null || cur.val != pre.val) {
                    count = 1;
                }else {
                    count++;
                }
                // 更新结果
                if (count > maxCount) {
                    maxCount = count;
                    result.clear();
                    result.add(cur.val);
                }else if (count == maxCount) {
                    result.add(cur.val);
                }
                pre = cur;
                cur = cur.right;
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
