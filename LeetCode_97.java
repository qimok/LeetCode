/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/interleaving-string/
 * @since 2020-07-18
 */
public class LeetCode_97 {

    class Solution {

        /**
         * dp
         * <p>
         *     dp[i][j] 表示 s1 前 i 字符能与 s2 前 j 字符组成 s3 前 i + j 个字符
         */
        public boolean isInterleave(String s1, String s2, String s3) {
            int m = s1.length(), n = s2.length();
            if (m + n != s3.length()) {
                return false;
            }
            boolean[][] dp = new boolean[m + 1][n + 1];
            dp[0][0] = true;
            for (int i = 1; i <= m && s1.charAt(i - 1) == s3.charAt(i - 1); i++) {
                // 不相符直接终止
                dp[i][0] = true;
            }
            for (int j = 1; j <= n && s2.charAt(j - 1) == s3.charAt(j - 1); j++) {
                // 不相符直接终止
                dp[0][j] = true;
            }
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    dp[i][j] = (dp[i - 1][j] && s3.charAt(i + j - 1) == s1.charAt(i - 1)) ||
                            (dp[i][j - 1] && s3.charAt(i + j - 1) == s2.charAt(j - 1));
                }
            }
            return dp[m][n];
        }

    }

}
