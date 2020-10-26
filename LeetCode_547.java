import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/friend-circles/
 * @since 2020-10-25
 */
public class LeetCode_547 {

    class Solution {

        /**
         * dfs
         */
        public int findCircleNum1(int[][] M) {
            // 是否访问过
            int[] visited = new int[M.length];
            int count = 0;
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    // 遍历没有被访问过的横坐标，继续进行 dfs 扫描
                    dfs(M, visited, i);
                    count++;
                }
            }
            return count;
        }

        private void dfs(int[][] M, int[] visited, int i) {
            visited[i] = 1;
            for (int j = 0; j < M.length; j++) {
                if (M[i][j] == 1 && visited[j] == 0) {
                    // 假如 i、j 是朋友且以 j 为横坐标的边的节点没有被访问过，直接进入 dfs
                    dfs(M, visited, j);
                }
                // ...此时已经把能扩散的在同一个朋友圈的元素遍历完了
            }
        }

        /**
         * bfs
         */
        public int findCircleNum2(int[][] M) {
            int[] visited = new int[M.length];
            int count = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < M.length; i++) {
                if (visited[i] == 0) {
                    queue.add(i);
                    while (!queue.isEmpty()) {
                        Integer x = queue.remove();
                        visited[x] = 1;
                        for (int j = 0; j < M.length; j++) {
                            if (M[x][j] == 1 && visited[j] == 0) {
                                queue.add(j);
                            }
                        }
                    }
                    count++;
                }
            }
            return count;
        }

        /**
         * 并查集
         */
        public int findCircleNum3(int[][] M) {
            int n = M.length;
            UF uf = new UF(n);
            for (int i = 0; i < n; i++) {
                // 优化点：由于连接矩阵的朋友关系是沿左斜线对称的，故 j 只需要计算小于 i 的情况即可
                // i != j 的原因：因为自己跟自己肯定是朋友，即肯定连通，故不需要重复计算
                for (int j = 0; j < i; j++) {
                    if (M[i][j] == 1) {
                        uf.union(i, j);
                    }
                }
            }
            return uf.count();
        }

    }

}
