import java.util.Arrays;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/split-array-largest-sum/
 * @since 2020-07-25
 */
public class LeetCode_410 {

    class Solution {

        /**
         * 二分查找
         */
        public int splitArray1(int[] nums, int m) {
            if (nums == null || nums.length == 0 || m == 0) {
                return 0;
            }
            // 由于题目的返回要求：返回最小和的值
            // 最小和必然落在 [max(nums), sum(nums)] 之间
            // 可以使用二分来进行查找
            // 令 low = max(nums)，high = sum(nums)
            int low = 0, high = 0;
            for (int num : nums) {
                low = Math.max(low, num);
                high += num;
            }
            while (low < high) {
                int mid = low + (high - low) / 2;
                /**
                 * 淘汰算法：
                 * 由前向后对 nums 进行划分，使其子数组和 <= mid，然后统计满足条件的数组数量
                 * 若选的 sum 值过小，则满足条件的数量 > m，目标值应落在 [mid+1, high]
                 * 若选的 sum 值过大，则满足条件的数量 < m，目标值应落在 [low, mid-1]
                 */
                // count：数组和最大值不大于 mid 对应的子数组个数
                int tmp = 0, count = 1;
                for (int num : nums) {
                    tmp += num;
                    if (tmp > mid) {
                        tmp = num;
                        count++;
                    }
                }
                if (count > m) {
                    // 说明划分的子数组多了
                    low = mid + 1;
                } else {
                    // 说明划分的子数组少了
                    high = mid;
                }
            }
            return low;
        }

        /**
         * dp（没彻底搞懂...）
         * <p>
         *     dp[i][j]：将数组的前 i 个数分割成 j 段所能得到的最大连续子数组和的最小值
         */
        public int splitArray2(int[] nums, int m) {
            int n = nums.length;
            int[] sum = new int[n + 1];
            // 初始化 sum 数组
            for (int i = 0; i < n; i++) {
                sum[i + 1] = sum[i] + nums[i];
            }
            int[][] dp = new int[n + 1][m + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }
            dp[0][0] = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= Math.min(i, m); j++) {
                    // 枚举拆分的子数组的个数
                    for (int k = 0; k < i; k++) {
                        // 枚举k，其中前k个数被分割为 j - 1 段，而第 k + 1 到第 i 个数为第 j 段
                        dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sum[i] - sum[k]));
                    }
                }
            }
            return dp[n][m];
        }

    }

}
