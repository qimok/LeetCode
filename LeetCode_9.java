/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/palindrome-number/
 * @since 2020-06-10 12:52
 */
public class LeetCode_9 {

    class Solution {

        /**
         * 粗暴解法：违背题目要求
         */
        public boolean isPalindrome1(int x) {
            String reversedStr = (new StringBuilder(x + "")).reverse().toString();
            return (x + "").equals(reversedStr);
        }

        /**
         * 双指针法：违背题目要求
         */
        public boolean isPalindrome2(int x) {
            String str = String.valueOf(x);
            int len = str.length();
            int left = 0, right = len - 1;
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 数学解法
         * <p>
         *     举个例子：1221 这个数字
         *          通过计算 1221 / 1000， 得首位1
         *          通过计算 1221 % 10， 可得末位 1
         *          进行比较
         *          再将 22 取出来继续比较
         */
        public boolean isPalindrome3(int x) {
            if (x < 0) {
                return false;
            }
            int div = 1;
            while (x / div >= 10) {
                div *= 10;
            }
            while (x > 0) {
                int left = x / div;
                int right = x % 10;
                if (left != right) {
                    return false;
                }
                x = (x % div) / 10;
                div /= 100;
            }
            return true;
        }

        /**
         * 折半解法
         * <p>
         *     取出后半段数字进行翻转：
         *          每次进行取余操作（%10），取出最低的数字：y = x % 10
         *          将最低的数字加到取出数的末尾：revertNum = revertNum * 10 + y
         *          每取一个最低位数字，x 都要自除以 10
         *          判断 x 是不是小于 revertNum ，当它小于的时候，说明数字已经对半或者过半了
         *          最后，判断奇偶数情况：
         *              如果是偶数的话，revertNum 和 x 相等；
         *              如果是奇数的话，最中间的数字就在revertNum 的最低位上，将它除以 10 以后应该和 x 相等
         */
        public boolean isPalindrome4(int x) {
            if (x < 0 || (x % 10 == 0 && x != 0)) {
                return false;
            }
            int reversedNumber = 0;
            while (x > reversedNumber) {
                reversedNumber = reversedNumber * 10 + x % 10;
                x /= 10;
            }
            return x == reversedNumber || x == reversedNumber / 10;
        }

    }

}
