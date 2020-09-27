/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/sort-list/
 * @since 2020-09-27
 */
public class LeetCode_148 {

    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    class Solution {

        /**
         * 归并排序
         * <p>
         *     分割 cut 环节： 找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）
         *          我们使用 fast, slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点
         *          找到中点 slow 后，执行 slow.next = None 将链表切断
         *          递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)
         *      cut 递归终止条件： 当 head.next == None 时，说明只有一个节点了，直接返回此节点
         *          合并 merge 环节： 将两个排序链表合并，转化为一个排序链表
         *          双指针法合并，建立辅助 ListNode h 作为头部
         *          设置两指针 left, right 分别指向两链表头部，比较两指针处节点值大小，由小到大加入合并链表头部，指针交替前进，直至添加完两个链表
         *          返回辅助 ListNode h 作为头部的下个节点 h.next
         *          时间复杂度 O(l + r)，l, r 分别代表两个链表长度
         *      当题目输入的 head == None 时，直接返回None
         */
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode slow = head, fast = head.next;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            ListNode temp = slow.next;
            slow.next = null;
            // 自顶向下
            ListNode left = sortList(head);
            ListNode right = sortList(temp);
            ListNode h = new ListNode(0);
            ListNode res = h;
            while (left != null && right != null) {
                if (left.val < right.val) {
                    h.next = left;
                    left = left.next;
                } else {
                    h.next = right;
                    right = right.next;
                }
                h = h.next;
            }
            h.next = left != null ? left : right;
            return res.next;
        }

    }

}
