import java.util.LinkedList;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/merge-two-binary-trees/
 * @since 2020-10-15
 */
public class LeetCode_617 {

    class Solution {

        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;
            TreeNode(int x) { val = x; }
        }

        /**
         * 递归
         * <p>
         *     终止条件：树 1 的节点为 null，或者树 2 的节点为 null
         *     递归函数内：将两个树的节点相加后，再赋给树 1 的节点。再递归的执行两个树的左节点，递归执行两个树的右节点
         */
        public TreeNode mergeTrees1(TreeNode t1, TreeNode t2) {
            if (t1 == null || t2 == null) {
                return t1 == null ? t2 : t1;
            }
            return dfs(t1, t2);
        }

        private TreeNode dfs(TreeNode r1, TreeNode r2) {
            if (r1 == null || r2 == null) {
                return r1 == null ? r2 : r1;
            }
            r1.val += r2.val;
            r1.left = dfs(r1.left, r2.left);
            r1.right = dfs(r1.right, r2.right);
            return r1;
        }

        /**
         * 迭代 bfs
         * <p>
         *     只要两颗树的左节点都不为 null，就把将他们放入队列中；同理只要两棵树的右节点都不为 null 了，也将他们放入队列中，然后我们不断的从队列中取出节点，把他们相加
         *     如果出现 树 1 的 left 节点为 null，树 2 的 left 不为 null，直接将树 2 的 left 赋给树 1 就可以了
         *     同理，如果树 1 的 right 节点为 null，树 2 的不为 null，将树 2 的 right 节点赋给树 1
         */
        public TreeNode mergeTrees2(TreeNode t1, TreeNode t2) {
            if (t1 == null || t2 == null) {
                return t1 == null ? t2 : t1;
            }
            LinkedList<TreeNode> queue = new LinkedList<>();
            queue.add(t1);
            queue.add(t2);
            while (queue.size() > 0) {
                TreeNode r1 = queue.remove();
                TreeNode r2 = queue.remove();
                r1.val += r2.val;
                if (r1.left != null && r2.left != null) {
                    queue.add(r1.left);
                    queue.add(r2.left);
                } else if (r1.left == null) {
                    r1.left = r2.left;
                }
                if (r1.right != null && r2.right != null) {
                    queue.add(r1.right);
                    queue.add(r2.right);
                } else if (r1.right == null) {
                    r1.right = r2.right;
                }
            }
            return t1;
        }

    }

}
