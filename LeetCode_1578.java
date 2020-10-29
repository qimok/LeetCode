/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/
 * @since 2020-10-28
 */
public class LeetCode_1578 {

    class Solution {

        /**
         * 动态规划
         * <p>
         *     dp[i]：表示到达第 i 个元素时的最小花费
         *     base case：dp[0] = 0 (到达第 0 个元素时，还没有遇到重复元素，故初始花费为 0)
         *     状态转移：if (s.charAt(i) == s.charAt(i - 1)) dp[i] = Math.min(cost[i], cost[i - 1]) + dp[i - 1];
         */
        public int minCost1(String s, int[] cost) {
            int len = s.length();
            int[] dp = new int[len];
            dp[0] = 0;
            for (int i = 1; i < len; i++) {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    dp[i] = Math.min(cost[i], cost[i - 1]) + dp[i - 1];
                    // 直接修改 cost 数组，将成本较高的成本值赋给当前位置
                    cost[i] = Math.max(cost[i], cost[i - 1]);
                } else {
                    dp[i] = dp[i - 1];
                }
            }
            return dp[len - 1];
        }

        /**
         * 贪心算法
         * <p>
         *     贪心：体现在每轮迭代中若发现两个相同的相邻字符则直接删除代价较小的那个，得到局部的最优解
         */
        public int minCost2(String s, int[] cost) {
            int len = s.length();
            int costSum = 0;
            for (int i = 0; i < len; i++) {
                int maxCost = cost[i];
                while (i < len - 1 && s.charAt(i) == s.charAt(i + 1)) {
                    // 一直循环到相邻没有重复元素为止
                    i++;
                    int curCost = cost[i];
                    // 花费的肯定是成本较少的元素
                    costSum += Math.min(maxCost, curCost);
                    // 留下的肯定是成本较大的元素
                    maxCost = Math.max(maxCost, curCost);
                }
            }
            return costSum;
        }

        /**
         * 贪心算法 + 双指针
         * <p>
         *     贪心：体现在每轮迭代中若发现两个相同的相邻字符则直接删除代价较小的那个，得到局部的最优解
         */
        public int minCost3(String s, int[] cost) {
            int len = s.length();
            if (len <= 1) {
                return 0;
            }
            int costSum = 0;
            int i = 0, j = 1;
            while (j < len) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (cost[i] > cost[j]) {
                        costSum += cost[j];
                    } else {
                        costSum += cost[i];
                        i = j;
                    }
                } else {
                    i = j;
                }
                j++;
            }
            return costSum;
        }

    }

}
