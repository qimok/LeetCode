/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/product-of-array-except-self/
 * @since 2020-06-04 23:44
 */
public class LeetCode_238 {

    class Solution {

        /**
         * 左右乘积列表
         */
        public int[] productExceptSelf1(int[] nums) {
            int len = nums.length;

            // left 和 right 分别表示左右两侧的乘积列表
            int[] left = new int[len], right = new int[len];
            int[] answer = new int[len];

            // left[i] 为索引 i 左侧所有元素的乘积
            // 对于索引为 '0' 的元素，因为左侧没有元素，所以 L[0] = 1
            left[0] = 1;
            for (int i = 1; i < len; i++) {
                left[i] = nums[i - 1] * left[i - 1];
            }
            // right[i] 为索引 i 右侧所有元素的乘积
            // 对于索引为 'length-1' 的元素，因为右侧没有元素，所以 R[length-1] = 1
            right[len - 1] = 1;
            for (int i = len - 2; i >= 0; i--) {
                right[i] = nums[i + 1] * right[i + 1];
            }

            // 对于索引 i，除 nums[i] 之外其余各元素的乘积就是左侧所有元素的乘积乘以右侧所有元素的乘积
            for (int i = 0; i < len; i++) {
                answer[i] = left[i] * right[i];
            }
            return answer;
        }

        /**
         * 左右乘积列表 + 空间优化
         */
        public int[] productExceptSelf2(int[] nums) {
            int length = nums.length;
            int[] answer = new int[length];

            // answer[i] 表示索引 i 左侧所有元素的乘积
            // 因为索引为 '0' 的元素左侧没有元素， 所以 answer[0] = 1
            answer[0] = 1;
            for (int i = 1; i < length; i++) {
                answer[i] = nums[i - 1] * answer[i - 1];
            }
            // right 为右侧所有元素的乘积
            // 刚开始右边没有元素，所以 right = 1
            int right = 1;
            for (int i = length - 1; i >= 0; i--) {
                // 对于索引 i，左边的乘积为 answer[i]，右边的乘积为 right
                answer[i] = answer[i] * right;
                // right 需要包含右边所有的乘积，所以计算下一个结果时需要将当前值乘到 right 上
                right *= nums[i];
            }
            return answer;
        }

    }

}
