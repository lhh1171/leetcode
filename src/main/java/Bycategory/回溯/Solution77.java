package Bycategory.回溯;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution77 {

    List<List<Integer>> result = new ArrayList<>();
    LinkedList<Integer> path = new LinkedList<>();

    public List<List<Integer>> combine1(int n, int k) {
        combineHelper1(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，
     * 就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     */
    private void combineHelper1(int n, int k, int startIndex) {
        //终止条件
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        /*
        * 1.已经选择的元素个数：path.size();
        * 2.还需要的元素个数为: k - path.size();
        * 3.在集合n中至多要从该起始位置 : n - (k - path.size()) + 1，开始遍历,后面的组合元素个数不满足要求
        *
        * 为什么有个+1呢，因为包括起始位置，我们要是一个左闭的集合。
        * 举个例子，n = 4，k = 3， 目前已经选取的元素为0（path.size为0），n - (k - 0) + 1 即 4 - ( 3 - 0) + 1 = 2。
        * 从2开始搜索都是合理的，可以是组合[2, 3, 4]。
        * 这里大家想不懂的话，建议也举一个例子，就知道是不是要+1了。
        * */
        //后面的组合元素个数不满足要求
        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++) {
            path.add(i);
            //递归
            combineHelper1(n, k, i + 1);
            path.removeLast();
        }
    }


    public List<List<Integer>> combine2(int n, int k) {
        combineHelper2(n, k, 1);
        return result;
    }

    /**
     * 每次从集合中选取元素，可选择的范围随着选择的进行而收缩，调整可选择的范围，就是要靠startIndex
     * @param startIndex 用来记录本层递归的中，集合从哪里开始遍历（集合就是[1,...,n] ）。
     * 后面的组合元素个数不满足要求
     */
    private void combineHelper2(int n, int k, int startIndex){
        //终止条件
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i <= n - (k - path.size()) + 1; i++){
            path.add(i);
            combineHelper2(n, k, i + 1);
            path.removeLast();
        }
    }

}
