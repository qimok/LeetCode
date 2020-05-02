import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * @since 2020-05-02 22:24
 */
public class LeetCode_3 {

    class Solution {

        /**
         * 双重 for 循环 + 最左下标
         */
        public int lengthOfLongestSubstring1(String s) {
            // 定义 ans 记录无重复字符子串的最大长度
            // 定义 left 记录无重复字符子串的最左下标
            int ans = 0, left = 0;
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                for (int j = left; j < i; j++) {
                    if (chars[i] == chars[j]) {
                        // 如果相等，则重新计算 ans，并将无重复字符子串的最左下边右移一位，同时，终止本次内层循环
                        ans = Math.max(ans, i - left);
                        left = j + 1;
                        break;
                    }
                }
            }
            return Math.max(ans, chars.length - left);
        }

        /**
         * 滑动窗口：双指针 + Set
         */
        public int lengthOfLongestSubstring2(String s) {
            int len = s.length();
            char[] chars = s.toCharArray();
            Set<Character> set = new HashSet<>();
            // 定义 ans 记录无重复字符子串的最大长度，定义双指针截取子串
            int left = 0, right = 0, ans = 0;
            while (left < len && right < len) {
                if (!set.contains(right)) {
                    // 没有出现重复则将新字符加入哈希表并且记录最大长度继续遍历
                    set.add(chars[right++]);
                    ans = Math.max(ans, right - left);
                } else {
                    // 出现重复则循环移除从左指针开始到与右指针重复的字符为止的所有字符
                    set.remove(chars[left++]);
                }
            }
            return ans;
        }

        /**
         * 滑动窗口：双指针 + Map
         */
        public int lengthOfLongestSubstring3(String s) {
            int len = s.length();
            char[] chars = s.toCharArray();
            // 定义 ans 记录无重复字符子串的最大长度，定义双指针截取子串
            int left = 0, right = 0, ans = 0;
            Map<Character, Integer> map = new HashMap<>();
            // 滑动窗口遍历无重复字符的子串
            while (left < len && right < len) {
                if (map.containsKey(chars[right])) {
                    // 根据重复字符是否在当前遍历子串中来判断是否需要更新左指针
                    left = Math.max(left, map.get(chars[right]) + 1);
                }
                // 每次更新遍历到的字符与当前索引的对应关系存入 HashMap
                map.put(chars[right], right);
                // 每次遍历记录到当前位置无重复字符子串的最大长度
                ans = Math.max(ans, right - left + 1);
                right++;
            }
            return ans;
        }

    }
}
