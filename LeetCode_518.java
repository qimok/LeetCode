/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/coin-change-2/
 * @since 2020-10-18
 */
public class LeetCode_518 {

    class Solution {

        /**
         * dp
         * <p>
         *     dp[i] 表示：当目标金额为 i 时的组合数
         *     base case：dp[0] = 1
         *     动态转移方程：dp[i] += dp[i - coin]
         */
        public int change(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= amount; i++) {
                    dp[i] += dp[i - coin];
                }
            }
            return dp[amount];
        }

    }

}
