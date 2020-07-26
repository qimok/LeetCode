/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 * @since 2020-07-26
 */
public class LeetCode_329 {

    class Solution {

        // 在遍历过程中记录每个点递增能到达的最长路径的最大值，可以省去最后对 max 数组遍历取最大
        private int longest;

        // 方向数组，路径问题必备
        private final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

        /**
         * 深度优先 + 记忆化
         */
        public int longestIncreasingPath1(int[][] matrix) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return 0;
            }
            int m = matrix.length, n = matrix[0].length;
            // 用二维数组也可以，没太大区别，占用空间都是 m * n
            int[] max = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (max[i * n + j] == 0) {
                        longestIncreasingPath(matrix, max, i, j, m, n);
                    }
                    // 对于已经访问并记录了最大递增路径的节点，直接跳过
                }
            }
            return longest;
        }

        /**
         * 找到（x, y）节点能访问到的最大路径，期间记录到 max 中
         */
        private int longestIncreasingPath(int[][] matrix, int[] max, int x, int y, int m, int n) {
            int index = x * n + y;
            if (max[index] != 0) {
                return max[index];
            }
            max[index] = 1; // 未记录过的位置，能访问到的路径至少为 1
            for (int[] dir : directions) {
                int nextX = x + dir[0];
                int nextY = y + dir[1];
                // 关键：只找递增的路径，进行下探（因为只找的递增，所以保证了不会往回跑）
                if (nextX >= 0 && nextY >= 0 && nextX < m && nextY < n && matrix[x][y] < matrix[nextX][nextY]) {
                    max[index] = Math.max(max[index], longestIncreasingPath(matrix, max, nextX, nextY, m, n) + 1);
                }
            }
            longest = Math.max(longest, max[index]);
            return max[index];
        }

        /**
         * 深度优先 + 记忆化
         */
        public int longestIncreasingPath2(int[][] matrix) {
            if (matrix.length == 0) {
                return 0;
            }
            // visited有两个作用：1.判断是否访问过，2.存储当前格子的最长递增长度
            int[][] visited = new int[matrix.length][matrix[0].length];
            int max = 0;
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (visited[i][j] == 0) {
                        // 这里先做一次比较找出 max，可以避免最后再去遍历一个 visited 数组
                        max = Math.max(max, dfs(i, j, matrix, visited));
                    }
                }
            }
            return max;
        }

        public int dfs(int i, int j, int[][] matrix, int[][] visited) {
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length) {
                return 0;
            }
            if (visited[i][j] > 0) {
                return visited[i][j];
            }
            int max = 0;
            // 分别去判断四周是否比当前数小，然后去递归遍历
            if (i - 1 >= 0 && matrix[i - 1][j] < matrix[i][j]) {
                max = Math.max(max, dfs(i - 1, j, matrix, visited));
            }
            if (i + 1 < matrix.length && matrix[i + 1][j] < matrix[i][j]) {
                max = Math.max(max, dfs(i+1, j, matrix, visited));
            }
            if(j - 1 >= 0 && matrix[i][j-1] < matrix[i][j]){
                max = Math.max(max, dfs(i, j-1, matrix, visited));
            }
            if(j + 1 < matrix[0].length && matrix[i][j+1] < matrix[i][j]){
                max = Math.max(max, dfs(i, j+1, matrix, visited));
            }
            visited[i][j] = max + 1;
            return max + 1;
        }

    }

}
