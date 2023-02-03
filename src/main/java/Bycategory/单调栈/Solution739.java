package Bycategory.单调栈;

import java.util.Deque;
import java.util.LinkedList;

class Solution739 {
    // 版本 1
    public int[] dailyTemperatures1(int[] temperatures) {
        int lens = temperatures.length;
        int[] res = new int[lens];

        /**
         如果当前遍历的元素 大于栈顶元素，表示 栈顶元素的 右边的最大的元素就是 当前遍历的元素，
         所以弹出 栈顶元素，并记录
         如果栈不空的话，还要考虑新的栈顶与当前元素的大小关系
         否则的话，可以直接入栈。
         注意，单调栈里 加入的元素是 下标。
         */
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i < lens; i++) {

            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }


    //--------这 是一条分界线
    // 版本 2
    public int[] dailyTemperatures2(int[] temperatures) {
        int lens = temperatures.length;
        int[] res = new int[lens];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < lens; i++) {

            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                res[stack.peek()] = i - stack.peek();
                stack.pop();
            }
            stack.push(i);
        }

        return res;
    }
}