import java.util.Arrays;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-increasing-subsequence/
 * @since 2020-11-5
 */
public class LeetCode_300 {

    class Solution {

        /**
         * dp 定义：dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
         * base case：dp[i] 初始值为 1，因为以 nums[i] 结尾的最长递增子序列起码要包含它自己
         */
        public int lengthOfLIS1(int[] nums) {
            int[] dp = new int[nums.length];
            // base case
            Arrays.fill(dp, 1);
            for (int i = 0; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int res = 0;
            for (int i = 0; i < dp.length; i++) {
                res = Math.max(res, dp[i]);
            }
            return res;
        }

        /**
         * 二分查找
         * <p>
         *     参考题解：https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/dong-tai-gui-hua-she-ji-fang-fa-zhi-pai-you-xi-jia/
         */
        public int lengthOfLIS2(int[] nums) {
            // 每个牌堆中最底下的那张牌（即每个牌堆中最大的那张牌）
            int[] top = new int[nums.length];
            // 牌堆数初始化为 0
            int piles = 0;
            for (int i = 0; i < nums.length; i++) {
                // 当前要处理的扑克牌
                int poker = nums[i];
                // 搜索左侧边界的二分查找
                int left = 0, right = piles;
                while (left < right) {
                    int mid = left + (right - left) / 2;
                    if (top[mid] > poker) {
                        right = mid;
                    } else if (top[mid] < poker) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                if (left == piles) {
                    // 前置要求：只能把点数小的牌压到点数比它大的牌上，如果当前牌点数较大没有可以放置的堆，则新建一个堆
                    // 遍历完最后一个牌堆之后，还是没有找到合适的牌堆，则新建一堆
                    piles++;
                }
                // 把这张牌放到牌堆顶
                top[left] = poker;
            }
            // 牌堆数就是 LIS 长度
            return piles;
        }

    }

}
