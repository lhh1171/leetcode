package difficult;

import java.util.ArrayDeque;

class Solution862 {
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length,ans = n+1;
        long[] sum = new long [n+1];
        //建立前缀和数组 前缀合数组中元素要用long
        for(int i=0;i<n+1;i++){
            if(i==0) sum[i] = 0L;
            else sum[i] = sum[i-1]+nums[i-1];
        }
        //建立双端队列
        ArrayDeque<Integer> qu = new ArrayDeque<>();
        //这里i是指前i项
        //遍历sum
        for(int i=0;i<=n;i++){
            //取出sum[i]
            long temp = sum[i];
        //下面是两个优化操作 因为使用了队列的数据结构 我们要找如何减少队列中元素的优化方法。
        //（下文中配对指的是sum[i]-sum[j]>=k sum[i]与sum[j]配对）

        //优化1:队列中第一元素代表的前缀和已经可以和一个sum[i]匹配，
            // 所以这个元素就能够出队列（因为如果后面又有sum[i]能和这个元素配对，他们的子数组长度会更长）
            /*=============================================*/
        //优化2:队列中最后一个元素是sum[i-1]如果它大于等于sum[i]，
            // 后面一旦有一个sum[x]能与sum[i-1]配对，sum[x]也一定能与sum[i]配对，所以让sum[i-1]出队列。

            //队列不为空且temp-sum[qu.peekFirst]>=k  已经满足条件了
            //sum[i] - 队列第一个元素(sum[i-1]) >= k,
            while(!qu.isEmpty() && temp>=k+sum[qu.peekFirst()]){
                //ans,i-qu.peekFirst最前位置
                //5,3-1
                ans = Math.min(ans,i - qu.peekFirst());
                qu.pollFirst();
            }
            //sum[i] - 队列最后一个元素(sum[i-1]) >= sum[i],让sum[i-1]出队列
            while(!qu.isEmpty() && temp<=sum[qu.peekLast()]){
                qu.pollLast();
            }
            qu.addLast(i);
        }
        System.out.println("");
        return ans>n ? -1:ans;
    }

    //队列里保留的是满足条件的最短子序列
    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{2, 3, 1, 5}, 5));
        System.out.println("dfsadf");
    }
}
