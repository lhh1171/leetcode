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
            // 在LimitMax当前位，
            // 遍历digits时候，
            // ==当前位的时候 temp[i][1] = temp[i-1][1],等于必须每一位都得等于
            // <当前位的时候  temp[i][0] += temp[i - 1][1]，digits每一位小于LimitMax当前位的个数的 + 上一位这个数的累乘
            for (String digit : digits) {
                if (digit.charAt(0) == limitMaxStr.charAt(i - 1)) {
                    temp[i][1] = temp[i - 1][1];
                } else if (digit.charAt(0) < limitMaxStr.charAt(i - 1)) {
                    temp[i][0] += temp[i - 1][1];
                } else {
                    break;
                }
            }
            if (i > 1) {
                //累乘
                temp[i][0] += digitsLen + temp[i - 1][0] * digitsLen;
            }
        }
        //每一位都小于  ， 有一位或者以上等于的数字
        return temp[limitMaxStrLen][0] + temp[limitMaxStrLen][1];
    }
}
