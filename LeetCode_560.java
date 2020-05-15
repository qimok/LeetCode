import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/subarray-sum-equals-k/
 * @since 2020-05-15 13:22
 */
public class LeetCode_560 {

    class Solution {

        /**
         * 暴力求解
         */
        public int subarraySum1(int[] nums, int k) {
            int count = 0;
            for (int start = 0; start < nums.length; start++) {
                int sum = 0;
                for (int end = start; end >= 0; end--) {
                    sum += nums[end];
                    if (sum == k) {
                        count++;
                    }
                }
            }
            return count++;
        }

        /**
         * 前缀和 + 哈希表优化
         * <p>
         *     定义 pre[i] 为 [0..i] 里所有数的和，则 pre[i] 可以由 pre[i − 1] 递推而来，即：
         *     pre[i] = pre[i − 1] + nums[i]
         *
         *     那么 "[j..i] 这个子数组和为 k" 这个条件我们可以转化为
         *     pre[i] − pre[j − 1] == k
         *
         *     简单移项可得符合条件的下标 j 需要满足
         *     pre[j − 1] == pre[i] − k
         *     所以，我们考虑以 i 结尾的和为 k 的连续子数组个数时只要统计有多少个前缀和为 pre[i] − k 的 pre[j] 即可
         *
         *     建立哈希表 map，以和为键，出现次数为对应的值，记录 pre[i] 出现的次数，从左往右边更新 map 边计算答案，
         *     那么以 i 结尾的答案 map[pre[i] − k] 即可在 O(1) 时间内得到
         *     最后的答案即为所有下标结尾的和为 k 的子数组个数之和
         *
         *     需要注意的是，从左往右边更新边计算的时候已经保证了 map[pre[i] − k] 里记录的 pre[j] 的下标范围是 0 ≤ j ≤ i
         *     同时，由于 pre[i] 的计算只与前一项的答案有关，因此我们可以不用建立 pre 数组，直接用 pre 变量来记录 pre[i − 1] 的答案即可
         */
        public int subarraySum2(int[] nums, int k) {
            int sum = 0, count = 0, pre;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0 ,1); // 初始化，切记，切记，切记
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                pre = sum - k; // 获取前置节点
                if (map.containsKey(pre)) {
                    count += map.get(pre);
                }
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
            return count;
        }

    }

}
