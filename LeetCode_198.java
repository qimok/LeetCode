/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/house-robber/submissions/
 * @since 2020-05-29 13:50
 */
public class LeetCode_198 {

    class Solution {

        /**
         * dp
         * <p>
         *     读题知输入一个长度为 n 的数组，表示 n 个房屋，让我们求到第 n 个房屋的最大金额。
         *     想一下到第n个房屋的最大金额 dp[n]:
         *          1. 如果偷第 n 个房屋的话，由于相邻的房屋不能偷，所以 dp[n] = dp[n - 2] + nums[n]（即等于到第 n - 2 个房屋的最大金额 + 第 n 个房屋的金额）
         *          2. 反之如果不偷第 n 个房屋的话，那么 dp[n] = dp[n - 1]（即等于到第 n - 1 个房屋的最大金额）
         *     于是，得到状态转移方程：dp[i] = max(dp[i - 1], dp[i - 2] + nums[i])
         */
        public int rob1(int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            if (len == 1) {
                return nums[0];
            }
            int[] dp = new int[len];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < len; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[len - 1];
        }

        /**
         * dp 简化
         * <p>
         *     参照上面 dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]) 这个方程来写
         *
         *     用 a 表示 i - 2，b 表示 i - 1，
         *
         *     >> 上面的方程变成了dp[i] = max(b, a + nums[i])，
         *
         *     然后下一轮循环的时候：
         *          i - 1 就变成了i - 2，因此现在需要把 b 赋值给 a；
         *          i 就变成了 i - 1，因此现在需要把 dp[i] 赋值给 b
         */
        public int rob2(int[] nums) {
            int a = 0, b = 0;
            for (int i = 0; i < nums.length; i++) {
                int c = Math.max(b, a + nums[i]);
                a = b;
                b = c;
            }
            return b;
        }

    }

}
