/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * @since 2020-07-10 13:21
 */
public class LeetCode_309 {

    class Solution {

        /**
         * dp
         * <p>
         *     dp[i][j] 表示 [0, i] 区间内，在下标为 i 这一天状态为 j 时的最大收益
         *     j 取三个值：
         *         0 表示不持股；
         *         1 表示持股；
         *         2 表示处在冷冻期
         *     不持股可以由这两个状态转换而来：
         *         昨天不持股，今天什么都不操作，仍然不持股
         *         昨天持股，今天卖了一股
         *     持股可以由这两个状态转换而来：
         *         昨天持股，今天什么都不操作，仍然持股
         *         昨天处在冷冻期，今天买了一股
         *     处在冷冻期只可以由不持股转换而来，因为题目中说，刚刚把股票卖了，需要冷冻 1 天
         */
        public int maxProfit1(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int[][] dp = new int[len][3];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            for (int i = 1; i < len; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][2] - prices[i]);
                dp[i][2] = dp[i - 1][0];
            }
            return Math.max(dp[len - 1][0], dp[len - 1][2]);
        }

        /**
         * 滚动数组
         */
        public int maxProfit2(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int[][] dp = new int[2][3];
            dp[0][0] = 0;
            dp[0][1] = -prices[0];
            dp[0][2] = 0;
            for (int i = 1; i < len; i++) {
                dp[i & 1][0] = Math.max(dp[(i - 1) & 1][0], dp[(i - 1) & 1][1] + prices[i]);
                dp[i & 1][1] = Math.max(dp[(i - 1) & 1][1], dp[(i - 1) & 1][2] - prices[i]);
                dp[i & 1][2] = dp[(i - 1) & 1][0];
            }
            return Math.max(dp[(len - 1) & 1][0], dp[(len - 1) & 1][2]);
        }

        public int maxProfit3(int[] prices) {
            int len = prices.length;
            if (len < 2) {
                return 0;
            }
            int[] dp = new int[3];
            dp[0] = 0;
            dp[1] = -prices[0];
            dp[2] = 0;
            // 不持股
            int preCash = dp[0];
            // 持股
            int preStock = dp[1];
            for (int i = 1; i < len; i++) {
                dp[0] = Math.max(preCash, preStock + prices[i]);
                dp[1] = Math.max(preStock, dp[2] - prices[i]);
                // 处在冷冻期只可以由不持股转换而来（由方法1中分析可得）
                dp[2] = preCash;
                preCash= dp[0];
                preStock = dp[1];
            }
            return Math.max(dp[0], dp[2]);
        }

    }

}
