/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/minimum-cost-for-tickets/
 * @since 2020-05-06 13:17
 */
public class LeetCode_983 {

    class Solution {

        /**
         * 动态规划：逆推
         * <p>
         *     dp方程：dp[i]：第 i 天开始，所需花费的最小费用
         *     时间复杂度：O(maxDay - minDay)
         *     空间复杂度：O(maxDay)
         */
        public int mincostTickets(int[] days, int[] costs) {
            int len = days.length, maxDay = days[len - 1], minDay = days[0];
            int[] dp = new int[maxDay + 31]; // 由于是倒序，故多扩几天，防止为期30天的通行证导致数组越界
            for (int d = maxDay, i = len - 1; d >= minDay; d--) {
                if (i == days[d]) { // 需要出门，则求三种决策的花费的最小的金额
                    /**
                     * dp[i] = min(决策1,             决策2,            决策3);
                     *       = min(c[0] + 1天后不包,  c[1] + 7天后不包,  c[2] + 30天不包);
                     *       = min(c[0] + dp[i + 1], c[1] + dp[i + 7], c[2] + dp[i + 30]);
                     */
                    dp[d] = Math.min(costs[0] + dp[d + 1], costs[1] + dp[d + 7]);
                    dp[d] = Math.min(dp[d], costs[2] + dp[d + 30]);
                    i--;
                } else {
                    dp[d] = dp[d + 1]; // 不需要出门
                }
            }
            return dp[minDay];
        }

    }

}
