import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array/
 * @since 2020-06-29 12:57
 */
public class LeetCode_215 {

    class Solution {

        /**
         * 暴力求解：
         * <p>
         *     语义是从右边往左边数第 k 个元素（从 1 开始），那么从左向右数是第几个呢？下面找下规律
         *      一共 6 个元素，找第 2 大，索引是 4
         *      一共 6 个元素，找第 4 大，索引是 2
         *      因此，升序排序以后，目标元素的索引是 len - k
         */
        public int findKthLargest1(int[] nums, int k) {
            Arrays.sort(nums);
            return nums[nums.length - k];
        }

        /**
         * 最小堆
         */
        public int findKthLargest2(int[] nums, int k) {
            int len = nums.length;
            PriorityQueue<Integer> minHeap = new PriorityQueue<>(len, (a, b) -> a - b);
            for (int i = 0; i < len; i++) {
                minHeap.add(nums[i]);
            }
            for (int i = 0; i < len - k; i++) {
                minHeap.poll();
            }
            return minHeap.peek();
        }

        /**
         * 最大堆
         */
        public int findKthLargest3(int[] nums, int k) {
            int len = nums.length;
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(len, (a, b) -> b - a);
            for (int i = 0; i < len; i++) {
                maxHeap.add(nums[i]);
            }
            for (int i = 0; i < k - 1; i++) {
                maxHeap.poll();
            }
            return maxHeap.peek();
        }

    }

}
