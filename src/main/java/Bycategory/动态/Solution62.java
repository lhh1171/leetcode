package Bycategory.动态;

//二元dp数组
//去第i+1行和第j+1列的方式是dp[i][j]
//去第i行和第j+1列的方式是dp[i-1][j],右移一步到ij
//去第i+1行和第j列的方式是dp[i][j-1],下移一步到ij
public class Solution62 {
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        //初始化
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }
}
