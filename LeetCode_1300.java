/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/sum-of-mutated-array-closest-to-target/
 * @since 2020-06-14 13:17
 */
public class LeetCode_1300 {

    class Solution {

        /**
         * 二分查找
         */
        public int findBestValue(int[] arr, int target) {
            int left = 0, right = 0;
            for (int num : arr) {
                right = Math.max(num, right);
            }
            while (left < right) {
                int mid = left + (right - left) / 2;
                int sum = calculateSum(arr, mid);
                if (sum < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            // 比较阈值线分别定在 left - 1 和 left 的时候与 target 的接近程度
            int sum1 = calculateSum(arr, left - 1);
            int sum2 = calculateSum(arr, left);
            // 题意要求：如果有多种使得和最接近 target 的方案，请你返回这些整数中的最小值
            if (target - sum1 <= sum2 - target) {
                return left - 1;
            }
            return left;
        }

        private int calculateSum(int[] arr, int threshold) {
            int sum = 0;
            for (int num : arr) {
                sum += Math.min(num, threshold);
            }
            return sum;
        }

    }

}
