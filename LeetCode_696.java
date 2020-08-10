import java.util.ArrayList;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/count-binary-substrings/
 * @since 2020-08-10
 */
public class LeetCode_696 {

    class Solution {

        /**
         * 可以将字符串 s 按照 0 和 1 的连续段分组，存在 counts 数组中，例如 s = 00111011，可以得到这样的 counts 数组：counts = {2, 3, 1, 2}
         *
         * 这里 counts 数组中两个相邻的数一定代表的是两种不同的字符。假设 counts 数组中两个相邻的数字为 u 或者 v，它们对应着 u 个 0 和 v 个 1，或者 u 个 1 和 v 个 0
         * 它们能组成的满足条件的子串数目为 min{u, v}，即一对相邻的数字对答案的贡献
         *
         * 只需要遍历所有相邻的数对，求它们的贡献总和，即可得到答案
         */
        public int countBinarySubstrings1(String s) {
            List<Integer> counts = new ArrayList<>();
            int ptr = 0, len = s.length();
            while (ptr < len) {
                char c = s.charAt(ptr);
                int count = 0;
                while (ptr < len && s.charAt(ptr) == c) {
                    ++ptr;
                    ++count;
                }
                counts.add(count);
            }
            int ans = 0;
            for (int i = 1; i < counts.size(); i++) {
                ans += Math.min(counts.get(i), counts.get(i - 1));
            }
            return ans;
        }

        /**
         * 对于某一个位置 i，其实我们只关心 i - 1 位置的 counts 值是多少，所以可以用一个 last 变量来维护当前位置的前一个位置，这样可以省去一个 counts 数组的空间
         */
        public int countBinarySubstrings2(String s) {
            int ptr = 0, len = s.length(), last = 0, ans = 0;
            while (ptr < len) {
                char c = s.charAt(ptr);
                int count = 0;
                while (ptr < len && s.charAt(ptr) == c) {
                    ++ptr;
                    ++count;
                }
                ans += Math.min(count, last);
                last = count;
            }
            return ans;
        }

    }

}
