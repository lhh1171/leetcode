package Bycategory.字符串;

class Solution28 {
    /**
     * 基于窗口滑动的算法
     * <p>
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(1)
     * 注：n为haystack的长度，m为needle的长度
     */
    public int strStr1(String haystack, String needle) {
        // m表示模式串
        int m = needle.length();
        // 当 needle 是空字符串时我们应当返回 0
        if (m == 0) {
            return 0;
        }
        // n表示原字符串长度
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n - m + 1) {
            // 找到首字母相等
            while (i < n && haystack.charAt(i) != needle.charAt(j)) {
                i++;
            }
            if (i == n) {
                // 没有首字母相等的
                return -1;
            }
            // 遍历后续字符，判断是否相等
            i++;
            j++;
            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                // 找到
                return i - j;
            } else {
                // 未找到
                i -= j - 1;
                j = 0;
            }
        }
        return -1;
    }




    //KMP
    public int strStr2(String haystack, String needle) {
        if (needle.length() == 0) {
            return 0;
        }

        int[] next = new int[needle.length()];
        //-1 0 -1 0 1 -1
        getNext2(next, needle);
        int j = -1;
        for (int i = 0; i < haystack.length(); i++) {
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            if (j == needle.length() - 1) {
                return (i - needle.length() + 1);
            }
        }
        return -1;
    }

    //KMP  前缀表（不减一）Java实现
    public int strStr3(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        //0 1 0 1 2 0
        getNext3(next, needle);

        int j = 0;
        //遍历原字符串aabaabaaf
        for (int i = 0; i < haystack.length(); i++) {
            //利用前缀表
            while (j > 0 && needle.charAt(j) != haystack.charAt(i))
                j = next[j - 1];
            //字符串正常比对
            if (needle.charAt(j) == haystack.charAt(i))
                j++;
            //完全匹配返回
            if (j == needle.length())
                return i - needle.length() + 1;
        }
        return -1;

    }

    // 方法一
    public void getNext2(int[] next, String s) {
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++) {
            while (j >= 0 &&
                    s.charAt(i) != s.charAt(j + 1)) {
                j = next[j];
            }

            if (s.charAt(i) == s.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
    }

    // 方法二
    private void getNext3(int[] next, String s) {
        int j = 0;
        next[0] = 0;
        // 遍历s.length-1次
        for (int i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(j) != s.charAt(i))
                j = next[j - 1];
            if (s.charAt(j) == s.charAt(i))
                j++;
            next[i] = j;
        }
    }

    public static void main(String[] args) {
        Solution28 solution28=new Solution28();
        //0 1 0 1 2 0
        solution28.strStr3("aabaabaaf","aabaaf");

        //0 0 1 2 3 4 5 6
//        solution28.strStr3("aabaabaaf","abababab");
    }
}
