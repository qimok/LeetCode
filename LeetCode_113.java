import java.util.*;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/path-sum-ii/
 * @since 2020-07-08 10:03
 */
public class LeetCode_113 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {

        List<List<Integer>> res = new LinkedList<>();
        Deque<Integer> path = new LinkedList<>();

        /**
         * dfs （递归）
         */
        public List<List<Integer>> pathSum1(TreeNode root, int sum) {
            dfs(root, sum);
            return res;
        }

        private void dfs(TreeNode root, int sum) {
            if (root == null) {
                // 递归终止条件
                return;
            }
            // 处理当前层逻辑
            path.offerLast(root.val);
            sum -= root.val;
            if (root.left == null && root.right == null && sum == 0) {
                res.add(new LinkedList<>(path));
            }
            // 下探
            dfs(root.left, sum);
            dfs(root.right, sum);
            // 回溯
            path.pollLast();
        }

        List<List<Integer>> ret = new LinkedList<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();

        /**
         * bfs
         */
        public List<List<Integer>> pathSum2(TreeNode root, int sum) {
            if (root == null) {
                return ret;
            }
            Queue<TreeNode> queueNode = new LinkedList<>();
            Queue<Integer> queueSum = new LinkedList<>();
            queueNode.offer(root);
            queueSum.offer(0);
            while (!queueNode.isEmpty()) {
                TreeNode node = queueNode.poll();
                int rec = queueSum.poll() + node.val;
                if (node.left == null && node.right == null) {
                    if (rec == sum) {
                        getPath(node);
                    }
                } else {
                    if (node.left != null) {
                        map.put(node.left, node);
                        queueNode.offer(node.left);
                        queueSum.offer(rec);
                    }
                    if (node.right != null) {
                        map.put(node.right, node);
                        queueNode.offer(node.right);
                        queueSum.offer(rec);
                    }
                }
            }
            return ret;
        }

        private void getPath(TreeNode node) {
            List<Integer> temp = new LinkedList<>();
            while (node != null) {
                temp.add(node.val);
                node = map.get(node);
            }
            Collections.reverse(temp);
            ret.add(temp);
        }

    }

}
