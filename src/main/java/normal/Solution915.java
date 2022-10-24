package normal;

class Solution915 {
    public int partitionDisjoint(int[] nums) {
        //拿到长度
        int n = nums.length;

        int leftMax = nums[0], leftPos = 0, curMax = nums[0];

        for (int i = 1; i < n - 1; i++) {
            //比较curMax,nums[i]
            curMax = Math.max(curMax, nums[i]);
            //当nums[i]小leftMax,leftMax需要更新为最大
            if (nums[i] < leftMax) {
                leftMax = curMax;
                leftPos = i;
            }
        }
        //最终curMax为数组中的最大值
        return leftPos + 1;
    }
}
