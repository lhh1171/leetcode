package Bycategory.哈希;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution349 {
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        //遍历数组1
        for (int i : nums1) {
            set1.add(i);
        }
        //遍历数组2的过程中判断哈希表中是否存在该元素
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }
        //将结果集合转为数组
        int[] res=new int[resSet.size()];
        Object[] objects = resSet.toArray();
        for (int i = 0; i < objects.length; i++) {
            res[i]= (int) objects[i];
        }
        return res;
        //https://www.runoob.com/java/java8-streams.html
//        return resSet.stream().mapToInt(x -> x).toArray();
    }

    public static void main(String[] args) {
        System.out.println(
                Arrays.toString(intersection(new int[]{1, 3, 4, 5, 6, 4}, new int[]{2, 3, 4})));
    }

}