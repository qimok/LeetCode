/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/regular-expression-matching/
 * @since 2020-06-20 15:49
 */
public class LeetCode_10 {

    class Solution {

        /**
         * 递归回溯
         */
        public boolean isMatch1(String s, String p) {
            if (p.isEmpty()) {
                // 当 p 为空时，只需要查看 s 是否为空即可
                return s.isEmpty();
            }
            // 查看首元素是否一致
            boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
            if (p.length() >= 2 && p.charAt(1) == '*') {
                // 当 p 的下一个元素是 * 时，* 消掉之前的元素 || * 匹配之前的元素
                return isMatch1(s, p.substring(2)) || (firstMatch && isMatch1(s.substring(1), p));
            } else {
                // 一般情况
                return firstMatch && isMatch1(s.substring(1), p.substring(1));
            }
        }

        /**
         * dp
         * <p>
         *     dp[i][j]表示：s 的前 i 个字符和 p 的前 j 个字符是否匹配
         */
        public boolean isMatch2(String s, String p) {
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
            dp[0][0] = true;
            for (int j = 2; j <= p.length(); j++) {
                // s 为空时，填二维表
                // dp[0][j]：是辅助行，用于后续判断
                dp[0][j] = p.charAt(j - 1) == '*' && dp[0][j - 2];
            }
            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < p.length(); j++) {
                    // i 为 0 时，表示 s 为空；j 为 0 时，表示 p 为空
                    if (p.charAt(j) == '*') {
                        // 一种是 s 取下一个元素，p 取前一个元素，看是否可以匹配上('*' 匹配零个或多个"前面的那一个元素")
                        // 另一种是 s 不变，p 取 * 前一个元素，即看当前首字符是否相同，假如相同，s 不变，p 取下一个元素，看看 p 的下一个元素与 s 当前元素是否可以匹配
                        dp[i + 1][j + 1] = dp[i + 1][j - 1] || firstMatch(s, p, i, j - 1) && dp[i][j + 1];
                    } else {
                        // 一般情况
                        dp[i + 1][j + 1] = firstMatch(s, p, i, j) && dp[i][j];
                    }
                }
            }
            return dp[s.length()][p.length()];
        }

        private boolean firstMatch(String s, String p, int i, int j) {
            return s.charAt(i) == p.charAt(j) || p.charAt(j) == '.';
        }

    }

}
