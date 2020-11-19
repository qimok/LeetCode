/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/fan-zhuan-lian-biao-lcof/
 * @since 2020-11-19
 */
public class LeetCode_24_jzOffer {

    class Solution {

        /**
         * 迭代
         * <p>
         *     双指针直接反转
         */
        public ListNode reverseList1(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode tmp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tmp;
            }
            return pre;
        }

        /**
         * 递归
         */
        public ListNode reverseList2(ListNode head) {
            if (head == null || head.next == null) {
                // 递归终止
                return head;
            }
            // 下探（自上而下）
            // 假设初始链表为 1 -> 2 -> 3 -> 4 -> 5，如果 head 在节点4，那么 cur 就代表节点 5
            ListNode cur = reverseList2(head.next);
            while (head.next != null) {
                // 指针反转
                head.next.next = head;
                head.next = null;
            }
            return cur;
        }

    }
    
}
