package 最old.simple;

class Solution1768 {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int i = 0, j = 0;

        StringBuilder ans = new StringBuilder();
        //双指针问题
        while (i < m || j < n) {
            if (i < m) {
                ans.append(word1.charAt(i));
                ++i;
            }
            if (j < n) {
                ans.append(word2.charAt(j));
                ++j;
            }
        }
        return ans.toString();
    }
}
