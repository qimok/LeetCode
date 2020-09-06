/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/happy-number/
 * @since 2020-04-30 13:33
 */
public class LeetCode_202 {

    class Solution {

        /**
         * 快慢指针
         * <p>
         *     如果 n 是一个快乐数，即没有循环，那么快跑者最终会比慢跑者先到达数字 1
         *     如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇
         */
        public boolean isHappy(int n) {
            int slow = n, fast = squareSum(n);
            while (slow != fast) {
                slow = squareSum(slow);
                fast = squareSum(squareSum(fast));
            }
            return slow == 1;
        }

        /**
         * 计算每一位平方的和
         */
        private int squareSum(int n) {
            int sum = 0;
            // 待所有位上的数都计算完平方和为止
            while (n > 0) {
                // 取余数
                int digit = n % 10;
                // 计算余数的和
                sum += digit * digit;
                // 相当于十进制右移一位
                n /= 10;
            }
            return sum;
        }

    }
}
