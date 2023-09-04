package Bycategory.数组;

import java.util.Arrays;

// 平方之后再排序 https://leetcode.cn/problems/squares-of-a-sorted-array/
public class Solution977 {

    // 再建数组插入排序
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int k = nums.length - 1;
        int i = 0, j = 0;
        for (i = 0, j = nums.length - 1; i <= j; ) {
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[k] = nums[i] * nums[i];
                k--;
                i++;
            } else {
                res[k] = nums[j] * nums[j];
                k--;
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution977 solution977 = new Solution977();
        System.out.println(Arrays.toString(solution977.sortedSquares(new int[]{-4, -3, 1, 4, 5})));
    }
}
