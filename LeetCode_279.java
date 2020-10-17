
/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/perfect-squares/
 * @since 2020-10-16
 */
public class LeetCode_279 {

    class Solution {

        /**
         * dp
         * <p>
         *     dp[n] 的定义：输入一个目标值 n，返回凑出目标值 n 的最少的平方数的数量
         *     动态转移方程：dp[i] = Math.min(dp[i], dp[i - j * j] + 1)
         */
        public int numSquares(int n) {
            int[] dp = new int[n + 1]; // 默认初始化值都为 0
            for (int i = 1; i <= n; i++) {
                dp[i] = i; // 最坏的情况是每次都加1
                for (int j = 1; i - j * j >= 0; j++) {
                    dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                }
            }
            return dp[n];
        }

    }

}
