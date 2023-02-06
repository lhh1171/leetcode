package 最old.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution784 {

    public static List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        char[] charArray = S.toCharArray();
        dfs(charArray, 0, res);
        return res;
    }
    //递归回溯

    private static void dfs(char[] charArray, int index, List<String> res) {

        System.out.println(index+"-----------");
        if (index == charArray.length) {
            //右节点
            System.out.println("添加----------"+ Arrays.toString(charArray));
            res.add(new String(charArray));
            System.out.println("结束回退上一级"+(index-1));
            return;
        }

        System.out.println("递归传入--"+(index+1));
        dfs(charArray, index + 1, res);
        System.out.println("继续----"+index);
        if (Character.isLetter(charArray[index])) {
            //大写变成小写，小写变成大写
            //左节点
            charArray[index] ^= 1 << 5;
            System.out.println("大小写------传入"+(index+1));
            dfs(charArray, index + 1, res);
        }
    }

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2c3"));
        System.out.println();
    }
}

