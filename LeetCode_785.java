import java.util.LinkedList;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/is-graph-bipartite/
 * @since 2020-07-16 22:29
 */
public class LeetCode_785 {

    class Solution {

        /**
         * bfs
         */
        public boolean isBipartite1(int[][] graph) {
            // 定义 visited 数组，初始值为 0 表示未被访问，赋值为 1 或者 -1 表示两种不同的颜色
            int[] visited = new int[graph.length];
            Queue<Integer> queue = new LinkedList<>();
            // 因为图中可能含有多个连通域，所以我们需要判断是否存在顶点未被访问，若存在则从它开始再进行一轮 bfs 染色
            for (int i = 0; i < graph.length; i++) {
                if (visited[i] != 0) {
                    continue;
                }
                // 每出队一个顶点，将其所有邻接点染成相反的颜色并入队
                queue.offer(i);
                visited[i] = 1;
                while (!queue.isEmpty()) {
                    int v = queue.poll();
                    for (int w : graph[v]) {
                        // 如果当前顶点的某个邻接点已经被染过色了，且颜色和当前顶点相同，说明此无向图无法被正确染色，返回 false
                        if (visited[v] == visited[w]) {
                            return false;
                        }
                        if (visited[w] == 0) {
                            visited[w] = -visited[v];
                            queue.offer(w);
                        }
                    }
                }
            }
            return true;
        }

        /**
         * dfs
         */
        public boolean isBipartite2(int[][] graph) {
            // 定义 visited 数组，初始值为 0 表示未被访问，赋值为 1 或者 -1 表示两种不同的颜
            int[] visited = new int[graph.length];
            // 因为图中可能含有多个连通域，所以我们需要判断是否存在顶点未被访问，若存在则从它开始再进行一轮 dfs 染色
            for (int i = 0; i < graph.length; i++) {
                if (visited[i] == 0 && !dfs(graph, i, 1, visited)) {
                    return false;
                }
            }
            return true;
        }

        private boolean dfs(int[][] graph, int v, int color, int[] visited) {
            // 如果要对某顶点染色时，发现它已经被染色了，则判断它的颜色是否与本次要染的颜色相同，如果矛盾，说明此无向图无法被正确染色，返回 false
            if (visited[v] != 0) {
                return visited[v] == color;
            }
            // 对当前顶点进行染色，并将当前顶点的所有邻接点染成相反的颜色
            visited[v] = color;
            for (int w : graph[v]) {
                if (!dfs(graph, w, -color, visited)) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 并查集
         */
        public boolean isBipartite3(int[][] graph) {
            // 初始化并查集
            UnionFind uf = new UnionFind(graph.length);
            // 遍历每个顶点，将当前顶点的所有邻接点进行合并
            for (int i = 0; i < graph.length; i++) {
                int[] adjs = graph[i];
                for (int w : adjs) {
                    // 若某个邻接点与当前顶点已经在一个集合中了，说明不是二分图，返回 false
                    if (uf.isConnected(i, w)) {
                        return false;
                    }
                    uf.union(adjs[0], w);
                }
            }
            return true;
        }

        // 并查集
        class UnionFind {

            int[] roots;

            public UnionFind(int n) {
                roots = new int[n];
                for (int i = 0; i < n; i++) {
                    roots[i] = i;
                }
            }

            public int find(int i) {
                if (roots[i] == i) {
                    return i;
                }
                return roots[i] = find(roots[i]);
            }

            // 判断 p 和 q 是否在同一个集合中
            public boolean isConnected(int p, int q) {
                return find(q) == find(p);
            }

            // 合并 p 和 q 到一个集合中
            public void union(int p, int q) {
                roots[find(p)] = find(q);
            }

        }

    }

}
