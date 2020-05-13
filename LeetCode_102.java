import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal/submissions/
 * @since 2020-05-13 13:45
 */
public class LeetCode_102 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {

        /**
         * dfs
         */
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            inorderTraverse(res, 0, root);
            return res;
        }

        private void inorderTraverse(List<List<Integer>> res, int depth, TreeNode curr) {
            if (curr != null) { // 递归终止条件
                if (res.size() == depth) {
                    // 刚到一层时需要给 res 中增加 List<Integer>
                    res.add(new ArrayList<>());
                }
                res.get(depth).add(curr.val); // 处理当前层逻辑
                inorderTraverse(res, depth + 1, curr.left); // 下探
                inorderTraverse(res, depth + 1, curr.right); // 下探
            }
        }

        /**
         * bfs
         * <p>
         *     创建队列辅助搜索，依次遍历每一层节点并将下一层节点入队。
         *     每遍历完一层进行一次数据统计，直到遍历完成返回最终统计数据。
         */
        public  List<List<Integer>> levelOrder2(TreeNode root) {
            List<List<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root); // 将二叉树头节点入队
            while (!queue.isEmpty()) {
                List<Integer> level = new ArrayList<>();
                // 得到每一层的元素个数
                int size = queue.size();
                while (size > 0) {
                    TreeNode curr = queue.poll();
                    size--; // 出队一个元素，size--
                    level.add(curr.val);
                    // 依次将下一层的节点入队
                    if (curr.left != null) {
                        queue.offer(curr.left);
                    }
                    if (curr.right != null) {
                        queue.offer(curr.right);
                    }
                }
                res.add(level);
            }
            return res;
        }

    }

}
