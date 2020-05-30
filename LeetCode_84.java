import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram/
 * @since 2020-05-30 12:40
 */
public class LeetCode_84 {

    class Solution {

        /**
         * 暴力解法
         * <p>
         *     时间复杂度：O(n^2)
         *     空间复杂度：O(1)
         *
         *     固定高，求最长底边
         *     >> 从 i 向两边遍历，找到左边和右边第 1 个严格小于 height[i] 的时候停下，中间的长度就是最长底边
         */
        public int largestRectangleArea1(int[] heights) {
            int len = heights.length;
            int maxArea = 0;
            for (int mid = 0; mid < len; mid++) {
                // 枚举高
                int height = heights[mid];
                int left = mid, right = mid;
                // 确定左边界
                while (left - 1 >= 0 && heights[left - 1] >= height) {
                    --left;
                }
                // 确定右边界
                while (right + 1 < len && heights[right + 1] >= height) {
                    ++right;
                }
                // 计算最大面积
                maxArea = Math.max(maxArea, (right - left + 1) * height);
            }
            return maxArea;
        }

        /**
         * 单调递增栈
         * <p>
         *     时间复杂度：O(n^2)
         *     空间复杂度：O(n)
         */
        public int largestRectangleArea2(int[] heights) {
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            int maxArea = 0;
            for (int i = 0; i < heights.length; i++) {
                while (stack.peek() != -1 && heights[stack.peek()] > heights[i]) {
                    // i - stack.peek() - 1：当前节点的前一个节点下标到栈顶元素下标的距离
                    maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
                }
                stack.push(i);
            }
            while (stack.peek() != -1) {
                // heights.length - stack.peek() - 1：当前节点的前一个节点下标到栈顶元素下标的距离
                maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() - 1));
            }
            return maxArea;
        }

        /**
         * 单调递增栈 + 哨兵模式
         * <p>
         *     时间复杂度：O(n)
         *     空间复杂度：O(n)
         *
         *     使用哨兵的好处：避免特殊情况的讨论，简化代码逻辑
         */
        public int largestRectangleArea3(int[] heights) {
            int len = heights.length;
            int maxArea = 0;
            // len + 2：newHeights 的头元素和尾元素都是哨兵
            // 头哨兵：高度为 0，保证栈中一定非空
            // 尾哨兵：高度为 0，保证遍历完成以后所有原始输入数据可以完成计算，而不必重复写相关逻辑
            int[] newHeights = new int[len + 2];
            for (int i = 0; i < len; i++) {
                newHeights[i + 1] = heights[i];
            }
            // 以下步骤，基本同第 2 种解法
            Deque<Integer> stack = new ArrayDeque<>();
            stack.addLast(0);
            for (int i = 1; i < newHeights.length; i++) {
                while (newHeights[stack.peekLast()] > newHeights[i]) {
                    int height = newHeights[stack.removeLast()];
                    int width = i - stack.peekLast() - 1;
                    maxArea = Math.max(maxArea, width * height);
                }
                stack.addLast(i);
            }
            return maxArea;
        }

    }

}
