/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @since 2020-05-05 19:09
 */
public class LeetCode_98 {

    class Solution {

        long pre = Long.MIN_VALUE;

        /**
         * 中序遍历：中序遍历时，判断当前节点是否大于中序遍历的前一个节点，如果大于，说明满足BST，继续遍历；否则直接返回false
         */
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
            // 访问当前节点，如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回false；否则继续遍历
            if (pre >= root.val) {
                return false;
            }
            pre = root.val;
            return isValidBST(root.right);
        }

    }
}
