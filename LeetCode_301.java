import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/remove-invalid-parentheses/
 * @since 2020-10-14
 */
public class LeetCode_301 {

    class Solution {

        List<String> res = new ArrayList<>();

        /**
         * 算法思路
         * <p>
         *     1、首先计算出输入字符串中，不合法的左右括号数量，记为 l,r
         *         (1)、不合法的左括号指在它后面，没有右括号与之匹配
         *         (2)、不合法的右括号指在它前面，找不到可以匹配的左括号
         *     2、删除最少的括号数就是 l + r
         *     3、回溯
         *         (1)、递归出口：l == 0, r == 0, 并且当前字符串合法
         *         (2)、如果 r > 0，先删除多出来的右括号【为 l 做出真实贡献的左括号位置一定在为 r 做出贡献的右括号后面，所以先删除右括号】
         *         (3)、r == 0 后，如果 l > 0，删除多出来的左括号
         */
        public List<String> removeInvalidParentheses(String s) {
            if (s == null || s.isEmpty()) {
                return Arrays.asList(new String[]{""});
            }
            char[] str = s.toCharArray();
            // 计算出不合法的右括号数量 r 和左括号数量 l
            int[] lr = computeInvalidCount(str);
            backtrace(str, 0, lr[0], lr[1], new boolean[str.length]);
            return res;
        }

        private void backtrace(char[] str, int curr, int l, int r, boolean[] isDelete) {
            if (l == 0 && r == 0) {
                if (isValid(str, isDelete)) {
                    // l == 0 && r == 0 && valid，则加入结果集
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < str.length; i++) {
                        if (isDelete[i]) continue;
                        sb.append(str[i]);
                    }
                    res.add(sb.toString());
                }
            }
            if (r > 0) {
                // 如果 r > 0，先删右括号，先保证前面不会非法，然后再考虑左括号
                for (int i = curr; i <= str.length - l - r; i++) { // 小剪枝【当前位置 i 到 len - 1 还需要删除 l + r 个括号，假设 [i, len - 1] 刚好全是要删除的括号，len-1 - i + 1 = l + r，i = len - l - r，i 最大能取到 len - l - r】
                    if (str[i] != ')') continue;
                    if (i > curr && str[i] == str[i - 1] && isDelete[i - 1] == false) continue; // 如果相同字符连续,只考虑第一个
                    isDelete[i] = true;
                    backtrace(str, i + 1, l, r - 1, isDelete);
                    isDelete[i] = false;
                }
            } else if (l > 0) {
                // r == 0, l > 0
                for (int i = curr; i <= str.length - l; i++) { // 小剪枝
                    if (str[i] != '(') continue;
                    if (i > curr && str[i] == str[i - 1] && isDelete[i - 1] == false) continue; // 如果相同字符连续,只考虑第一个
                    isDelete[i] = true;
                    backtrace(str, i + 1, l - 1, r, isDelete);
                    isDelete[i] = false;
                }
            }
        }

        private boolean isValid(char[] str, boolean[] isDelete) {
            int count = 0;
            for (int i = 0; i < str.length; i++) {
                if (isDelete[i]) continue;
                if (str[i] == '(') {
                    count++;
                } else if (str[i] == ')') {
                    count--;
                }
                if (count < 0) {
                    return false;
                }
            }
            return count == 0;
        }

        private int[] computeInvalidCount(char[] str) {
            // l 表示当前等待被匹配的左括号个数,  r 表示匹配不到左括号的右括号个数
            int l = 0, r = 0;
            for (char c : str) {
                if (c == '(') {
                    l++;
                } else if (c == ')') {
                    if (l > 0) {
                        // match
                        l--;
                    } else {
                        r++;
                    }
                }
            }
            return new int[]{l, r};
        }

    }

}
