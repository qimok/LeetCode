import java.util.Arrays;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/find-the-longest-substring-containing-vowels-in-even-counts/
 * @since 2020-05-20 14:12
 */
public class LeetCode_1371 {

    class Solution {

        /**
         * 前缀和 + 状态压缩
         */
        public int findTheLongestSubstring(String s) {
            // u o i e a
            // 0 0 0 0 0
            // 奇数：1 偶数：0 （奇奇 -> 偶，奇偶 -> 奇）
            // 00000 -> 11111 (0 -> 31)
            // status -> pos
            int[] pos = new int[1 << 5];
            Arrays.fill(pos, -2); // 初始状态，都没遇到过，位置坐标设为 -2
            pos[0] = -1; // 当 status 被还原(值为0)时的位置坐标设为 -1，方便后面 ans 的计算
            int status = 0;
            int ans = 0;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                // update status
                switch (c) {
                    // 异或可以消掉相同的元素
                    case 'a':
                        status ^= 1;
                        break;
                    case 'e':
                        status ^= (1 << 1);
                        break;
                    case 'i':
                        status ^= (1 << 2);
                        break;
                    case 'o':
                        status ^= (1 << 3);
                        break;
                    case 'u':
                        status ^= (1 << 4);
                        break;
                    default: break;
                }
                // 当前字符与前面子串的某个字符相等
                if (pos[status] >= -1) {
                    ans = Math.max(ans, i - pos[status]); // 很巧妙：i - pos[status]，当前位置坐标 减去 status 被还原(值为0)时的位置坐标，值即为当前元音字母出现偶数次的最大长度
                } else { // 当前字符与前面子串的所有字符都不相等
                    pos[status] = i;
                }
            }
            return ans;
        }

    }

}
