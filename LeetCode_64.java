/**
 * @author qimok
 * @description https://leetcode-cn.com/problems/minimum-path-sum/
 * @since 2020-07-23
 */
public class LeetCode_64 {

    class Solution {

        /**
         *  只能向右或向下走，换句话说，当前单元格 (i, j) 只能从左方单元格 (i-1, j) 或上方单元格 (i,j-1) 走到，因此只需要考虑矩阵左边界和上边界
         *
         *  初始状态：dp 初始化即可，不需要修改初始 0 值。
         *
         *  返回值：返回 dp 矩阵右下角值，即走到终点的最小路径和。
         *  其实完全不需要建立 dp 矩阵浪费额外空间，直接遍历 grid[i][j] 修改即可。这是因为：grid[i][j] = min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j]
         *  原 grid 矩阵元素中被覆盖为 dp 元素后（都处于当前遍历点的左上方），不会再被使用到
         */
        public int minPathSum(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (i == 0 && j == 0) {
                        continue;
                    } else if (i == 0) {
                        grid[i][j] = grid[i][j - 1] + grid[i][j];
                    } else if (j == 0) {
                        grid[i][j] = grid[i - 1][j] + grid[i][j];
                    } else {
                        grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
                    }
                }
            }
            return grid[grid.length - 1][grid[0].length - 1];
        }

    }

}
