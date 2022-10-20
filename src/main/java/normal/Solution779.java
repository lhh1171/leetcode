package normal;
/*
* 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。

例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）


示例 1:

输入: n = 1, k = 1
输出: 0
解释: 第一行：0
*
*
*
*
* 输入: n = 2, k = 1
输出: 0
解释:
第一行: 0
第二行: 01
*
*
* */
class Solution779 {

    //递归
    public int kthGrammar1(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (k & 1) ^ 1 ^ kthGrammar1(n - 1, (k + 1) / 2);
    }

    //按位求解qu
    /*
    * 0
    * 01
    * 0110
    * 01101001
    * 0110100110010110*/
    public int kthGrammar2(int n, int k) {
        // return Integer.bitCount(k - 1) & 1;
        k--;
        int res = 0;
        while (k > 0) {
            //取反
            k &= k - 1;
            //亦或
            res ^= 1;
        }
        return res;
    }
}
