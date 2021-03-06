/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/target-sum/
 * @since 2020-09-20
 */
public class LeetCode_494 {

    class Solution {

        int count = 0;

        /**
         * 递归
         */
        public int findTargetSumWays1(int[] nums, int S) {
            calculate(nums, 0, 0 , S);
            return count;
        }

        private void calculate(int[] nums, int i, int sum, int S) {
            if (i == nums.length) {
                if (sum == S) {
                    count++;
                }
            } else {
                calculate(nums, i + 1, sum + nums[i], S);
                calculate(nums, i + 1, sum - nums[i], S);
            }
        }

        /**
         * base case：
         * <p>
         *     dp[i][0] = 1
         *     dp[0][j] = 1
         * 状态定义：dp[i][j] 表示用数组中的前 i 个元素，组成和为 j 的方案数
         * 状态转移：dp[i][j] = dp[i - 1][j - nums[i]] + dp[i - 1][j + nums[i]]
         *         或
         *         dp[i][j + nums[i]] += dp[i - 1][j]
         *         dp[i][j - nums[i]] += dp[i - 1][j]
         *         注意：由于数据中所有数的和不超过 1000，那么 j 的最小值可以达到 -1000，由于数组下标不能为负数，故需要给 dp[i][j] 的第二维预先增加 1000
         *         故状态转移方程：
         *         dp[i][j + nums[i] + 1000] += dp[i - 1][j + 1000]
         *         dp[i][j - nums[i] + 1000] += dp[i - 1][j + 1000]
         */
        public int findTargetSumWays2(int[] nums, int S) {
            int[][] dp = new int[nums.length][2001];
            dp[0][nums[0] + 1000] += 1;
            dp[0][-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[i - 1][sum + 1000] > 0) {
                        dp[i][sum + nums[i] + 1000] += dp[i - 1][sum + 1000];
                        dp[i][sum - nums[i] + 1000] += dp[i - 1][sum + 1000];
                    }
                }
            }
            return S > 1000 ? 0 : dp[nums.length - 1][S + 1000];
        }

        /**
         * 观察发现，上面方法的状态转移方程中，dp[i][...] 只和 dp[i - 1][...] 有关，因为可以优化动态规划的空间复杂度，只需要使用两个一维数组即可
         */
        public int findTargetSumWays3(int[] nums, int S) {
            int[] dp = new int[2001];
            dp[nums[0] + 1000] += 1;
            dp[-nums[0] + 1000] += 1;
            for (int i = 1; i < nums.length; i++) {
                int[] next = new int[2001];
                for (int sum = -1000; sum <= 1000; sum++) {
                    if (dp[sum + 1000] > 0) {
                        next[sum + nums[i] + 1000] += dp[sum + 1000];
                        next[sum - nums[i] + 1000] += dp[sum + 1000];
                    }
                }
                dp = next;
            }
            return S > 1000 ? 0 : dp[S + 1000];
        }

    }
    
}
