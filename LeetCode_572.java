/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/subtree-of-another-tree/
 * @since 2020-05-07 13:23
 */
public class LeetCode_572 {

    class Solution {

        /**
         * 递归
         */
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == null) { // 递归终止条件
                return false;
            }
            if (isSamp(s, t)) { // 处理当前层逻辑
                return true;
            }
            return isSubtree(s.left, t) || isSubtree(s.right, t); // 分别下探到左右子树
        }

        private boolean isSamp(TreeNode p, TreeNode q) {
            if (p == null && q == null) { // 递归终止条件
                return true;
            }
            if (p == null || q == null) { // 递归终止条件
                return false;
            }
            if (p.val != q.val) { // 处理当前层逻辑
                return false;
            }
            return isSamp(p.left, q.left) && isSamp(p.right, q.right); // 分别下探到下一层
        }

    }
}
