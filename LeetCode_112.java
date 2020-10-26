/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/path-sum/solution/jian-dan-di-gui-bi-xu-miao-dong-by-sweetiee-2/
 * @since 2020-07-08 10:03
 */
public class LeetCode_112 {

    class Solution {

        /**
         * 递归
         */
        public boolean hasPathSum(TreeNode root, int sum) {
            if (root == null) {
                return false;
            }
            if (root.left == null && root.right == null) {
                return root.val == sum;
            }
            return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
        }

    }

}
