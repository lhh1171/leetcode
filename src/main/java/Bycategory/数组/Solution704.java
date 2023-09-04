package Bycategory.数组;


// https://leetcode.cn/problems/binary-search/
public class Solution704 {
    public int findMiddle(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] > target) {
                //当前偏左的位置也不满足
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = left + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Solution704 solution704 = new Solution704();
        // 有序数组
        System.out.println(solution704.findMiddle(new int[]{1, 2, 3, 4}, 3));
    }
}
