/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/sqrtx/
 * @since 2020-05-09 13:23
 */
public class LeetCode_69 {

    class Solution {

        /**
         * 二分查找法
         */
        public int mySqrt1(int x) {
            // 单独照顾 0 这个特例
            if (x == 0) {
                return 0;
            }
            long left = 1;
            long right = x / 2; // 一个数一半的平方大于它自己,故右边界设置为这个数的一半
            while (left < right) {
                /**
                 * 不取左中位数的原因：
                 * <p>
                 *     当 x == 9，计算到区间 [3, 4] 的时候，其中位数假如为左中位数，即 mid = 3 ，此时 square = 9 < 9 不成立，进入 left = mid 这个分支
                 *     此时，区间没有发生收缩，即下一轮循环的索引区间还是 [3, 4]
                 *     此时，中位数还取左中位数，即 mid = 3 ，square = 9 < 9 不成立，又进入 left = mid 这个分支
                 *     即产生了死循环
                 */
                // 为了防止死循环,取右中位数
                long mid = left + (right - left + 1) / 2;
                long square = mid * mid;
                if (square > x) {
                    right = mid - 1;
                } else {
                    // square <= x,有可能等于,则 left = mid
                    left = mid;
                }
            }
            return (int)left;
        }

        /**
         * 牛顿迭代法
         * <p>
         *     公式：x = (x + a / x) / 2
         */
        public int mySqrt2(int a) {
            long x = a;
            while (x * x > a) {
                x = (x + a / x) / 2;
            }
            return (int)x;
        }

    }

}
