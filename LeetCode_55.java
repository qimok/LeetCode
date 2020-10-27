/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/jump-game-ii/
 * @since 2020-05-04 22:27
 */
public class LeetCode_45 {

    class Solution {

        /**
         * 顺藤摸瓜
         * <p>
         *     时间复杂度：O(n)
         *     空间复杂度：O(1)
         */
        public int jump1(int[] nums) {
            int end = 0;
            int maxPosition = 0;
            int steps = 0;
            for (int i = 0; i < nums.length - 1; i++) {
                // 找能跳的最远的位置
                maxPosition = Math.max(maxPosition, i + nums[i]);
                if (i == end) {
                    // 遇到边界，就更新边界，并且步数加一
                    end = maxPosition;
                    steps++;
                }
            }
            return steps;
        }

        /**
         * 顺瓜摸藤
         * <p>
         *     时间复杂度：O(n^2)
         *     空间复杂度：O(1)
         */
        public int jump2(int[] nums) {
            // 要找的位置
            int position = nums.length - 1;
            int steps = 0;
            // 是否到了第 0 个位置
            while (position != 0) {
                for (int i = 0; i < position; i++) {
                    if (nums[i] >= position - i) {
                        // 更新要找的位置
                        position = i;
                        steps++;
                        break;
                    }
                }
            }
            return steps;
        }

    }

}
