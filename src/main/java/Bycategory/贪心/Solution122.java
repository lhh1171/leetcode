package Bycategory.贪心;

// 贪心思路
class Solution122 {
    //假如第0天买入，第3天卖出，那么利润为：prices[3] - prices[0]。
    //相当于(prices[3] - prices[2]) + (prices[2] - prices[1]) + (prices[1] - prices[0])。
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            //收集正利润的区间，就是股票买卖的区间，而我们只需要关注最终利润，不需要记录区间。
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
}