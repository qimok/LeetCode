/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/valid-palindrome/
 * @since 2020-06-19 13:17
 */
public class LeetCode_125 {

    class Solution {

        /**
         * 字符串反转
         */
        public boolean isPalindrome1(String s) {
            StringBuilder sb = new StringBuilder();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                Character ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }
            }
            StringBuilder sbRev = new StringBuilder(sb).reverse();
            return sbRev.toString().equals(sb.toString());
        }

        /**
         * 去除无用字符 + 双指针
         */
        public boolean isPalindrome2(String s) {
            StringBuilder sb = new StringBuilder();
            int len = s.length();
            for (int i = 0; i < len; i++) {
                Character ch = s.charAt(i);
                if (Character.isLetterOrDigit(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }
            }
            int left = 0, right = sb.length() - 1;
            while (left < right) {
                if (sb.charAt(left) != sb.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        /**
         * 双指针 + 在字符串上直接判断
         */
        public boolean isPalindrome3(String s) {
            int len = s.length();
            int left = 0, right = len - 1;
            while (left < right) {
                while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                    left++;
                }
                while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                    right--;
                }
                if (left < right) {
                    if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                        return false;
                    }
                    left++;
                    right--;
                }
            }
            return true;
        }

    }

}
