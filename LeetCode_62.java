import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/unique-paths/
 * @since 2020-09-17
 */
public class LeetCode_62 {

    class Solution {

        /**
         * base case：
         * <p>
         *     dp[i][0] = 1
         *     dp[0][j] = 1
         * 状态定义：dp[i][j] 到达坐标 i,j 的最多路径
         * 状态转移：dp[i][j] = dp[i - 1][j] + dp[i][j - 1]
         */
        public int uniquePaths1(int m, int n) {
            int[][] dp = new int[m][n];
            for (int i = 0; i < m; i++) {
                dp[i][0] = 1;
            }
            for (int j = 1; j < n; j++) {
                dp[0][j] = 1;
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
            return dp[m - 1][n - 1];
        }

        /**
         * 优化1
         */
        public int uniquePaths2(int m, int n) {
            int[] pre = new int[n]; // 前一列每个位置的路径
            int[] curr = new int[n]; // 同一列上方位置的路径
            Arrays.fill(pre, 1);
            Arrays.fill(curr, 1);
            for (int i = 1; i < m; i++) { // 列
                for (int j = 1; j < n; j++) { // 行
                    curr[j] = curr[j - 1] + pre[j];
                }
                pre = curr.clone();
            }
            return pre[n - 1];
        }

        /**
         * 优化2：观察优化1可得，无需区分行与列，就近算出的两个位置的值，即为当前位置的值
         */
        public int uniquePaths3(int m, int n) {
            int[] curr = new int[n];
            Arrays.fill(curr, 1);
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    curr[j] += curr[j - 1];
                }
            }
            return curr[n - 1];
        }

    }

}
