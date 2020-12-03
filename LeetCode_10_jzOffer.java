/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/
 * @since 2020-12-2
 */
public class LeetCode_10_jzOffer {

    class Solution {

        /**
         * dp[i] 表示到达第 i 阶台阶的跳法总数
         * base case：dp[1] = 1, dp[2] = 2
         */
        public int numWays1(int n) {
            if (n <= 1) {
                return 1;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
                // 题目要求
                dp[i] %= 1000000007;
            }
            return dp[n];
        }

        /**
         * 简化空间
         */
        public int numWays2(int n) {
            if (n <= 1) {
                return 1;
            }
            int first = 1, second = 2;
            for (int i = 3; i <= n; i++) {
                int tmp = first + second;
                first = second % 1000000007;
                second = tmp % 1000000007;
            }
            return second;
        }

    }

}
