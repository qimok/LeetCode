/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/jump-game/
 * @since 2020-10-27
 */
public class LeetCode_55 {

    class Solution {

        /**
         * dp
         * <dp>
         *     1、状态定义：
         *     dp[i] 表示节点 i 可达
         *     2、状态转移：
         *     if (dp[j] && j + nums[j] >= i) dp[i] = true
         */
        public boolean canJump1(int[] nums) {
            if (nums == null) {
                return false;
            }
            boolean[] dp = new boolean[nums.length];
            dp[0] = true;
            for (int i = 1; i < nums.length; i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && j + nums[j] >= i) {
                        // 如果之前的 j 节点可达，并且从此节点可以到跳到 i，说明 i 节点可达
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[nums.length - 1];
        }

        /**
         * 贪心算法
         * <p>
         *     问题：设想一下，对于数组中的任意一个位置 y，我们如何判断它是否可以到达？
         *     回答：根据题目的描述，只要存在一个位置 x，它本身可以到达，并且它跳跃的最大长度为 x + nums[x]，这个值大于等于 y，
         *     即 x + nums[x] ≥ y，那么位置 y 也可以到达。
         */
        public boolean canJump2(int[] nums) {
            int len = nums.length;
            int rightMost = 0;
            for (int i = 0; i < len; i++) {
                if (i <= rightMost) {
                    // 对于 位置i，它本身可以达到（i <= rightMost）
                    rightMost = Math.max(rightMost, i + nums[i]);
                }
                if (rightMost >= len - 1) {
                    // 如果 位置i 可以跳跃的最大长度大于等于 最后一个位置，则代表可以到达最后一个位置
                    return true;
                }
            }
            return false;
        }

    }

}
