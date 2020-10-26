/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 * @since 2020-08-02
 */
public class LeetCode_114 {

    class Solution {

        /**
         *     1
         *    / \
         *   2   5
         *  / \   \
         * 3   4   6
         *
         * 将 1 的左子树插入到右子树的地方
         *     1
         *      \
         *       2         5
         *      / \         \
         *     3   4         6
         * 将原来的右子树接到左子树的最右边节点
         *     1
         *      \
         *       2
         *      / \
         *     3   4
         *          \
         *           5
         *            \
         *             6
         *
         * 将 2 的左子树插入到右子树的地方
         *     1
         *      \
         *       2
         *        \
         *         3       4
         *                  \
         *                   5
         *                    \
         *                     6
         *
         * 将原来的右子树接到左子树的最右边节点
         *     1
         *      \
         *       2
         *        \
         *         3
         *          \
         *           4
         *            \
         *             5
         *              \
         *               6
         *
         *   ......
         *
         */
        public void flatten1(TreeNode root) {
            while (root != null) {
                if (root.left == null) {
                    // 左子树为 null，直接考虑下一个节点
                    root = root.right;
                } else {
                    // 找左子树最右边的节点
                    TreeNode pre = root.left;
                    while (pre.right != null) {
                        pre = pre.right;
                    }
                    // 将原来的右子树接到左子树的最右边节点
                    pre.right = root.right;
                    // 将左子树插入到右子树的地方
                    root.right = root.left;
                    root.left = null;
                    // 考虑下一个节点
                    root = root.right;
                }
            }
        }

        /**
         * dfs + 递归
         * 首先将根节点的左子树变成链表
         * 其次将根节点的右子树变成链表
         * 最后将变成链表的右子树放在变成链表的左子树的最右边
         */
        public void flatten2(TreeNode root) {
            if (root == null) {
                return;
            }
            // 将根节点的左子树变成链表
            flatten2(root.left);
            // 将根节点的右子树变成链表
            flatten2(root.right);
            TreeNode temp = root.right;
            // 把树的右边换成左边的链表
            root.right = root.left;
            // 将左边置空
            root.left = null;
            // 找到树的最右边的接口
            while (root.right != null) {
                root = root.right;
            }
            // 把右边的链表接到刚才树的最右边的节点
            root.right = temp;
        }

    }

}
