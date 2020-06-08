/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/satisfiability-of-equality-equations/
 * @since 2020-06-08 13:10
 */
public class LeetCode_990 {

    class Solution {

        /**
         * 并查集
         */
        public boolean equationsPossible(String[] equations) {
             UnionFind uf = new UnionFind(26);
             for (String equation : equations) {
                 char[] chars = equation.toCharArray();
                 if (chars[1] == '=') {
                     int index1 = chars[0] - 'a';
                     int index2 = chars[3] - 'a';
                     uf.union(index1, index2);
                 }
             }
             for (String equation : equations) {
                 char[] chars = equation.toCharArray();
                 if (chars[1] == '!') {
                     int index1 = chars[0] - 'a';
                     int index2 = chars[3] - 'a';
                     if (uf.isConnected(index1, index2)) {
                         // 如果合并失败，表示等式有矛盾，根据题意，返回 false
                         return false;
                     }
                 }
             }
             // 如果检查了所有不等式，都没有发现矛盾，返回 true
             return true;
        }

        private class UnionFind {

            private int[] parent;

            public UnionFind(int n) {
                parent = new int[n];
                for (int i = 0; i < n; i++) {
                    parent[i] = i;
                }
            }

            public int find(int x) {
                // 隔代压缩
                while (x != parent[x]) {
                    parent[x] = parent[parent[x]];
                    x = parent[x];
                }
                return x;
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                parent[rootX] = rootY;
            }

            public boolean isConnected(int x, int y) {
                return find(x) == find(y);
            }

        }

    }

}
