/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/valid-palindrome-ii/
 * @since 2020-05-19 22:20
 */
public class LeetCode_680 {

    class Solution {

        /**
         * 二分查找(贪心)
         */
        public boolean validPalindrome1(String s) {
            char[] chars = s.toCharArray();
            int left = 0, right = chars.length - 1;
            while (left < right) {
                if (chars[left] == chars[right]) {
                    left++;
                    right--;
                } else {
                    boolean flag1 = true, flag2 = true;
                    for (int i = left + 1, j = right; i < j; i++, j--) {
                        if (chars[i] != chars[j]) {
                            flag1 = false;
                            break;
                        }
                    }
                    for (int i = left, j = right - 1; i < j; i++, j--) {
                        if (chars[i] != chars[j]) {
                            flag2 = false;
                            break;
                        }
                    }
                    return flag1 || flag2;
                }
            }
            return true;
        }

        /**
         * 二分查找(贪心)
         */
        public boolean validPalindrome2(String s) {
            int left = 0, right = s.length() - 1;
            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
                }
                // 如果相等，则继续比较 left++、right--
                left++;
                right--;
            }
            return true;
        }

        private boolean isPalindromeRange(String s, int left, int right) {
            for (int i = left; i < left + (right - left) / 2; i++) {
                if (s.charAt(i) != s.charAt(right - i + left)) { // i 在自增1的时候， right - i + left 相当于自减1
                    return false;
                }
            }
            return true;
        }

    }

}
