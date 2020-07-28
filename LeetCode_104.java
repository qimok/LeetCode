import java.util.LinkedList;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @since 2020-07-28
 */
public class LeetCode_104 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    class Solution {

        /**
         * BFS
         */
        public int maxDepth1(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int maxDepth = 0;
            while (!queue.isEmpty()) {
                maxDepth++;
                int levelSize = queue.size();
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = queue.pollFirst();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return maxDepth;
        }

        /**
         * 递归
         */
        public int maxDepth2(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int leftMaxDepth = maxDepth2(root.left);
            int rightMaxDepth = maxDepth2(root.right);
            return Math.max(leftMaxDepth, rightMaxDepth) + 1;
        }

    }

}
