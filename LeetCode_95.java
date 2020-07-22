import java.util.ArrayList;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * @since 2020-07-22
 */
public class LeetCode_95 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {

        public List<TreeNode> generateTrees1(int n) {
            List<TreeNode> ans = new ArrayList<>();
            if (n == 0) {
                return ans;
            }
            return getAns(1, n);
        }

        private List<TreeNode> getAns(int start, int end) {
            List<TreeNode> ans = new ArrayList<>();
            if (start > end) {
                ans.add(null);
                return ans;
            }
            if (start == end) {
                TreeNode tree = new TreeNode(start);
                ans.add(tree);
                return ans;
            }
            for (int i = start; i <= end; i++) {
                List<TreeNode> leftTrees = getAns(start, i - 1);
                List<TreeNode> rightTrees = getAns(i + 1, end);
                for (TreeNode leftTree : leftTrees) {
                    for (TreeNode rightTree : rightTrees) {
                        TreeNode root = new TreeNode(i);
                        root.left = leftTree;
                        root.right = rightTree;
                        ans.add(root);
                    }
                }
            }
            return ans;
        }

        /**
         * 动态规划
         * <p>
         *     每次新增加的数字大于之前的所有数字，所以新增加的数字出现的位置只可能是根节点或者是根节点的右孩子，
         *     右孩子的右孩子，右孩子的右孩子的右孩子等等，总之一定是右边。
         *     其次，新数字所在位置原来的子树，改为当前插入数字的左孩子即可，因为插入数字是最大的
         *
         *     解析：https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/
         */
        public List<TreeNode> generateTrees2(int n) {
            List<TreeNode> pre = new ArrayList<>();
            if (n == 0) {
                return pre;
            }
            pre.add(null);
            // 每次增加一个数字
            for (int i = 1; i <= n; i++) {
                List<TreeNode> cur = new ArrayList<>();
                // 遍历之前的所有解
                for (TreeNode root : pre) {
                    // 插入到根节点
                    TreeNode insert = new TreeNode(i);
                    insert.left = root;
                    cur.add(insert);
                    // 插入到右孩子，右孩子的右孩子...最多找 n 次孩子
                    for (int j = 0; j <= n; j++) {
                        // 复制当前的树
                        TreeNode rootCopy = treeCopy(root);
                        // 找到要插入右孩子的位置
                        TreeNode right = rootCopy;
                        int k = 0;
                        // 遍历 j 次找右孩子
                        for (; k < j; k++) {
                            if (right == null) {
                                break;
                            }
                            right = right.right;
                        }
                        // 到达 null 提前结束
                        if (right == null) {
                            break;
                        }
                        // 保存当前右孩子的位置的子树作为插入节点的左孩子
                        TreeNode rightTree = right.right;
                        insert = new TreeNode(i);
                        // 右孩子是插入的节点
                        right.right = insert;
                        // 插入节点的左孩子更新为插入位置之前的子树
                        insert.left = rightTree;
                        // 加入结果中
                        cur.add(rootCopy);
                    }
                }
                pre = cur;
            }
            return pre;
        }

        private TreeNode treeCopy(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode newRoot = new TreeNode(root.val);
            newRoot.left = treeCopy(root.left);
            newRoot.right = treeCopy(root.right);
            return newRoot;
        }

    }

}
