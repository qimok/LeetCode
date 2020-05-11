/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @since 2020-05-11 09:55
 */
public class LeetCode_236 {

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
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            // 终止条件：若当前节点为 null、p、q 之一，直接返回当前节点
            if (root == null || root == p || root == q) {
                return root;
            }
            // 下探：分别进入自己的左孩子，右孩子
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            // 当 left 和 right 同时不为空 ：说明 p,q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root
            if (left != null && right != null) {
                return root;
            }
            // 当 left 不为空 ，right 为空：p,q 都不在 root 的右子树中，直接返回 left，具体可分为两种情况：
            // p,q 其中一个在 root 的 左子树中，此时 left 指向 p（假设为 p）；--内层
            // p,q 两节点都在 root 的 左子树中，此时的 left 指向 最近公共祖先节点 ；--外层
            // 当 right 不为空 ， left 为空：同上
            return left != null ? left : right;
        }

    }

}
