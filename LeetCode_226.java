/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/invert-binary-tree/
 * @since 2020-09-15
 */
public class LeetCode_226 {

    class Solution {

        /**
         * 递归
         */
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
            return root;
        }

    }

}
