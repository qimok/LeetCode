/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/
 * @since 2020-08-23
 */
public class LeetCode_201 {

    class Solution {

        /**
         * 暴力法
         * <p>
         *     右边界 n 是 2147483647，也就是 Integer 中最大的正数，二进制形式是 01111...1，其中有 31 个 1。
         *     在代码中当 i 等于 n 的时候依旧会进入循环。出循环执行 i++，我们期望它变成 2147483647 + 1 = 2147483648，
         *     然后跳出 for 循环。事实上，对 2147483647 加 1，也就是 01111...1 加 1，变成了 1000..000。
         *     而这个二进制在补码中表示的是 -2147483648。因此我们依旧会进入 for 循环，以此往复，直到结果是 0 才出了 for 循环
         */
        public int rangeBitwiseAnd1(int m, int n) {
            if (m == Integer.MAX_VALUE) {
                return m;
            }
            int res = m;
            for (int i = m + 1; i <= n; i++) {
                res &= i;
                if (res == 0 || i == Integer.MAX_VALUE) {
                    return res;
                }
            }
            return res;
        }

        /**
         * 参与相与的数中最低位要么在第一个数字第一次出现 0 ，要么在第二个数字出现第一次出现 0
         * 如果 m < n，也就是参与相与的数字的个数至少有两个，所以一定会有 0 的出现，所以相与结果一定是 0
         * 只需要把 m 和 n 同时右移一位。然后继续按照上边的思路考虑新的最低位的结果即可
         * 而当 m == n 的时候，很明显，结果就是 m 了。
         * 代码中，需要用一个变量 zero 记录我们右移的次数，也就是最低位 0 的个数。
         */
        public int rangeBitwiseAnd2(int m, int n) {
            int zeros = 0;
            while (m < n) {
                zeros++;
                m >>>= 1;
                n >>>= 1;
            }
            return m << zeros;
        }

        /**
         * 对于解法二的升级
         * <p>
         *     对于任意一个数 n，然后 n & (n-1) 的结果就是把 n 的最右边的 1 置为 0
         */
        public int rangeBitwiseAnd3(int m, int n) {
            while (m < n) {
                n = n & (n - 1);
            }
            return n;
        }

    }

}
