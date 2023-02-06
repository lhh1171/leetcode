package Bycategory.贪心;

class Solution53 {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int num : nums) {
            count += num;
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            //count小于0的时候重置
            //count碰见小于0的时候重新计数，方便调整起点
            if (count <= 0) {
                count = 0;
            }
        }
       return sum;
    }
}