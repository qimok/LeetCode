/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/minimum-size-subarray-sum/
 * @since 2020-06-28 13:03
 */
public class LeetCode_209 {

    class Solution {

        /**
         * 暴力求解
         */
        public int minSubArrayLen1(int s, int[] nums) {
            int min = Integer.MAX_VALUE;
            int len = nums.length;
            for (int i = 0; i < len; i++) {
                int start = i, sum = 0;
                while (start < len) {
                    sum += nums[start++];
                    if (sum >= s) {
                        min = Math.min(min, start - i);
                        break;
                    }
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

        /**
         * 双指针
         */
        public int minSubArrayLen2(int s, int[] nums) {
            int len = nums.length;
            if (len == 0) {
                return 0;
            }
            int left = 0, right = 0, sum = 0, min = Integer.MAX_VALUE;
            while (right < len) {
                sum += nums[right++];
                while (sum >= s) {
                    min = Math.min(min, right - left);
                    sum -= nums[left++];
                }
            }
            return min == Integer.MAX_VALUE ? 0 : min;
        }

    }

}
