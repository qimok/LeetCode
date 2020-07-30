/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/integer-break/
 * @since 2020-07-30
 */
public class LeetCode_343 {

    class Solution {

        /**
         * dp
         * <p>
         *     创建数组 dp，其中 dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。特别地，0 不是正整数，1 是最小的正整数，0 和 1 都不能拆分，因此 dp[0]=dp[1]=0
         *     当 i ≥ 2 时，假设对正整数 i 拆分出的第一个正整数是 j（1 ≤ j < i），则有以下两种方案：
         *          将 i 拆分成 j 和 i − j 的和，且 i − j 不再拆分成多个正整数，此时的乘积是 j × (i − j)
         *          将 i 拆分成 j 和 i − j 的和，且 i − j 继续拆分成多个正整数，此时的乘积是 j × dp[i − j]
         *     因此，当 j 固定时，有 dp[i] = max(j × (i − j), j × dp[i − j])。由于 j 的取值范围是 1 到 i − 1，需要遍历所有的 j 得到 dp[i] 的最大值
         */
        public int integerBreak(int n) {
            int[] dp = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                int curMax = 0;
                for (int j = 1; j < i; j++) {
                    curMax = Math.max(curMax, Math.max(j * (i - j), j * dp[i - j]));
                }
                dp[i] = curMax;
            }
            return dp[n];
        }

    }

}
