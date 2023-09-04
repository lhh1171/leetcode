package Bycategory.数组;

// 移除元素
// https://leetcode.cn/problems/remove-element/
public class Solution27 {

    public int removeElement1(int[] nums, int val) {
        // 快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

    //相向双指针法
    public int removeElement2(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;
        //将right移到从右数第一个值不为val的位置
        while (right >= 0 && nums[right] == val) right--;
        while (left <= right) {
            //left位置的元素需要移除
            if (nums[left] == val) {
                //将right位置的元素移到left（覆盖），right位置移除
                nums[left] = nums[right];
                right--;
            }
            left++;
            //将right移到一个值不为val的位置
            while (right >= 0 && nums[right] == val) right--;
        }
        return left;
    }


    public static void main(String[] args) {
        //两个指针，一个指针计算除了目标数组之后的长度，一个指针负责遍历偏移
        //fast slow
        //right left
        Solution27 solution27 = new Solution27();
        System.out.println(solution27.removeElement1(new int[]{1, 2, 3, 4, 3}, 3));
    }

}
