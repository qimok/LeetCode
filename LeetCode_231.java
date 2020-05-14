/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/power-of-two/
 * @since 2020-05-13 22:51
 */
public class LeetCode_231 {

    class Solution {

        /**
         * 2 的幂次方最多只有一位为 1，且 2 的 n 次幂肯定是正数
         * <p>
         *     00001        2^0 = 1
         *     00010        2^1 = 2
         *     00100        2^2 = 4
         *     01000        2^3 = 8
         *     10000        2^3 = 16
         *
         *
         */
        public boolean isPowerOfTwo(int n) {
            return (n > 0) && (n & (n - 1)) == 0;
        }

    }

}
