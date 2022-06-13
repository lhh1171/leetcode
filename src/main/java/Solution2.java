import java.util.Arrays;

class Solution2 {
    public int heightChecker(int[] heights) {
        int[] arr = new int[101];
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            while (arr[i]-- > 0) {
                if (heights[j++] != i) count++;
            }
        }
        return count;
    }
}
//class demo{
//    public static void main(String[] args) {
//        Solution solution=new Solution();
//        System.out.println(solution.heightChecker(new int[]{1, 1, 4, 2, 1, 3}));
//    }
//}