/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/is-subsequence/
 * @since 2020-07-27
 */
public class LeetCode_392 {

    class Solution {

        /**
         * 穷举
         */
        public boolean isSubsequence1(String s, String t) {
            int i = 0;
            for (char ch : s.toCharArray()) {
                while (i < t.length() && t.charAt(i) != ch) {
                    i++;
                }
                if (i++ > t.length()) {
                    break;
                }
            }
            return i <= t.length();
        }

        /**
         * 双指针
         */
        public boolean isSubsequence2(String s, String t) {
            int n = s.length(), m = t.length();
            int i = 0, j = 0;
            while (i < n && j < m) {
                if (s.charAt(i) == t.charAt(j)) {
                    i++;
                }
                j++;
            }
            return i == n;
        }

        /**
         * dp
         *         0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25
         *         a  b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z (26个英文字母)
         *  0 " "  1 -1 -1  9  ...
         *  1  a   4 -1 -1  9  ...
         *  2  v   4 -1 -1  9  ...
         *  3  e   4 -1 -1  9  ...
         *  4  a  10 -1 -1  9  ...
         *  5  n  10 -1 -1  9  ...
         *  6  i  10 -1 -1  9  ...     布阵
         *  7  c  10 -1 -1  9  ...
         *  8  e  10 -1 -1  9  ...
         *  9  d  10 -1 -1 -1  ...
         * 10  a  10 -1 -1 -1  ...
         * 11  y  -1 -1 -1 -1  ...
         *    (t)
         *
         *   (s) : a d a
         *   1：i = 0, ch - 'a' = 0, i = 1
         *   2: i = 1, ch - 'd' = 3, i = 9
         *   3: i = 9, ch - 'a' = 0, i = 10
         *   over...
         */
        public boolean isSubsequence3(String s, String t) {
            // 预处理
            t = " " + t; // 开头加一个空字符作为匹配入口
            int n = t.length();
            int[][] dp = new int[n][26]; // 记录每个位置的下一个ch的位置
            for (int ch = 0; ch < 26; ch++) { // 从每列
                int p = -1;
                for (int i = n - 1; i >= 0; i--) { // 从后往前记录dp
                    dp[i][ch] = p;
                    if (t.charAt(i) == ch + 'a') {
                        p = i;
                    }
                }
            }
            // 匹配
            int i = 0;
            for (char ch : s.toCharArray()) { // 跳跃遍历
                i = dp[i][ch - 'a'];
                if (i == -1) {
                    return false;
                }
            }
            return true;
        }

    }

}
