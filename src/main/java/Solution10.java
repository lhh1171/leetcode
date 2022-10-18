/*
* 给定一个按 非递减顺序 排列的数字数组 digits 。
* 你可以用任意次数 digits[i] 来写的数字。
* 例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
* 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
* */
class Solution10 {
    public int atMostNGivenDigitSet(String[] digits, int limitMax) {
        String limitMaxStr = Integer.toString(limitMax);
        int limitMaxStrLen = limitMaxStr.length();
        int digitsLen = digits.length;
        int[][] temp = new int[limitMaxStrLen + 1][2];
        temp[0][1] = 1;
        for (int i = 1; i <= limitMaxStrLen; i++) {
            for (int j = 0; j < digitsLen; j++) {
                if (digits[j].charAt(0) == limitMaxStr.charAt(i - 1)) {
                    temp[i][1] = temp[i - 1][1];
                } else if (digits[j].charAt(0) < limitMaxStr.charAt(i - 1)) {
                    temp[i][0] += temp[i - 1][1];
                } else {
                    break;
                }
            }
            if (i > 1) {
                temp[i][0] += digitsLen + temp[i - 1][0] * digitsLen;
            }
        }
        return temp[limitMaxStrLen][0] + temp[limitMaxStrLen][1];
    }
}
