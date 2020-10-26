/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/same-tree/
 * @since 2020-08-07
 */
public class LeetCode_100 {

    class Solution {

        public boolean isSameTree(TreeNode p, TreeNode q) {
            if (p == null && q == null) {
                return true;
            }
            if (p == null || q == null) {
                return false;
            }
            if (p.val != q.val) {
                return false;
            }
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

    }

}
