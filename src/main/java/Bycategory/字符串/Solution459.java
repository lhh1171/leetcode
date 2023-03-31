package Bycategory.字符串;

public class Solution459 {
    public boolean repeatedSubstringPattern(String s) {
        if (s.equals("")) return false;
        int len = s.length();

        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            //从后往前匹配，
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]){
                System.out.println("----------");
                j = next[j];
            }
            // 匹配成功，j往后移,chars[i] 和 chars[j+1]
            if (chars[i] == chars[j + 1]) j++;
            System.out.println(s.substring(0, i));
            System.out.println(s.substring(0, j));
            System.out.println(i+"..........."+j);
            // 更新 next 数组的值
            next[i] = j;
        }
        for (int i:
             next) {
            System.out.print(i +" ");
        }
        System.out.println();
        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        // len % (len - next[len])表示len%（(字符串的长度) - (最长公共前后缀的长度) ）-----------是否能整除
        //len - next[len]------(字符串的长度) - (最长公共前后缀的长度) ---------表示最小子串单元的长度
        System.out.println(len+","+next[len]);
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

    //所以判断字符串s是否由重复子串组成，只要两个s拼接在一起，
    // 里面还出现一个s的话，就说明是由重复子串组成。
    boolean repeatedSubstringPattern2(String s) {
        String t = s + s;
        String substring = t.substring(0, t.length() - 1);// 掐头去尾
        return substring.contains(s); // r
    }

    public static void main(String[] args) {
        Solution459 solution459=new Solution459();
        //a的最长相等前后缀是0，因为他没有前后缀
        //ab的最长相等前后缀是0，因为他他前缀是a,后缀是b
        //aba  a  a 1
        //abab ab ab 2
        //ababa aba aba 3------>ababf 从后往前匹配 a ab aba abab:b ba bab babf没有一个匹配的
        //ababab abab abab 4
        //abababa ababa ababa 5
        //abababab ababab ababab 6
        //0 0 1 2 3 4 5 6


        //一般使用双指针法，在当前子串，i表示后缀末尾，j表示前缀的末尾（代表最长相等前后缀的长度）
//        System.out.println(solution459.repeatedSubstringPattern("abcabcabcabc"));

        System.out.println(solution459.repeatedSubstringPattern("ababfbab"));

//        补充一下
        System.out.println(solution459.repeatedSubstringPattern("ababfbababfb"));

    }
}
