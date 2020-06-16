import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
 * @since 2020-06-16 13:19
 */
public class LeetCode_297 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public class Codec {

        /**
         * dfs：先序遍历
         */
        public String serialize1(TreeNode root) {
            return rserialize1(root, "");
        }

        public String rserialize1(TreeNode root, String str) {
            if (root == null) {
                str += "null,";
            } else {
                str += (Integer.valueOf(root.val)) + ",";
                str = rserialize1(root.left, str);
                str = rserialize1(root.right, str);
            }
            return str;
        }

        public TreeNode deserialize1(String data) {
            String[] nodes = data.split(",");
            List<String> list = new LinkedList<>(Arrays.asList(nodes));
            return rdeserialize1(list);
        }

        private TreeNode rdeserialize1(List<String> list) {
            if (list.get(0).equals("null")) {
                list.remove(0);
                return null;
            }
            TreeNode root = new TreeNode(Integer.valueOf(list.get(0)));
            list.remove(0);
            root.left = rdeserialize1(list);
            root.right = rdeserialize1(list);
            return root;
        }

        /**
         * bfs
         * <p>
         *     按照层序遍历把 TreeNode 转成 String
         *     从 String 转 TreeNode 的时候：
         *          用 isLeft 来表示 curr 节点是当前 parent 节点的左孩子还是右孩子
         *          用 queue 来存储还未找到 ChildNode 的node
         *          当 isLeft 从 right 变为 left 时，表示当前 parent 节点的孩子已经找完，可以去下一个 parent 了
         */
        public String serialize2(TreeNode root) {
            StringBuilder sb = new StringBuilder("[");
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode curr = queue.remove();
                if (curr == null) {
                    sb.append("null,");
                } else {
                    sb.append(Integer.valueOf(curr.val) + ",");
                    queue.add(curr.left);
                    queue.add(curr.right);
                }
            }
            sb.setLength(sb.length() - 1); // 将最后一个逗号去掉
            sb.append("]");
            return sb.toString();
        }

        public TreeNode deserialize2(String data) {
            String[] nodes = data.substring(1, data.length() - 1).split(",");
            TreeNode root = getNode(nodes[0]);
            Queue<TreeNode> parents = new LinkedList<>();
            TreeNode parent = root;
            boolean isLeft = true;
            for (int i = 1; i < nodes.length; i++) {
                TreeNode curr = getNode(nodes[i]);
                if (isLeft) {
                    parent.left = curr;
                } else {
                    parent.right = curr;
                }
                if (curr != null) {
                    parents.add(curr);
                }
                isLeft = !isLeft;
                if (isLeft) {
                    // 当 isLeft 从 right 变为 left 时，表示当前 parent 节点的孩子已经找完，可以去下一个 parent 了
                    parent = parents.peek();
                    parents.poll();
                }
            }
            return root;
        }

        private TreeNode getNode(String val) {
            if (val.equals("null")) {
                return null;
            }
            return new TreeNode(Integer.valueOf(val));
        }

    }

}
