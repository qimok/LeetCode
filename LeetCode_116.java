/**
 * @author Xinshuai
 * @description 题目链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 * @since 2020-09-15
 */
public class LeetCode_116 {

    class Node {

        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }

    }

    class Solution {

        public Node connect(Node root) {
            if (root == null) return null;
            connectTwoNode(root.left, root.right);
            return root;
        }

        private void connectTwoNode(Node node1, Node node2) {
            if (node1 == null || node2 == null) return;
            node1.next = node2;
            connectTwoNode(node1.left, node1.right);
            connectTwoNode(node2.left, node2.right);
            connectTwoNode(node1.right, node2.left);
        }

    }

}
