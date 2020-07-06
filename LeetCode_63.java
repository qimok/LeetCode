/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/unique-paths-ii/
 * @since 2020-07-06 16:25
 */
public class LeetCode_63 {

    class Solution {

        /**
         * 如果网格 (i, j) 上有障碍物，则 dp[i][j] 值为 0，表示走到该格子的方法数为 0
         * 否则网格 (i, j) 可以从网格 (i - 1, j) 或者 网格 (i, j - 1) 走过来
         * 因此走到该格子的方法数为走到网格 (i - 1, j) 和网格 (i, j - 1) 的方法数之和，即 dp[i, j] = dp[i - 1, j] + dp[i, j - 1]
         *
         * 初始条件：
         *     第 1 列的格子只有从其上边格子走过去这一种走法，因此初始化 dp[i][0] 值为 1，存在障碍物时为 0
         *     第 1 行的格子只有从其左边格子走过去这一种走法，因此初始化 dp[0][j] 值为 1，存在障碍物时为 0
         */
        public int uniquePathsWithObstacles(int[][] obstacleGrid) {
            if (obstacleGrid == null || obstacleGrid.length == 0) {
                return 0;
            }
            int m = obstacleGrid.length, n = obstacleGrid[0].length;
            int[][] dp = new int[m][n];
            for (int i = 0; i < m && obstacleGrid[i][0] == 0; i++) {
                dp[i][0] = 1;
            }
            for (int i = 0; i < n && obstacleGrid[0][i] == 0; i++) {
                dp[0][i] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    if (obstacleGrid[i][j] == 0) {
                        dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                    }
                }
            }
            return dp[m - 1][n - 1];
        }

    }

}
