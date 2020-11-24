/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/permutation-in-string/
 * @since 2020-11-24
 */
public class LeetCode_567 {

    class Solution {

        public boolean checkInclusion(String s1, String s2) {
            if (s1.length() > s2.length()) {
                return false;
            }
            // 定义一个数组，用来记录字符串 s1 中出现字符的频率，也就是窗口内需要匹配的字符和相应的频率
            int[] need = new int[128];
            for (char c : s1.toCharArray()) {
                need[c]++;
            }
            int left = 0, right = 0;
            int matcher = 0;
            while (right < s2.length()) {
                // 右边界的那个字符
                char c = s2.charAt(right);
                // 可以理解为需要匹配的字符 c 减少了一个
                need[c]--;
                if (need[c] >= 0) {
                    // 若字符 c 在 s1 中存在，那么经过上一步操作，只要个数还大于等于 0，说明匹配了一个
                    // 若字符 c 在 s1 中不存在，那么经过上一步操作，若个数小于 0，不进行任何操作
                    matcher++;
                }
                // 右边界右移
                right++;
                while (right - left >= s1.length()) {
                    // 移动 left 缩小窗口的时机是窗口大小等于 s1.length() 时
                    if (matcher == s1.length()) {
                        // 当发现 matcher == s1.length() 时，说明窗口中是一个合法的排列，所以立即返回 true
                        return true;
                    }
                    char d = s2.charAt(left);
                    // 左边界的字符要移出窗口
                    need[d]++;
                    if (need[d] > 0) {
                        // 移出后，matcher 的个数就减少 1
                        matcher--;
                    }
                    // 左边界收缩
                    left++;
                }
            }
            // 未找到符合条件的子串
            return false;
        }

    }

}
