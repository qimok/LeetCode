/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @since 2020-05-26 23:26
 */
public class LeetCode_287 {

    class Solution {

        /**
         * 二分查找
         * <p>
         *     二分法的思路是先猜一个数（有效范围 [left, right]里的中间数 mid），
         *     然后统计原始数组中 "小于等于" 这个中间数的元素的个数 cnt，如果 cnt "严格大于" mid，
         *     根据抽屉原理，重复元素就在区间 [left, mid] 里
         *
         *     以 [2, 4, 5, 2, 3, 1, 6, 7] 为例，一共 8 个数，n + 1 = 8，n = 7，根据题目意思，每个数都在 1 和 7 之间。
         *
         *     例如：区间 [1, 7] 的中位数是 4，遍历整个数组，统计小于等于 4 的整数的个数，最多为 4 个，等于 4 的时候也可能有重复。
         *     但是，如果整个数组里小于等于 4 的整数的个数严格大于 4 的时候，就可以说明重复的数存在于区间 [1, 4]
         */
        public int findDuplicate1(int[] nums) {
            int len = nums.length;
            int left = 1;
            int right = nums.length - 1;
            while (left < right) {
                int mid = (left + right) >>> 1;
                int cnt = 0;
                for (int num : nums) {
                    if (num <= mid) {
                        cnt += 1;
                    }
                }
                if (cnt > mid) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        /**
         * 快慢指针
         * <p>
         *                    -------C
         *                   ｜顺时针 ｜
         *     A-------------B--------
         *     设快慢指针从 A 出发相遇在点 C
         *     设环的长度为 L
         *     慢指针路程：AB + BC
         *     快指针路程：2(AB + BC)
         *     因为相遇，2(AB + BC) - (AB + BC) = L，即 L = AB + BC
         *     因为 BC + CB = L，所以 AB = CB
         *     所以两个指针再以 1 的速度分别从 A 点 和 C 点出发，会相遇在点 B
         */
        public int findDuplicate2(int[] nums) {
            int slow = 0, fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);
            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }

    }

}
