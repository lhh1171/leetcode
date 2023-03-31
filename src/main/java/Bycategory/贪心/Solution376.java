package Bycategory.贪心;


class Solution376 {
    public static int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];

            // 通过从原始序列中删除一些（也可以不删除）
            // 元素来获得子序列，剩下的元素保持其原始顺序。
            //如果当前差值和上一个差值为一正一负,这就是贪心的地方
            //等于0的情况表示初始时的preDiff
            //当前差值大于0且之前差值小于等于0，或者，当前差值小于0且之前差值大于于等于0
            if ((curDiff > 0 && preDiff <= 0) ||
                    (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(wiggleMaxLength(new int[]{-1, -2, -3, -4}));
    }
}