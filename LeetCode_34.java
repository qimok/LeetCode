/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * @since 2020-11-18
 */
public class LeetCode_34 {

    class Solution {

        /**
         * 二分查找（很多细节一定注意）
         * <p>
         *     参考：https://labuladong.gitbook.io/algo/di-ling-zhang-bi-du-xi-lie/er-fen-cha-zhao-xiang-jie
         */
        public int[] searchRange(int[] nums, int target) {
            int[] res = {-1, -1};
            // 先判断第一个位置
            int leftIdx = binarySearch(nums, target, true);
            if (leftIdx == nums.length || nums[leftIdx] != target) {
                return res;
            }
            res[0] = leftIdx;
            // 再判断最后一个位置
            res[1] = binarySearch(nums, target, false) - 1; // 细节
            return res;
        }

        private int binarySearch(int[] nums, int target, boolean isLeft) {
            int len = nums.length;
            int left = 0, right = len - 1; // 细节
            while (left <= right) { // 细节
                int mid = left + (right - left) / 2;
                if (nums[mid] > target) {
                    right = mid - 1;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] == target) {
                    if (isLeft) {
                        right = mid - 1; // 向左收缩
                    } else {
                        left = mid + 1; // 向右收缩
                    }
                }
            }
            return left;
        }

    }

}
