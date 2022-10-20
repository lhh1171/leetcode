package old;

import java.util.Random;

public class Solution1 {
    int[] nums;
    public Solution1(int[] nums) {
        this.nums=nums;
    }

    public int pick(int target) {
        int ii=0;
        for(int i:nums){
            if(i==target){
                ii++;
            }
        }
        Random r=new Random();
        int flag=r.nextInt(ii);
        if (ii==1){
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]==target){
                    return i;
                }
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                if(nums[i]==target){
                    if(flag==0){
                        return i;
                    }
                    flag--;
                }
            }
        }
        System.out.println("error");
        return -1;
    }
}

//class demo{
//    public static void main(String[] args) {
//        Solution solution=new Solution(new int[]{1,2,3,3,3});
//        System.out.println(solution.pick(3));
//        System.out.println(solution.pick(1));
//        System.out.println(solution.pick(3));
//    }
//}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */