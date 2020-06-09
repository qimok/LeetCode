/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 * @since 2020-06-09 13:28
 */
public class LeetCode_46 {

    class Solution {

        /**
         * dp
         * <p>
         *     状态定义：设动态规划列表 dp ，dp[i] 代表以 x_i为结尾的数字的翻译方案数量
         *     转移方程：若 x_i 和 x_i-1 组成的两位数字可以被翻译，则 dp[i] = dp[i - 1] + dp[i - 2]；否则 dp[i] = dp[i - 1]
         *     初始状态：dp[0] = dp[1] = 1，即 “无数字” 和 “第 11 位数字” 的翻译方法数量均为 1
         *     返回值：dp[n] ，即此数字的翻译方案数量
         *     1 2 2 5 8
         *   b a -->
         */
        public int translateNum1(int num) {
            String s = String.valueOf(num);
            int a = 1, b = 1;
            // 正序
            for (int i = 2; i <= s.length(); i++) {
                String temp = s.substring(i - 2, i);
                // 10x_i−1 + x_i ∈[10,25]时，dp[i] = dp[i − 1] + dp[i − 2]
                // 10x_i−1 + x_i ∈ [0,10) ∪ (25,99]时，dp[i] = dp[i - 1]
                int c = temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0 ? a + b : a;
                b = a;
                a = c;
            }
            return a;
        }

        /**
         * 数字求余：优化空间复杂度为 O(1)
         * <p>
         *     1 2 2 5 8
         *         <-- a  b
         */
        public int translateNum2(int num) {
            int a = 1, b = 1, x, y = num % 10;
            // 倒序
            while (num != 0) {
                num /= 10;
                x = num % 10;
                int temp = 10 * x + y;
                int c = temp >= 10 && temp <= 25 ? a + b : a;
                b = a;
                a = c;
                y = x;
            }
            return a;
        }

    }

}
