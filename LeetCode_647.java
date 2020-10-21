
/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/palindromic-substrings/
 * @since 2020-10-21
 */
public class LeetCode_647 {

    class Solution {

        /**
         * dp
         * <p>
         *     定义：dp[i][j] 表示字符串 s 在 [i, j] 区间的子串是否是一个回文串
         *     状态转移方程：当 s[i] == s[j] && (j - i < 2 || dp[i + 1][j - 1]) 时，dp[i][j] = true，否则为 false
         *     解释：
         *     <p>
         *         1、当只有一个字符时，比如 a 自然是一个回文串
         *         2、当有两个字符时，如果是相等的，比如 aa，也是一个回文串。
         *         3、当有三个及以上字符时，比如 ababa 这个字符记作串 1，把两边的 a 去掉，也就是 bab 记作串 2，
         *              可以看出只要串2是一个回文串，那么左右各多了一个 a 的串 1 必定也是回文串。
         *              所以当 s[i]==s[j] 时，自然要看 dp[i+1][j-1] 是不是一个回文串
         */
        public int countSubstrings1(String s) {
            int len = s.length();
            boolean[][] dp = new boolean[len][len];
            int ans = 0;
            for (int j = 0; j < len; j++) {
                for (int i = 0; i <= j; i++) {
                    if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                        dp[i][j] = true;
                        ans++;
                    }
                }
            }
            return ans;
        }

        /**
         * 中心扩散法：围绕中心点向两边扩散
         * <p>
         *     确定中心点的数量：中心点不能只有单个字符构成，还要包括两个字符，比如上面这个子串 abab，就可以有中心点 ba 扩展一次得到，
         *                    所以最终的中心点由 2 * len - 1 个，分别是 len 个单字符和 len - 1 个双字符
         */
        public int countSubstrings2(String s) {
            int ans = 0;
            // center 中心点
            for (int center = 0; center < 2 * s.length() - 1; center++) {
                // left 和 right 指针和中心点的关系是？
                // 首先是left，有一个很明显的2倍关系的存在，其次是 right，可能和 left 指向同一个（偶数时），也可能往后移动一个（奇数）
                int left = center / 2;
                int right = left + center % 2;
                while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                    ans++;
                    left--; // 向左
                    right++; // 向右
                }
            }
            return ans;
        }

    }

}

