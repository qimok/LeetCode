import java.util.ArrayList;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/
 * @since 2020-11-27
 */
public class LeetCode_438 {

    class Solution {

        public List<Integer> findAnagrams(String s, String p) {
            List<Integer> res = new ArrayList<>();
            if (s.length() == 0 || s.length() < p.length()) {
                return res;
            }
            // 定义一个数组，用来记录字符串 s 中出现字符的频率，也就是窗口内需要匹配的字符和相应的频率
            int[] need = new int[128];
            for (char c : p.toCharArray()) {
                need[c]++;
            }
            int left = 0, right = 0;
            // 匹配字符的个数
            int matcher = 0;
            while (right < s.length()) {
                // 右边界的那个字符
                char c = s.charAt(right);
                // 可以理解为需要匹配的字符 c 减少了一个
                need[c]--;
                // 若字符 c 在 s 中存在，那么经过上一步操作，只要个数还大于等于 0，说明匹配了一个
                // 若字符 c 在 s 中不存在，那么经过上一步操作，若个数小于 0，不进行任何操作
                if (need[c] >= 0) {
                    matcher++;
                }
                // 右边界右移，这样下面就变成了左开右闭区间，方便计算窗口大小：right - left
                right++;
                // 只要窗口内匹配的字符达到了要求，右边界固定，左边界收缩
                while (right - left == p.length()) {
                    if (matcher == p.length()) {
                        res.add(left);
                    }
                    // 左边界的那个字符
                    char d = s.charAt(left);
                    // 左边界的字符要移出窗口
                    need[d]++;
                    // 不在 s 中出现的字符，移出窗口，最终能够达到的最大值 need[d] = 0（因为前面有 need[c]--，上一步有 need[d]++，这里的 c 和 d 是同一个字符）
                    // 如果恰好移出了需要匹配的一个字符，那么这里 nee[d] > 0, 也就是还要匹配字符 d，此时 match--
                    if (need[d] > 0) {
                        matcher--;
                    }
                    // 左边界收缩
                    left++;
                }
            }
            return res;
        }

    }

}
