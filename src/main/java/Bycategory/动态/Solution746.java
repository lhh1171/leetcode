package Bycategory.动态;
// 方式一：第一步不支付费用
class Solution746 {
    //dp数组表示
    //dp[i]表示到i阶的最小花费
    //dp[i]等于
    //dp[i-2]+cos[i-2]表示要想再上两格
    //dp[i-1]+cos[i-1]表示要想再上一格

    //人到达了第i格但是cos[i-1]还没有花费，花费cos[i-1]可以选择跳一格还是两格
    public int minCostClimbingStairs(int[] cost) {
        int len = cost.length;
        int[] dp = new int[len + 1];

        // 从下标为 0 或下标为 1 的台阶开始，因此支付费用为0
        dp[0] = 0;
        dp[1] = 0;

        // 计算到达每一层台阶的最小费用
        for (int i = 2; i <= len; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }

        return dp[len];
    }
}