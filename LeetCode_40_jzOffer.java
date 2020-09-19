import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/
 * @since 2020-06-30 12:52
 */
public class LeetCode_40_jzOffer {

    class Solution {

        /**
         * 大根堆
         */
        public int[] getLeastNumbers1(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
            for (int num : arr) {
                if (pq.size() < k) {
                    pq.offer(num);
                } else if (pq.peek() > num) {
                    pq.poll();
                    pq.offer(num);
                }
            }
            int[] res = new int[k];
            int idx = 0;
            for (int num : pq) {
                res[idx++] = num;
                if (idx == k) {
                    return res;
                }
            }
            return res;
        }

        /**
         * 快排
         */
        public int[] getLeastNumbers2(int[] arr, int k) {
            if (k == 0 || arr.length == 0) {
                return new int[0];
            }
            // 注意最后一个参数传入我们要找的下标（第 k 小的数下标是 k - 1）
            return quickSearch(arr, 0, arr.length - 1, k - 1);
        }

        private int[] quickSearch(int[] nums, int lo, int hi, int k) {
            // 每快排切分 1 次，找到排序后下标为 j 的元素，如果 j 恰好等于 k 就返回 j 及 j 左边所有的数
            int j = partition(nums, lo, hi);
            if (j == k) {
                return Arrays.copyOf(nums, j + 1);
            }
            // 否则根据下标 j 与 k 的大小关系来决定继续切分左段还是右段
            return j > k ? quickSearch(nums, lo, j - 1, k) : quickSearch(nums, j + 1, hi, k);
        }

        // 快排切分，返回下标 j，使得比 nums[j] 小的数都在 j 的左边，比 nums[j] 大的数都在 j 的右边
        private int partition(int[] nums, int lo, int hi) {
            int v = nums[lo];
            int i = lo, j = hi + 1;
            while (true) {
                // 比 nums[j] 小的数都在 j 的左边，比 nums[j] 大的数都在 j 的右边
                while (++i <= hi && nums[i] < v);
                while (--j >= lo && nums[j] > v);
                if (i >= j) {
                    break;
                }
                int t = nums[j];
                nums[j] = nums[i];
                nums[i] = t;
            }
            // 交换 nums[lo] 和 nums[j] 的值，并返回 j
            nums[lo] = nums[j];
            nums[j] = v;
            return j;
        }

    }

}
