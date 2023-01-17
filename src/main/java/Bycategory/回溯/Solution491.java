package Bycategory.回溯;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Solution491 {
    private List<Integer> path1 = new ArrayList<>();
    private List<List<Integer>> res1 = new ArrayList<>();
    public List<List<Integer>> findSubsequences(int[] nums) {
        backtracking(nums,0);
        return res1;
    }

    private void backtracking (int[] nums, int start) {
        if (path1.size() > 1) {
            res1.add(new ArrayList<>(path1));
        }

        int[] used = new int[201];
        for (int i = start; i < nums.length; i++) {
            if (!path1.isEmpty() && nums[i] < path1.get(path1.size() - 1) ||
                    (used[nums[i] + 100] == 1)) continue;
            used[nums[i] + 100] = 1;
            path1.add(nums[i]);
            backtracking(nums, i + 1);
            path1.remove(path1.size() - 1);
        }
    }


    //结果集合
    List<List<Integer>> res2 = new ArrayList<>();
    //路径集合
    LinkedList<Integer> path2 = new LinkedList<>();
    public List<List<Integer>> findSubsequences2(int[] nums) {
        getSubsequences2(nums,0);
        return res2;
    }
    private void getSubsequences2( int[] nums, int start ) {
        if(path2.size()>1 ){
            res2.add( new ArrayList<>(path2) );
            // 注意这里不要加return，要取树上的节点
        }
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=start ;i < nums.length ;i++){
            if(!path2.isEmpty() && nums[i]< path2.getLast()){
                continue;
            }
            // 使用过了当前数字
            if ( map.getOrDefault( nums[i],0 ) >=1 ){
                continue;
            }
            map.put(nums[i],map.getOrDefault( nums[i],0 )+1);
            path2.add( nums[i] );
            getSubsequences2( nums,i+1 );
            path2.removeLast();
        }
    }
}
