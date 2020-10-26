import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * @since 2020-09-14
 */
public class LeetCode_94 {

    class Solution {

        /**
         * 迭代
         */
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            while (!stack.isEmpty() || root != null) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                res.add(root.val);
                root = root.right;
            }
            return res;
        }

        /**
         * 递归
         */
        public List<Integer> inorderTraversal2(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(res, root);
            return res;
        }

        private void dfs(List<Integer> res, TreeNode root) {
            if (root == null) return;
            dfs(res, root.left);
            res.add(root.val);
            dfs(res, root.right);
        }

        /**
         * 莫里斯遍历
         */
        public List<Integer> inorderTraversal3(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            TreeNode pre;
            while (root != null) {
                // 如果左节点不为空，就将当前节点连带右子树全部挂到左节点的最右子树下面
                if (root.left != null) {
                    pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    pre.right = root;
                    // 将 root 指向 root 的左子树
                    TreeNode tmp = root;
                    root = root.left;
                    tmp.left = null;
                } else {
                    // 左子树为空，则打印这个节点，并向右边遍历
                    res.add(root.val);
                    root = root.right;
                }
            }
            return res;
        }

    }

}
