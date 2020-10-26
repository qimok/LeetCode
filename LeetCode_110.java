/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/balanced-binary-tree/
 * @since 2020-08-17
 */
public class LeetCode_110 {

    class Solution {

        public boolean isBalanced(TreeNode root) {
            // 若 recur(root) != 1 ，则说明此树平衡，返回 true ； 否则返回 false
            return recur(root) != -1;
        }

        private int recur(TreeNode root) {
            if (root == null) {
                // 当越过叶子节点时，返回高度 0
                return 0;
            }
            int left = recur(root.left);
            if (left != -1) {
                // 当左（右）子树高度 left == -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1
                return -1;
            }
            int right = recur(root.right);
            if (right != -1) {
                // 当左（右）子树高度 left == -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1
                return -1;
            }
            // 当节点root 左 / 右子树的高度差 < 2 ：则返回以节点 root 为根节点的子树的最大高度，即节点 root 的左右子树中最大高度加 1 （max(left, right) + 1）；
            // 当节点root 左 / 右子树的高度差 ≥ 2 ：则返回 −1 ，代表 此子树不是平衡树
            return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
        }

    }

}
