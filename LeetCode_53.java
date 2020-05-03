/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/maximum-subarray/
 * @since 2020-05-03 22:55
 */
public class LeetCode_53 {

    class Solution {

        /**
         * 贪心算法：在循环中不断找到当前最大的 sum 和 max
         * <p>
         *     时间复杂度：O(n)
         *     空间复杂度：O(1)
         */
        public int maxSubArray1(int[] nums) {
            int max = nums[0], sum = nums[0];
            for (int i = 1; i < nums.length; i++) {
                sum = Math.max(nums[i], sum + nums[i]);
                max = Math.max(sum, max);
            }
            return max;
        }

        /**
         * 动态规划：
         * <p>
         *     dp[i]：以i结尾的子串的最大值
         *     dp[0] = nums[0]
         *     if (dp[i - 1] >= 0) dp[i] = nums[i] + dp[i - 1]
         *     if (dp[i - 1] < 0) dp[i] = nums[i]
         *     时间复杂度：O(n)
         *     空间复杂度：O(n)
         *
         */
        public int maxSubArray2(int[] nums) {
            int len = nums.length;
            int[] dp = new int[len];
            dp[0] = nums[0];
            for (int i = 1; i < len; i++) {
                if (dp[i - 1] >= 0) {
                    // 前一个子组合的最大值大于0，正增益
                    dp[i] = dp[i - 1] + nums[i];
                } else {
                    // 前一个子组合的最大值小于0，抛弃前面的结果
                    dp[i] = nums[i];
                }
            }
            int res = dp[0];
            for (int i = 1; i < len; i++) {
                res = Math.max(res, dp[i]);
            }
            return res;
        }

        /**
         * 动态规划的压缩版：
         * <p>
         *     时间复杂度：O(n)
         *     空间复杂度：O(1)
         */
        public int maxSubArray3(int[] nums) {
            int len = nums.length;
            // max：全局最大值，subMax：前一个子组合的最大值，状态压缩
            int max = nums[0], subMax = nums[0];
            for (int i = 1; i < len; i++) {
                if (subMax >= 0) {
                    // 前一个子组合的最大值大于0，正增益
                    subMax = subMax + nums[i];
                } else {
                    // 前一个子组合的最大值小于0，抛弃前面的结果
                    subMax = nums[i];
                }
                // 计算全局最大值
                max = Math.max(max, subMax);
            }
            return max;
        }

        /**
         * 分治法
         * <p>
         *     时间复杂度：O(nlogn)
         *     空间复杂度：O(1)
         */
        public int maxSubArray4(int[] nums) {
            return maxSubArrayDivideWithBorder(nums, 0, nums.length - 1);
        }

        private int maxSubArrayDivideWithBorder(int[] nums, int start, int end) {
            // 递归终止条件
            if (start == end) {
                return nums[start];
            }
            int center = start + (end - start) / 2;
            int leftMax = maxSubArrayDivideWithBorder(nums, start, center); // 计算左侧子序列最大值
            int rightMax = maxSubArrayDivideWithBorder(nums, center + 1, end); // 计算右侧子序列最大值

            // 下面计算横跨两个子序列的最大值

            // 计算包含左侧子序列最后一个元素的子序列最大值
            int leftCrossMax = Integer.MIN_VALUE;
            int leftCrossSum = 0;
            for (int i = center; i >= start; i--) {
                leftCrossSum += nums[i];
                leftCrossMax = Math.max(leftCrossMax, leftCrossSum);
            }

            // 计算包含右侧子序列最后一个元素的子序列最大值
            int rightCrossMax = nums[center + 1];
            int rightCrossSum = 0;
            for (int i = center + 1; i <= end; i++) {
                rightCrossSum += nums[i];
                rightCrossMax = Math.max(rightCrossMax, rightCrossSum);
            }

            // 计算跨中心的子序列的最大值
            int crossMax = leftCrossMax + rightCrossMax;

            // 比较三者，返回最大值
            return Math.max(crossMax, Math.max(leftMax, rightMax));
        }

    }

}
