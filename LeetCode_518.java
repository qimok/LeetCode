/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/coin-change-2/
 * @since 2020-10-18
 */
public class LeetCode_518 {

    class Solution {

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
