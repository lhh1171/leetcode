package normal;

import java.util.ArrayDeque;

public class Solution907 {
    private static final long MOD = Long.MAX_VALUE;

    public static int sumSubarrayMins(int[] arr) {
        long ans = 0L;
        ArrayDeque<Integer> st = new ArrayDeque<>();
        st.push(-1); // 哨兵
        //遍历arr
        for (int r = 0; r <= arr.length; ++r) {
            //是否为arr.length,如果是就为-1
            long x = r < arr.length ? arr[r] : -1; // 假设 arr 末尾有个 -1

            //如果栈的大小大于1且arr[st.peekFirst()]栈顶元素上一次塞进去下标代表的元素 大于等于当前r代表的元素
            while (st.size() > 1 && arr[st.peekFirst()] >= x) {
                //把上一次塞进去的元素取出来
                int i = st.removeFirst();
                ans += (long) arr[i] * (i - st.peekFirst()) * (r - i); // 累加贡献
            }
            st.addFirst(r);
        }
        System.out.println();
        return (int) (ans % MOD);
    }

    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 4}));
    }
}
