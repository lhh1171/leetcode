package Bycategory.数组;

public class Solution209 {
    public int minSubArrayLen(int target,int[] nums){
        int res=Integer.MAX_VALUE;
        int i=0;
        int sum=0;
        int subL=0;
        for (int j = 0; j < nums.length; j++) {
            sum+=nums[j];
            while (sum>=target){
                subL=j-i+1;
                res=Math.min(res,subL);
                sum=sum-nums[i];
                i++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution209 solution209=new Solution209();
        System.out.println(solution209.minSubArrayLen(8, new int[]{2, 4, 2, 5, 1, 1, 1}));
    }
}
