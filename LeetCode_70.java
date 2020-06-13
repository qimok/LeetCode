/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/climbing-stairs/
 * @since 2020-06-13 19:13
 */
public class LeetCode_70 {

    class Solution {

        /**
         * dp
         * <p>
         *     用 dp(x) 表示爬到第 x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以我们可以列出如下式子：
         *     dp(x) = dp(x - 1) + dp(x - 2)
         *     意味着爬到第 x 级台阶的方案数是爬到第 x - 1 级台阶的方案数和爬到第 x - 2 级台阶的方案数的和
         */
        public int climbStairs1(int n) {
            if (n <= 2) {
                return n;
            }
            int[] dp = new int[n + 1];
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            return dp[n];
        }

        /**
         * 构建斐波那契数列
         * <p>
         *     i = 0,   1,   2,   3,   4,   5
         *         1,   1,   2,   3,   5,   8
         *       left  right -->
         *                  left  right -->
         *                            left  right -->
         */
        public int climbStairs2(int n) {
            int left = 1, right = 1;
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    left = left + right;
                } else {
                    right = left + right;
                }
            }
            return n % 2 == 0 ? left : right;
        }

        /**
         * 构建斐波那契数列
         * <p>
         *     0, 1, 2, 3, 4
         *     1, 1, 2, 3, 5
         *  i  j  -->
         *     i  j  -->
         */
        public int climbStairs3(int n) {
            int i = 0, j = 1;
            while(n-- > 0){
                j = i + j;
                i = j - i;
            }
            return j;
        }

    }

}
