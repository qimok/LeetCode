import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/decode-string/
 * @since 2020-05-28 18:15
 */
public class LeetCode_394 {

    class Solution {

        /**
         * 双栈
         * <p>
         *     s = "3[a2[c]]"
         *     ------第 1 步(遇到3)------
         *     tail：null
         *     numStack：3
         *     strStack：
         *     ------第 2 步(遇到[)------
         *     tail：null
         *     numStack：3
         *     strStack：null
         *     ------第 3 步(遇到a)------
         *     tail：null a
         *     numStack：3
         *     strStack：null
         *     ------第 4 步(遇到2)------
         *     tail：null a
         *     numStack：3 2
         *     strStack：null
         *     ------第 5 步(遇到[)------
         *     tail：null a null
         *     numStack：3 2
         *     strStack：null a
         *     ------第 6 步(遇到c)------
         *     tail：null a null c
         *     numStack：3 2
         *     strStack：null a
         *     ------第 7 步(遇到])------
         *     tail：null a null c
         *     numStack：3  出栈>> repeatedTimes：2
         *     strStack：null 出栈>> temp：a
         *     结果>> a + 2 * c
         *           tail = acc
         *     ------第 8 步(遇到])------
         *     tail：acc
         *     numStack： 出栈 >> repeatedTimes：3
         *     strStack： 出栈 >> temp：null
         *     结果>> null + 3 * acc >> accaccacc
         */
        public String decodeString(String s) {
            // 出现次数栈
            Deque<Integer> numStack = new ArrayDeque<>();
            // tail 栈(tail：指 当前遍历到的字符 或 内部字符拼接后的字符串)
            Deque<String> strStack = new ArrayDeque<>();
            int len = s.length();
            StringBuilder tail = new StringBuilder();
            for (int i = 0; i < len; i++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)) {
                    int num = c - '0';
                    // 考虑大于 9 的数字
                    while (i + 1 < len && Character.isDigit(s.charAt(i + 1))) {
                        num = num * 10 + s.charAt(i + 1) - '0';
                        i++;
                    }
                    numStack.push(num);
                } else if (c == '[') {
                    // 遇到 '['，tail 就入栈，并且复位
                    strStack.push(tail.toString());
                    tail = new StringBuilder();
                } else if (c == ']') {
                    StringBuilder temp = new StringBuilder(strStack.pop());
                    int repeatedTimes = numStack.pop();
                    // 遇到 ']'，strStack 出栈(出来的是上次的tail)，然后拼接当前的 repeatedTimes * tail
                    for (int j = 0; j < repeatedTimes; j++) {
                        temp.append(tail);
                    }
                    tail = temp;
                } else {
                    // 遇到字符，就正常在当前的 tail 后面拼接即可
                    tail.append(c);
                }
            }
            return tail.toString();
        }

    }

}
