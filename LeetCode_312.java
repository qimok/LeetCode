/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/burst-balloons/
 * @since 2020-07-19
 */
public class LeetCode_312 {

    class Solution {

        /**
         * dp
         * <p>
         *     dp[i][j] = x 表示，戳破气球 i 和气球 j 之间（开区间，不包括 i 和 j）的所有气球，可以获得的最高分数为 x
         *     根据 dp 数组的定义，如果最后一个戳破气球 k，dp[i][j] 的值应该为：
         *          dp[i][j] = dp[i][k] + dp[k][j]+ points[i] * points[k] * points[j]
         */
        public int maxCoins(int[] nums) {
            int n = nums.length;
            // 添加两侧的虚拟气球
            int[] points = new int[n + 2];
            points[0] = points[n + 1] = 1;
            for (int i = 1; i <= nums.length; i++) {
                points[i] = nums[i - 1];
            }
            // base case 已经都被初始化为 0
            int[][] dp = new int[n + 2][n + 2];
            // 开始状态转移（对于任一 dp[i][j]，希望所有 dp[i][k] 和 dp[k][j] 已经被计算，故要么斜着遍历，要么从下到上从左到右遍历）
            // i 应该从下往上
            for (int i = n; i >= 0; i--) {
                // j 应该从左往右
                for (int j = i + 1; j < n + 2; j++) {
                    // 最后戳破的气球是哪个？
                    for (int k =  i + 1; k < j; k++) {
                        // 择优做选择
                        dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[i] * points[k] * points[j]);
                    }
                }
            }
            return dp[0][n + 1];
        }

    }

}
