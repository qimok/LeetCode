import java.util.Stack;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/daily-temperatures/
 * @since 2020-06-11 13:24
 */
public class LeetCode_739 {

    class Solution {

        /**
         * 暴力求解
         */
        public int[] dailyTemperatures1(int[] T) {
            int len = T.length;
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                int curr = T[i];
                for (int j = i + 1; j < len; j++) {
                    if (T[j] > curr) {
                        res[i] = j - i;
                        break;
                    }
                }
            }
            return res;
        }

        /**
         * 单调栈：递减栈
         */
        public int[] dailyTemperatures2(int[] T) {
            Stack<Integer> stack = new Stack<>();
            int len = T.length;
            int[] res = new int[len];
            for (int i = 0; i < len; i++) {
                while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                    // 栈非空 且 当前元素对应的值大于栈顶元素对应的值，则出栈
                    int pre = stack.pop();
                    res[pre] = i - pre;
                }
                stack.add(i);
            }
            return res;
        }

    }

}
