/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/maximum-length-of-repeated-subarray/
 * @since 2020-07-01 13:13
 */
public class LeetCode_718 {

    class Solution {

        /**
         * dp
         * <p>
         *     如果 A[i] == B[j]，那么 A[i:] 与 B[j:] 的最长公共前缀为 A[i + 1:] 与 B[j + 1:] 的最长公共前缀的长度加 1，
         *     否则 A[i:] 与 B[j:] 的最长公共前缀为零
         *
         *     >>>>
         *
         *     令 dp[i][j] 表示 A[i:] 和 B[j:] 的最长公共前缀，那么答案即为所有 dp[i][j] 中的最大值。
         *     如果 A[i] == B[j]，那么 dp[i][j] = dp[i + 1][j + 1] + 1，否则 dp[i][j] = 0。
         *
         *     "上面借用了 Python 表示数组的方法，A[i:] 表示数组 A 中索引 i 到数组末尾的范围对应的子数组"
         */
        public int findLength1(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int ans = 0;
            int[][] dp = new int[m + 1][n + 1];
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    dp[i][j] = A[i] == B[j] ? dp[i + 1][j + 1] + 1 : 0;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            return ans;
        }

        public int findLength2(int[] A, int[] B) {
            int m = A.length, n = B.length;
            int ans = 0;
            // A 不动，B 动
            for (int i = 0; i < n; i++) {
                int len = Math.min(m, n - i);
                ans = Math.max(ans, maxLen(A, B, 0, i, len));
            }
            // A动，B不动
            for (int i = 0; i < m; i++) {
                int len = Math.min(n, m - i);
                ans = Math.max(ans, maxLen(A, B, i, 0, len));
            }
            return ans;
        }

        /**
         * 返回最长的相同子数组长度
         */
        private int maxLen(int[] A, int[] B, int startA, int startB, int len) {
            int ans = 0, count = 0;
            for (int i = 0; i < len; i++) {
                if (A[startA + i] == B[startB + i]) {
                    count++;
                } else {
                    // count 置 0，从头开始
                    count = 0;
                }
                ans = Math.max(ans, count);
            }
            return ans;
        }

    }

}
