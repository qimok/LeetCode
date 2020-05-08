/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/maximal-square/
 * @since 2020-05-08 13:07
 */
public class LeetCode_221 {

    class Solution {

        /**
         * 二维动态规划
         * <p>
         *     dp[i][j] 是以 matrix[i][j] 为右下角的正方形的最大边长
         */
        public int maximalSquare1(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int maxSide = 0;
            // 相当于已经预处理新增第一行、第一列均为0
            int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
            for (int row = 0; row < matrix.length; row++) {
                for (int col = 0; col < matrix[0].length; col++) {
                    if (matrix[row][col] == '1') {
                        dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                        maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                    }
                }
            }
            return maxSide * maxSide;
        }

        /**
         * 一维动态规划
         * <p>
         *     dp[i] 是以当前行的 char[i] 为右下角的正方形的最大边长
         */
        public int maximalSquare2(char[][] matrix) {
            if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int maxSide = 0;
            int[] dp = new int[matrix[0].length + 1];
            int northWest; // 西北角/左上角
            for (char[] c : matrix) {
                northWest = 0;
                for (int col = 0; col < matrix[0].length; col++) {
                    int nextNorthWest = dp[col + 1];
                    if (c[col] == '1') {
                        dp[col + 1] = Math.min(Math.min(dp[col], dp[col + 1]), northWest) + 1;
                        maxSide = Math.max(maxSide, dp[col + 1]);
                    } else {
                        dp[col + 1] = 0;
                    }
                    northWest = nextNorthWest;
                }
            }
            return maxSide * maxSide;
        }

    }

}
