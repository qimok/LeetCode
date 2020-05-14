/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/single-number/
 * @since 2020-05-14 13:37
 */
public class LeetCode_136 {

    class Solution {

        /**
         * 异或
         * <p>
         *     任何数和 00 做异或运算，结果仍然是原来的数，即 a ⊕ 0 = a
         *     任何数和其自身做异或运算，结果是 0，即 a ⊕ a = 0
         *     异或运算满足交换律和结合律，即 a ⊕ b ⊕ a = b ⊕ a ⊕ a = b ⊕ (a ⊕ a) = b ⊕ 0 = b
         */
        public int singleNumber(int[] nums) {
            int res = 0;
            for (int num : nums) {
                res ^= num;
            }
            return res;
        }

    }

}
