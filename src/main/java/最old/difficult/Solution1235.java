package 最old.difficult;

import java.util.Arrays;

class Solution1235 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        //有多少份工作
        int n = startTime.length;
        //工作计划放在这里
        int[][] jobs = new int[n][];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        //根据a[i][0]startTime的大小来排序
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        //ans[i]表示的是最晚从jobs[i][0]开始兼职可以最多得到多少钱
        int[] ans = new int[n];
        //倒着算，每次都可以算出最大的报酬
        //从后往前遍历
        for (int i = n - 1; i >= 0; i--) {
            //判断是否超过最后一段工作的时机，如果超过ans[i]=jobs[i][2]
            if (jobs[i][1] <= jobs[n - 1][0]) {
                //如果没有超过，就需要规划这个方案第二份工作的最早开始时间
                int l = i + 1, r = n - 1;
                while (l < r) {
                    //除2
                    int mid = (l + r) >> 1;
                    if (jobs[mid][0] >= jobs[i][1]) {
                        r = mid;
                    } else {
                        l = mid + 1;
                    }
                    if (l == r - 1) {
                        if (jobs[l][0] >= jobs[i][1]) {
                            r = l;
                        }
                        break;
                    }
                }
                //加上立即开始的下一份工作的报酬，但是一开始ans[r]可能为0，因为还没算
                ans[i] = jobs[i][2] + ans[r];
            } else {
                ans[i] = jobs[i][2];
            }
            //只能做一份工作
            if (i < n - 1) {
                ans[i] = Math.max(ans[i], ans[i + 1]);
            }
        }
        return ans[0];
    }
}