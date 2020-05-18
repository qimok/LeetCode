/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/maximum-product-subarray/
 * @since 2020-05-18 13:05
 */
public class LeetCode_152 {

    class Solution {

        /**
         * 暴力求解
         */
        public int maxProduct1(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            if (nums.length == 1) {
                return nums[0];
            }
            int res = 0; // 最大的乘积
            for (int i = 0; i < nums.length; i++) {
                int ans = 1; // 乘积
                for (int j = i; j < nums.length; j++) {
                    ans *= nums[j];
                    res = Math.max(res, ans);
                }
            }
            return res;
        }

        /**
         * 动态规划（dp）
         * <p>
         *     dp_max[i] 表示以第 i 个数结尾的 乘积最大 的连续子序列
         *     dp_min[i] 表示以第 i 个数结尾的 乘积最小 的连续子序列
         */
        public int maxProduc2(int[] nums) {
            if (nums.length == 0) return 0;
            int[] dp_max = new int[nums.length + 1];
            int[] dp_min = new int[nums.length + 1];
            int max = Integer.MIN_VALUE;
            // 由于存在负数，所以需要维护两个数组
            dp_max[0] = 1;
            dp_min[0] = 1;
            for (int i = 1; i <= nums.length; i++) {
                // 如果数组的数是负数，那么会导致 max 变成 min，min 变成 max，故需要交换 dp
                if (nums[i - 1] < 0) {
                    int temp = dp_min[i - 1];
                    dp_min[i - 1] = dp_max[i - 1];
                    dp_max[i - 1] = temp;
                }
                dp_max[i] = Math.max(dp_max[i - 1] * nums[i - 1], nums[i - 1]);
                dp_min[i] = Math.min(dp_min[i - 1] * nums[i - 1], nums[i - 1]);
                max = Math.max(max, dp_max[i]);
            }
            return max;
        }

        /**
         * dp 抽象
         * <p>
         *     可以不通过数组来存储状态
         */
        public int maxProduc3(int[] nums) {
            int max = Integer.MIN_VALUE, imax = 1, imin = 1;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] < 0) {
                    int temp = imax;
                    imax = imin;
                    imin = temp;
                }
                imax = Math.max(imax * nums[i], nums[i]);
                imin = Math.min(imin * nums[i], nums[i]);
                max = Math.max(max, imax);
            }
            return max;
        }

    }

}
