/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-palindromic-substring/
 * @since 2020-05-21 14:13
 */
public class LeetCode_5 {
    class Solution {

        /**
         * 暴力匹配
         * <p>
         *     时间复杂度：O(n^3)
         *     空间复杂度：O(n)
         */
        public String longestPalindrome1(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int len = s.length();
            int maxLen = 0, begin = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < len - 1; i++) {
                for (int j = i + 1; j < len; j++) {
                    if (j - i + 1 > maxLen && validPalindromic(chars, i, j)) {
                        maxLen = j - i + 1;
                        begin = i;
                    }
                }
            }
            return s.substring(begin, begin + maxLen);
        }

        private boolean validPalindromic(char[] chars, int left, int right) {
            while (left < right) {
                if (chars[left] != chars[right]) {
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        /**
         * dp
         * <p>
         *     状态转移方程：dp[i][j] = (s[i] == s[j]) and dp[i + 1][j - 1]
         */
        public String longestPalindrome2(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int strLen = s.length();
            int maxLen = 0, maxStart = 0, maxEnd = 0;
            boolean[][] dp = new boolean[strLen][strLen];
            for (int r = 1; r < strLen; r++) {
                for (int l = 0; l < r; l++) {
                    // r - l < 3 等价于 r - i + 1 < 4，即当子串 s[l..r] 的长度等于 2 或者等于 3 的时候，其实只需要判断一下头尾两个字符是否相等就可以直接下结论了
                    // 如果子串 s[l + 1..r - 1] 只有 1 个字符，即去掉两头，剩下中间部分只有 11 个字符，显然是回文
                    // 如果子串 s[l + 1..r - 1] 为空串，那么子串 s[l, r] 一定是回文子串
                    // 因此，在 s.charAt(l) == s.charAt(r) 成立和 r - l < 3 的前提下，直接可以下结论，dp[i][j] = true，否则才执行状态转移
                    if (s.charAt(l) == s.charAt(r) && (r - l < 3 || dp[l + 1][r - 1])) {
                        dp[l][r] = true;
                        if (r - l + 1 > maxLen) {
                            maxLen = r - l + 1;
                            maxStart = l;
                            maxEnd = r;
                        }
                    }
                }
            }
            // String.substring 左闭右开
            return s.substring(maxStart, maxEnd + 1);
        }

        /**
         * 中心扩散法
         */
        public String longestPalindrome3(String s) {
            if (s == null || s.length() < 2) {
                return s;
            }
            int maxLen = 1;
            String res = s.substring(0, 1);
            int len = s.length();
            // 中心位置枚举到 len - 2 即可（下面当取奇数的回文串时，有个 i + 1 操作）
            for (int i = 0; i < len - 1; i++) {
                String oddStr = centerSpread(s, i, i); // 偶子串
                String evenStr = centerSpread(s, i, i + 1); // 奇子串
                String maxLenStr = oddStr.length() > evenStr.length() ? oddStr : evenStr;
                if (maxLenStr.length() > maxLen) {
                    maxLen = maxLenStr.length();
                    res = maxLenStr;
                }
            }
            return res;
        }

        private String centerSpread(String s, int left, int right) {
            int len = s.length();
            // left = right 时，回文中心是一个字符，回文串的长度是奇数
            // right = left + 1 时，回文中心是一个空隙，回文串的长度是偶数
            int i = left;
            int j = right;
            while (i >= 0 && j < len) {
                if (s.charAt(i) == s.charAt(j)) {
                    i--;
                    j++;
                } else {
                    break;
                }
            }
            //跳出 while 循环时，恰好满足 s.charAt(i) != s.charAt(j)，因此不能取 i，不能取 j
            // String.substring 左闭右开
            return s.substring(i + 1, j);
        }

    }

}
