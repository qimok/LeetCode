import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
 * @since 2020-05-08 14:27
 */
public class LeetCode_145 {

    class Solution {

        /**
         * 递归
         */
        public List<Integer> postorderTraversal1(TreeNode root) {
            List<Integer> output = new ArrayList<>();
            of(root, output);
            return output;
        }

        private void of(TreeNode root, List<Integer> output) {
            if (root != null) {
                if (root.left != null) {
                    of(root.left, output); // 下探
                }
                if (root.right != null) {
                    of(root.right, output); // 下探
                }
                output.add(root.val); // 处理当前层逻辑
            }
            // 递归终止条件
        }

        /**
         * DFS
         * <p>
         *     LinkedList 的常用方法：
         *     - poll 是基于队列结构实现的方法，当队列中没有元素时，调用该方法返回 null
         *     - pop 是基于栈结构实现的方法，当栈中没有元素时，调用该方法会发生异常
         *     public E poll()：检索并删除此列表的头部（第一个元素）
         *     public E pollFirst()：检索并删除此列表的第一个元素，如果此列表为空，则返回null
         *     public E pollLast()：检索并删除此列表的最后一个元素，如果此列表为空，则返回null
         *     public E peek()：检索但不删除此列表的头部（第一个元素）
         *     public E peekFirst()：检索但不删除此列表的第一个元素，如果此列表为空，则返回null
         *     public E peekLast()：检索但不删除此列表的最后一个元素，如果此列表为空，则返回null
         *     public void push(E e)：将元素插入列表的前面
         *     public E pop()：删除并返回列表的第一个元素
         *     add：在列表的末尾添加元素
         *     void addFirst(Object o)：在列表首部添加元素
         *     void addLast(Object o)：在列表末尾添加元素
         *     offer：将元素插入队列尾部，成功返回true，失败（没有空间）返回false
         */
        public List<Integer> postorderTraversal2(TreeNode root) {
            LinkedList<Integer> output = new LinkedList<>();
            if (root == null) {
                return output;
            }
            LinkedList<TreeNode> stack = new LinkedList<>();
            stack.add(root); // 在列表的末尾添加元素
            while (!stack.isEmpty()) {
                TreeNode node = stack.pollLast(); // 检索并删除此列表的最后一个元素，如果此列表为空，则返回null
                /**
                 * output：
                 * 入队(addFirst)：根节点入队首、右节点入队首、左节点入队首
                 */
                output.addFirst(node.val); // 在列表首部添加元素
                if (node.left != null) {
                    stack.add(node.left); // 在列表的末尾添加元素
                }
                if (node.right != null) {
                    stack.add(node.right); // 在列表的末尾添加元素
                }
            }
            /**
             * output:
             * 出队(按顺序出队)：左节点出队、右节点出队、根节点出队
             */
            return output;
        }

    }

}
