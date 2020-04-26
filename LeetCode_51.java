/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/
 * @since 2020-04-24 23:12
 */
public class LeetCode_51 {

    class Solution {

        /**
         * 归并排序方法（分治思想）：
         * <p>
         *     归并排序：
         *     分解：待排序的区间 [l, r]，令 m = [(l + r) / 2]，我们把 [l, r] 分成 [l, m] 和 [m + 1, r]
         *     解决：使用归并排序递归地排序两个子序列
         *     合并：把两个已经排好序的子序列 [l, m] 和 [m + 1， r] 合并起来
         *     <p>
         *         本题思路：
         *         1、对输入数组一直进行拆分，直到区间里只剩下一个元素的时候
         *         2、一个元素的数组肯定是有序的数组，然后再依次合并两个有序的数组
         *         3、计算逆序对的过程就发生在合并的过程中，每次合并完成后，得到一个更长的有序子区间
         *         4、同时也为下一次做好合并做好了准备，直至整个数组有序，也就得到了整个数组的逆序对的个数
         *
         */
        public int reversePairs(int[] nums) {
            int len = nums.length;
            if (len < 2) {
                return 0;
            }
            int[] copy = new int[len];
            for (int i = 0; i < len; i++) {
                copy[i] = nums[i];
            }
            int[] temp = new int[len];
            return reversePairs(copy, 0, len - 1, temp);
        }

        private int reversePairs(int[] nums, int left, int right, int[] temp) {
            if (left == right) {
                return 0;
            }

            int mid = left + (right - left) / 2;
            int leftPairs = reversePairs(nums, left, mid, temp);
            int rightPairs = reversePairs(nums, mid + 1, right, temp);

            if (nums[mid] <= nums[mid + 1]) {
                return leftPairs + rightPairs;
            }

            int crossPairs = mergeAndCount(nums, left, mid, right, temp);
            return leftPairs + rightPairs + crossPairs;
        }

        /**
         * 合并两个有序的子数组，并计算出合并后的逆序对
         */
        private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
            for (int i = left; i <= right; i++) {
                temp[i] = nums[i];
            }

            int i = left; // 左边子数组的第一个位置
            int j = mid + 1; // 右边子数组的第一个位置

            int count = 0;
            for (int k = left; k <= right; k++) {
                if (i == mid + 1) {
                    nums[k] = temp[j];
                    j++;
                } else if (j == right + 1) {
                    nums[k] = temp[i];
                    i++;
                } else if (temp[i] <= temp[j]) {
                    nums[k] = temp[i];
                    i++;
                } else {
                    nums[k] = temp[j];
                    j++;
                    count += mid - i + 1;
                }
            }
            return count;
        }

    }

}
