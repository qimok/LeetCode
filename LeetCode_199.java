import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/binary-tree-right-side-view/
 * @since 2020-04-22 23:06
 */
public class LeetCode_199 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {

        /**
         * BFS
         * <p>
         *     时间复杂度：O(n)
         *     空间复杂度：O(n)
         */
        public List<Integer> rightSideView1(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                // 遍历当前层的所有节点
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    // 将当前层的左右节点入队
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                    // 将当前层的最后一个节点放入结果列表
                    if (i == size - 1) {
                        res.add(node.val);
                    }
                }
            }
            return res;
        }

        /**
         * DFS
         * <p>
         *     访问节点的顺序：跟节点->右子树->左子树
         *     可保证每层最先访问的都是右子树
         */
        public List<Integer> rightSideView2(TreeNode root) {
            List<Integer> res = new LinkedList<>();
            dfs(root, 0, res);
            return res;
        }

        private void dfs(TreeNode node, int depth, List<Integer> res) {
            if (node == null) { // 递归终止条件
                return;
            }
            // 处理当前层逻辑（如果当前节点所在的深度还没出现在res里，
            // 说明在该深度下当前节点是第一个被访问到的节点，
            // 因为将当前节点放入到res里）
            if (depth == res.size()) {
                res.add(node.val);
            }
            depth++;
            dfs(node.right, depth, res); // 下探，保证先遍历右子树
            dfs(node.left, depth, res); // 下探
        }

    }

}
