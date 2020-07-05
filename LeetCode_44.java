/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/wildcard-matching/
 * @since 2020-07-05 18:01
 */
public class LeetCode_44 {

    class Solution {

        /**
         * 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配 (true 的话表示匹配)
         * 状态转移方程：
         *   1. 当 s[i] == p[j]，或者 p[j] == ? 那么 dp[i][j] = dp[i - 1][j - 1];
         *   2. 当 p[j] == * 那么 dp[i][j] = dp[i][j - 1] || dp[i - 1][j]    其中：
         *   dp[i][j - 1] 表示 * 代表的是空字符，例如 ab, ab*
         *   dp[i - 1][j] 表示 * 代表的是非空字符，例如 abcd, ab*
         * 初始化：
         *   1. dp[0][0] 表示什么都没有，其值为 true
         *   2. 第一行 dp[0][j]，换句话说，s 为空，与 p 匹配，所以只要 p 开始为 * 才为 true
         *   3. 第一列 dp[i][0]，当然全部为 false
         */
        public boolean isMatch1(String s, String p) {
            int m = s.length();
            int n = p.length();
            // 状态 dp[i][j] : 表示 s 的前 i 个字符和 p 的前 j 个字符是否匹配
            boolean[][] dp = new boolean[m + 1][n + 1];

            // 初始化
            dp[0][0] = true;
            for (int i = 1; i <= n; i++) {
                dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
            }

            // 状态转移
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if(p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                    }
                }
            }
            return dp[m][n];
        }

        /**
         * 双指针 + 贪心
         */
        public boolean isMatch2(String s, String p) {
            // i 和 j 分别代表 s 的指针和 p 的指针，iStar 和 jStar 分别表示星号在 s 和 p 中匹配的位置
            int i = 0, j = 0, iStar = -1, jStar = -1, m = s.length(), n = p.length();
            while (i < m) {
                if (j < n && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
                    ++i;
                    ++j;
                } else if (j < n && p.charAt(j) == '*') { // 记录如果之后序列匹配不成功时， i 和 j 需要回溯到的位置
                    iStar = i; // 记录遇到星号时，i 指针的位置
                    jStar = j++; // 记录遇到星号时，j 指针的位置，同时 j 移到下一位，准备下个循环 s.charAt(i) 和 p.charAt(j) 的匹配
                } else if (iStar >= 0) { // 发现字符不匹配且没有星号出现，但 istar >= 0 时，说明可能是 * 匹配的字符数量不对，此时回溯
                    i = ++iStar; // i 回溯到 istar + 1 的位置，因为上次从 s 串 istar 开始对 * 的尝试匹配已经被证明是不成功的（不然不会落入此分支），所以需要从 istar + 1 再开始试，同时 istar 自增更新回溯位置
                    j = jStar + 1; // j 回溯到 jstar + 1，重新使用 p 串 * 后的部分开始对 s 串 istar（这个 istar 在上一行已经自增过了）位置及之后字符的匹配
                } else {
                    return false;
                }
            }
            // 去除多余星号
            while (j < n && p.charAt(j) == '*') {
                ++j;
            }
            return j == n;
        }

    }

}
