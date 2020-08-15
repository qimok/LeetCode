/**
 * @author Xinshuai
 * @description 题目链接：https://leetcode-cn.com/problems/remove-boxes/
 * @since 2020-08-15
 */
public class LeetCode_546 {

    class Solution {

        public int removeBoxes(int[] boxes) {
            int[][][] dp = new int[100][100][100];
            return calculatePoints(boxes, dp, 0, boxes.length - 1, 0);
        }

        private int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
            if (l > r) {
                return 0;
            }
            if (dp[l][r][k] != 0) {
                return dp[l][r][k];
            }
            while (r > l && boxes[r] == boxes[r - 1]) {
                r--;
                k++;
            }
            // 策略1：r 右侧相同数字消掉，再计算与左边之和，即不考虑左边与右边相同的数，抽掉中间其它数来计算的情况
            dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
            for (int i = l; i < r; i++) {
                if (boxes[i] == boxes[r]) {
                    // 策略2：考虑到左边子串有与右边相同的数，通过遍历左边子串查找[i] = [r]，
                    // 找到之后，将子串分成两块计算，calculatePoints(boxes, dp, l, i, k + 1) 代表找到的 i 左子串的计算
                    // 计算时，加上 r 右侧相同的数的个数，即 k + 1
                    // calculatePoints(boxes, dp, i + 1, r - 1, 0)表示 i 右边子串的计算
                    // 遍历的同时更新最大值
                    dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
                }
            }
            return dp[l][r][k];
        }

    }

}
