package Bycategory.贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> partitionLabels1(String S) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26];
        char[] chars = S.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            edge[chars[i] - 'a'] = i;
        }
        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx,edge[chars[i] - 'a']);
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }

    /*解法二： 上述c++补充思路的Java代码实现*/
    
    public  int[][] findPartitions(String s) {
        List<Integer> temp = new ArrayList<>();
        int[][] hash = new int[26][2];//26个字母2列 表示该字母对应的区间

        for (int i = 0; i < s.length(); i++) {
            //更新字符c对应的位置i
            char c = s.charAt(i);
            if (hash[c - 'a'][0] == 0) hash[c - 'a'][0] = i;

            hash[c - 'a'][1] = i;

            //第一个元素区别对待一下
            hash[s.charAt(0) - 'a'][0] = 0;
        }


        List<List<Integer>> h = new LinkedList<>();
        //组装区间
        for (int i = 0; i < 26; i++) {
            //if (hash[i][0] != hash[i][1]) {
            temp.clear();
            temp.add(hash[i][0]);
            temp.add(hash[i][1]);
            //System.out.println(temp);
            h.add(new ArrayList<>(temp));
            // }
        }
        // System.out.println(h);
        // System.out.println(h.size());
        int[][] res = new int[h.size()][2];
        for (int i = 0; i < h.size(); i++) {
            List<Integer> list = h.get(i);
            res[i][0] =  list.get(0);
            res[i][1] =  list.get(1);
        }

        return res;

    }

    public  List<Integer> partitionLabels2(String s) {
        int[][] partitions = findPartitions(s);
        List<Integer> res = new ArrayList<>();
        Arrays.sort(partitions, (o1, o2) -> Integer.compare(o1[0], o2[0]));
        int right = partitions[0][1];
        int left = 0;
        for (int i = 0; i < partitions.length; i++) {
            if (partitions[i][0] > right) {
                //左边界大于右边界即可纪委一次分割
                res.add(right - left + 1);
                left = partitions[i][0];
            }
            right = Math.max(right, partitions[i][1]);

        }
        //最右端
        res.add(right - left + 1);
        return res;

    }
}