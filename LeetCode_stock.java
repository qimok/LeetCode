/**
 * @author qimok
 * @description 股票问题
 * @since 2020-05-12 10:09
 */
public class LeetCode_stock {


    /**
     * 股票问题：
     * <p>
     *    状态转移框架：
     *    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *                  max(   选择 rest  ,           选择 sell      )
     *    解释：今天我没有持有股票，有两种可能：
     *    要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     *    要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     *    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *                  max(   选择 rest  ,           选择 buy         )
     *    解释：今天我持有着股票，有两种可能：
     *    要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     *    要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     *    base case：
     *    dp[-1][k][0] = 0
     *    解释：因为 i 是从 0 开始的，所以 i = -1 意味着还没有开始，这时候的利润当然是 0 。
     *    dp[-1][k][1] = -infinity
     *    解释：还没开始的时候，是不可能持有股票的，用负无穷表示这种不可能。
     *    dp[i][0][0] = 0
     *    解释：因为 k 是从 1 开始的，所以 k = 0 意味着根本不允许交易，这时候利润当然是 0 。
     *    dp[i][0][1] = -infinity
     *    解释：不允许交易的情况下，是不可能持有股票的，用负无穷表示这种不可能。
     *
     *    >>>>>>>>>>>>>>>>>>>>>>>>>>> 总结 <<<<<<<<<<<<<<<<<<<<<<<<<<<
     *
     *    base case：
     *    dp[-1][k][0] = dp[i][0][0] = 0
     *    dp[-1][k][1] = dp[i][0][1] = -infinity
     *
     *    状态转移方程：
     *    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *
     */
    class Solution {

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k == 1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[121题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
         * <p>
         *    状态转移方程：
         *    dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
         *    dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
         *    由于本题要求的交易次数为1，故进行如下转化
         *    dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
         *    dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
         *               = max(dp[i-1][1][1], -prices[i])
         *    解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
         *
         *    现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
         *    可以进一步化简去掉所有 k：
         *    dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         *    dp[i][1] = max(dp[i-1][1], -prices[i])
         */
        public int maxProfit1(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int[][] dp = new int[len][2];
            for (int i = 0; i < len; i++) {
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    dp[i][1] = -prices[i];
                    continue;
                }
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i - 1][1], - prices[i]);
            }
            return dp[len - 1][0]; // 第二维参数：[1] 代表手上还持有股票，[0] 表示手上的股票已经卖出去了，很显然后者得到的利润一定大于前者
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k == 1 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[121题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/
         * 状态压缩
         */
        public int maxProfit2(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, -prices[i]);
            }
            return dp_i_0;
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k = +infinity <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[122题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
         * 如果 k 为正无穷，那么就可以认为 k 和 k - 1 是一样的。可以这样改写框架：
         *      dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
         *      dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
         *                  = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])
         *
         *      我们发现数组中的 k 已经不会改变了，也就是说不需要记录 k 这个状态了：
         *      dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         *      dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])
         */
        public int maxProfit3(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
            }
            return dp_i_0;
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k = +infinity with cooldown <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[309题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/submissions/
         * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         * dp[i][1] = max(dp[i-1][1], dp[i-2][0] - prices[i])
         * 解释：第 i 天选择 buy 的时候，要从 i-2 的状态转移，而不是 i-1 。
         */
        public int maxProfit4(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            int dp_pre_0 = 0; // 代表 dp[i-2][0]
            for (int i = 0; i < len; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);
                dp_pre_0 = temp;
            }
            return dp_i_0;
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k = +infinity with fee <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[714题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/submissions/
         * 每次交易要支付手续费，只要把手续费从利润中减去即可。改写方程：
         *
         * dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
         * dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i] - fee)
         * 解释：相当于买入股票的价格升高了。
         * 在第一个式子里减也是一样的，相当于卖出股票的价格减小了。
         */
        public int maxProfit(int[] prices, int fee) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < len; i++) {
                int temp = dp_i_0;
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                dp_i_1 = Math.max(dp_i_1, dp_i_1 - prices[i] - fee);
            }
            return dp_i_0;
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k = 2 <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[123题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/
         * k = 2 和前面题目的情况稍微不同，因为上面的情况都和 k 的关系不太大。要么 k 是正无穷，状态转移和 k 没关系了；要么 k = 1，跟 k = 0 这个 base case 挨得近，最后也没有存在感。
         * 这道题 k = 2 和后面要讲的 k 是任意正整数的情况中，对 k 的处理就凸显出来了
         */
        public int maxProfit(int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            int maxK = 2;
            int dp[][][] = new int[len][maxK + 1][2];
            for (int i = 0; i < len; i++) {
                for (int k = maxK; k > 0; k--) { // 由于没有消掉 k 的影响，所以必须要对 k 进行穷举
                    if (i - 1 == -1) {
                        dp[i][k][0] = 0;
                        dp[i][k][1] = -prices[i];
                        continue;
                    }
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
            return dp[len - 1][maxK][0];
        }

        /**
         * >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> k = any integer <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
         * 题目链接[188题]：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/submissions/
         * 一次交易由买入和卖出构成，至少需要两天。所以说有效的限制 k 应该不超过 n/2，如果超过，就没有约束作用了，相当于 k = +infinity
         */
        public int maxProfit(int k, int[] prices) {
            int len = prices.length;
            if (len == 0) {
                return 0;
            }
            if (k > len / 2) {
                return maxProfit3(prices);
            }
            int[][][] dp = new int[len][k + 1][2];
            for (int i = 0; i < len; i++) {
                for (int j = k; j > 0; j--) {
                    if (i - 1 == -1) {
                        dp[i][j][0] = 0;
                        dp[i][j][1] = -prices[i];
                        continue;
                    }
                    dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i - 1][j][1] + prices[i]);
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], dp[i - 1][j - 1][0] - prices[i]);
                }
            }
            return dp[len - 1][k][0];
        }

    }

}
