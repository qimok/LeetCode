/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
 * @since 2020-06-21 11:12
 */
public class LeetCode_124 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {

        // 初始值必须是 Integer.MIN_VALUE，防止全部节点为负数的情况
        int maxSum = Integer.MIN_VALUE;

        /**
         * 递归
         * <p>
         *            ｜
         *            a
         *          b   c
         *          ｜  ｜
         *     所有可能的路径情况：
         *      [左中右] b + a + c
         *      [左]    b + a
         *      [右]    c + a
         *      [单节点] a
         *
         */
        public int maxPathSum(TreeNode root) {
            maxGain(root);
            return maxSum;
        }

        /**
         * 空节点的最大贡献值等于 0
         * 非空节点的最大贡献值等于节点值与其子节点中的最大贡献值之和（对于叶节点而言，最大贡献值等于节点值）
         */
        private int maxGain(TreeNode node) {
            if (node == null) { // 递归终止条件
                return 0;
            }
            // 递归计算左右节点的最大贡献值
            // 只有在最大贡献值大于 0 时，才会选取对应的子节点
            int leftGain = Math.max(0, maxGain(node.left));
            int rightGain = Math.max(0, maxGain(node.right));
            // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
            int priceNewpath = node.val + leftGain + rightGain;
            maxSum = Math.max(maxSum, priceNewpath);
            // 返回当前节点与左右子节点可以构成最大贡献值的路径
            return node.val + Math.max(leftGain, rightGain);
        }

    }

}
