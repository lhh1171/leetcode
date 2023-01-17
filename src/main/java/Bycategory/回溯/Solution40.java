package Bycategory.回溯;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution40 {
    LinkedList<Integer> path1 = new LinkedList<>();
    List<List<Integer>> ans1 = new ArrayList<>();
    boolean[] used1;
    int sum1 = 0;

    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        //used的长度等于数组的长度
        used1 = new boolean[candidates.length];
        // 加标志数组，用来辅助判断同层节点是否已经遍历
        Arrays.fill(used1, false);
        // 为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort(candidates);
        backTracking1(candidates, target, 0);
        return ans1;
    }

    private void backTracking1(int[] candidates, int target, int startIndex) {
        if (sum1 == target) {
            ans1.add(new ArrayList(path1));
        }
        for (int i = startIndex; i < candidates.length; i++) {
            //剪枝
            if (sum1 + candidates[i] > target) {
                break;
            }
            // 出现重复节点，同层的第一个节点已经被访问过，所以直接跳过
            if (i > 0 && candidates[i] == candidates[i - 1] && !used1[i - 1]) {
                continue;
            }
            used1[i] = true;
            sum1 += candidates[i];
            path1.add(candidates[i]);
            // 每个节点仅能选择一次，所以从下一位开始
            backTracking1(candidates, target, i + 1);
            used1[i] = false;
            sum1 -= candidates[i];
            path1.removeLast();
        }
    }



    List<List<Integer>> res2 = new ArrayList<>();
    LinkedList<Integer> path2 = new LinkedList<>();
    int sum2 = 0;

    public List<List<Integer>> combinationSum2( int[] candidates, int target ) {
        //为了将重复的数字都放到一起，所以先进行排序
        Arrays.sort( candidates );
        backTracking2( candidates, target, 0 );
        return res2;
    }

    private void backTracking2(int[] candidates, int target, int start ) {
        if ( sum2 == target ) {
            System.out.println();
            res2.add( new ArrayList<>(path2) );
            return;
        }
        for (int i = start; i < candidates.length && sum2 + candidates[i] <= target; i++ ) {
            //正确剔除重复解的办法
            //跳过同一树层使用过的元素
            //因为排过序
            if ( i > start && candidates[i] == candidates[i - 1] ) {
                System.out.println(candidates[i]+","+candidates[i - 1]);
                continue;
            }
            sum2 += candidates[i];
            path2.add(candidates[i]);
            System.out.println(path2);
            // i+1 代表当前组内元素只选取一次
            backTracking2( candidates, target, i + 1 );
            int temp = path2.getLast();
            System.out.println("退出"+temp);
            sum2 -= temp;
            path2.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution40 solution40=new Solution40();
        solution40.combinationSum2(new int[]{2,5,2,1,2},5);
    }
}
