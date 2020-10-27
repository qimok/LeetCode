import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/triangle/
 * @since 2020-07-14 22:00
 */
public class LeetCode_120 {

    class Solution {

        /**
         * 递归：超时
         * <p>
         *     相邻结点：与(i, j) 点相邻的结点为 (i + 1, j) 和 (i + 1, j + 1)
         *     i 控制行，j 控制列
         */
        public int minimumTotal1(List<List<Integer>> triangle) {
            return dfs1(triangle, 0, 0);
        }

        private int dfs1(List<List<Integer>> triangle, int i, int j) {
            if (triangle.size() == i) {
                return 0;
            }
            return Math.min(dfs1(triangle, i + 1, j + 1), dfs1(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
        }

        /**
         * 递归 + 记忆化
         */
        Integer[][] memo;
        public int minimumTotal2(List<List<Integer>> triangle) {
            memo = new Integer[triangle.size()][triangle.size()];
            return dfs2(triangle, 0, 0);
        }

        private int dfs2(List<List<Integer>> triangle, int i, int j) {
            if (triangle.size() == i) {
                return 0;
            }
            if (memo[i][j] != null) {
                return memo[i][j];
            }
            return memo[i][j] = Math.min(dfs2(triangle, i + 1, j), dfs2(triangle, i + 1, j + 1))
                    + triangle.get(i).get(j);
        }

        /**
         * dp
         * <p>
         *     1、状态定义：
         *     dp[i][j] 表示从点 (i, j) 到底边的最小路径和
         *     2、状态转移：
         *     dp[i][j] = min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j]
         */
        public int minimumTotal3(List<List<Integer>> triangle) {
            int len = triangle.size();
            // dp[i][j] 表示从点 (i, j) 到底边的最小路径和。
            int[][] dp = new int[len + 1][len + 1];
            // 从三角形的最后一行开始递推
            for (int i = len - 1; i >= 0; i--) {
                for (int j = 0; j <= i; j++) {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
                }
            }
            return dp[0][0];
        }

    }

}
