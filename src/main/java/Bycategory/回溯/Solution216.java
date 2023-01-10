package Bycategory.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//lc77的优化
public class Solution216 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTracking(n, k, 1, 0);
        return result;
    }

    private void backTracking(int targetSum, int k, int startIndex, int sum) {
        // 减枝，results不收集该结果
        if (sum > targetSum) {
            return;
        }

        if (path.size() == k) {
            if (sum == targetSum) result.add(new ArrayList<>(path));
            return;
        }

        // 减枝 9 - (k - path.size()) + 1
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            path.add(i);
            sum += i;
            backTracking(targetSum, k, i + 1, sum);
            //回溯
            path.removeLast();
            //回溯
            sum -= i;
        }
    }


    // 上面剪枝 i <= 9 - (k - path.size()) + 1; 如果还是不清楚
    // 也可以改为 if (path.size() > k) return; 执行效率上是一样的
    LinkedList<Integer> path1 = new LinkedList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum4(int k, int n) {
        build(k, n, 1, 0);
        return ans;
    }

    private void build(int k, int n, int startIndex, int sum) {

        if (sum > n) return;

        if (path1.size() > k) return;

        if (sum == n && path1.size() == k) {
            ans.add(new ArrayList<>(path1));
            return;
        }

        for (int i = startIndex; i <= 9; i++) {
            path1.add(i);
            sum += i;
            build(k, n, i + 1, sum);
            sum -= i;
            path1.removeLast();
        }
    }
}
