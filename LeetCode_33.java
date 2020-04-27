/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array/
 * @since 2020-04-27 13:22
 */
public class LeetCode_33 {

    class Solution {

        /**
         * 二分法1
         */
        public int search1(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1, mid;
            while (lo <= hi) {
                mid = lo + (hi - lo) / 2;
                if (target == nums[mid]) {
                    return mid;
                }
                // 先根据 target 和 nums[0] 判断目标值在左边还是右边
                if (target >= nums[0]) {
                    // 目标值在左半段，若 mid 在右半段，则将 mid 索引值改为 Integer.MAX_VALUE
                    if (nums[mid] < nums[0]) {
                        nums[mid] = Integer.MAX_VALUE;
                    }
                    // 目标值在左半段，若 mid 在左半段，无需操作，因为 nums[mid] 肯定大于 target
                } else {
                    // 目标值在右半段，若 mid 在左半段，则将 mid 索引值改为 Integer.MIN_VALUE
                    if (nums[mid] >= nums[0]) {
                        nums[mid] = Integer.MIN_VALUE;
                    }
                    // 目标值在右半段，若 mid 在右半段，无需操作，因为nums[mid]肯定小于target
                }
                // 二分查找
                if (target > nums[mid]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return -1;
        }

        /**
         * 二分法2
         */
        public int search2(int[] nums, int target) {
            int lo = 0, hi = nums.length - 1, mid;
            while (lo <= hi) {
                mid = lo + (hi - lo) / 2;
                if (nums[mid] == target) {
                    return mid;
                }
                // 先根据 nums[mid] 与 nums[lo] 的关系判断 mid 是在左边还是右边
                if (nums[mid] >= nums[lo]) {
                    // mid 在左边，再判断 target 是在 mid 的左边还是右边，从而调整左右边界 lo 和 hi
                    if (target >= nums[lo] && target < nums[mid]) {
                        // target 在 mid 的左边
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                } else {
                    // mid 在右边
                    if (target > nums[mid] && target <= nums[hi]) {
                        // target 在 mid 的右边
                        lo = mid + 1;
                    } else {
                        hi = mid - 1;
                    }
                }
            }
            return -1;
        }

    }
}
