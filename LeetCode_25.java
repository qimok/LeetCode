import java.util.*;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * @since 2020-05-16 13:56
 */
public class LeetCode_25 {

    class Solution {

        /**
         * 用栈
         * <p>
         *     用栈，把 k 个数压入栈中，然后弹出来的顺序就是翻转的
         *     这里要注意几个问题：
         *          第一，剩下的链表个数够不够 k 个（因为不够 k 个不用翻转）
         *          第二，已经翻转的部分要与剩下链表连接起来
         *
         *     从功能上来讲，LinkedList、ArrayDeque 都可以作为栈和队列
         *     从执行效率上来将，LinkedList作为栈、ArrayDeque 作为队列
         */
        public ListNode reverseKGroup1(ListNode head, int k) {
            LinkedList<ListNode> stack = new LinkedList();
            ListNode dummy = new ListNode(0);
            ListNode curr = dummy;
            while (true) {
                int count = 0;
                ListNode temp = head;
                while (temp != null &&  count < k) {
                    stack.addLast(temp);
                    temp = temp.next;
                    count++;
                }
                if (count != k) {
                    curr.next = head;
                    break;
                }
                while (!stack.isEmpty()) {
                    curr.next = stack.pollLast();
                    curr = curr.next;
                }
                curr.next = temp;
                head = temp;
            }
            return dummy.next;
        }

        /**
         * 尾插法
         * <p>
         *     pre
         *     tail    head
         *     dummy    1     2     3     4     5
         *     # 我们将 tail 移到要翻转的部分最后一个元素
         *     pre     head       tail
         *     dummy    1     2     3     4     5
         *             curr
         *     # 尾插法的意思就是依次把 curr 移到 tail 后面
         *     pre          tail  head
         *     dummy    2     3    1     4     5
         * 	                      curr
         *     # 依次类推
         *     pre     tail       head
         *     dummy    3     2    1     4     5
         * 		       curr     nextHead
         * ....
         */
        public ListNode reverseKGroup2(ListNode head, int k) {
            ListNode dummy = new ListNode(0);
            dummy.next = head;
            ListNode pre = dummy;
            ListNode tail = dummy;
            while (true) {
                int count = 0;
                while (tail != null && count < k) {
                    tail = tail.next;
                    count++;
                }
                if (tail == null) {
                    break;
                }
                ListNode nextHead = pre.next;
                while (pre.next != tail) {
                    ListNode curr = pre.next;
                    pre.next = curr.next;
                    curr.next = tail.next;
                    tail.next = curr;
                }
                pre = nextHead;
                tail = nextHead;
            }
            return dummy.next;
        }

        /**
         * 递归
         */
        public ListNode reverseKGroup3(ListNode head, int k) {
            ListNode curr = head;
            int count = 0;
            while (curr != null && count < k) {
                curr = curr.next;
                count++;
            }
            if (count == k) {
                curr = reverseKGroup3(curr, k); // 下一段长度为 k 且待排序的子链
                while (count != 0) {
                    /**
                     * 当前子链表每次循环都会改变一个指针的指向，比如原来是 1 -> 2 -> 3
                     * 下一个子链 <- 1 2 -> 3
                     * 下一个子链 <- 1 <- 2 3
                     * 下一个子链 <- 1 <- 2 <- 3
                     *
                     * 最后 curr 指向元素3
                     */
                    count--;
                    ListNode temp = head.next; // 当前链表的 head 的下一个元素
                    head.next = curr; // 当前链表 head 的下一个指针指向下一个待排序的子链的头节点
                    curr = head; // 当前链表的 head 节点 赋值给 curr 节点（curr 原指下一个子链的头节点）
                    head = temp; // head 节点右移一位
                }
                head = curr; // 最后将 curr 复制给 head 即可
            }
            return head;
        }

    }

}
