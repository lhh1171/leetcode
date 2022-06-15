import java.util.Arrays;

class Solution4 {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0] + 1;
        while (left < right) {
            int mid = left + right >> 1;
            if (count(nums, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int count(int[] nums, int dist) {
        int ans = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            while (nums[j] - nums[i] > dist) {
                i++;
            }
            ans += j - i;
        }
        return ans;
    }
}
