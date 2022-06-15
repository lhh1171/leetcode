import java.util.Arrays;

class Solution4 {
    //不断调整mid,寻找该mid里有count
    public int smallestDistancePair(int[] nums, int k) {
        //先排序
        Arrays.sort(nums);
        //right=最大-最小-1
        int left = 0, right = nums[nums.length - 1] - nums[0] + 1;
        int count=0;
        int mid=0;
        while (left < right) {
            //继续除二获取中位值
             mid = left + right >> 1;
            //获取数组两者差大于mid的累加数组角标绝对差值，如果大于K就向左移动mid
            count=count(nums, mid);
            //表示数组里每对绝对值，大于mid的对数，对数如果大于等于k向左移动mid，要是小于向右移动mid
            if (count>= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(mid);
        return left;
    }


    //表示数组里每对绝对值，大于mid的对数
    private int count(int[] nums, int dist) {
        int ans = 0;
        //遍历nums,用nums[j]-nums[i]<=dist的时候,累加角标其差，ans+=j-i，返回累加值
        for (int i = 0, j = 0; j < nums.length; j++) {
            while (nums[j] - nums[i] > dist) {
                i++;
            }
            ans += j - i;
        }
        return ans;
    }
}
class demo{
    public static void main(String[] args) {
        Solution4 solution4=new Solution4();
        System.out.println(solution4.smallestDistancePair(new int[]{1, 2, 5, 7, 9}, 4));
    }
}
