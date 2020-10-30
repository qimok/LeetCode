import java.util.Arrays;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @since 2020-10-30
 */
public class LeetCode_19 {

    class Solution {

        /**
         * 遍历两次
         * <p>
         *     先遍历一次链表，求出链表的总长度 len，第二次遍历的时候，根据 len - n，就算出需要再遍历多少个节点，找到要删除的节点的前一个节点，
         *     然后将前一个节点的 next 指针指向 下下一个节点，即可删除指定位置的节点
         */
        public ListNode removeNthFromEnd1(ListNode head, int n) {
            // 增加一个特殊节点，方便边界处理
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode cur = dummy;
            int len = 0;
            // 第一次遍历，计算链表总长度
            while (cur.next != null) {
                cur = cur.next;
                len++;
            }
            // 如果链表总长度小于 n，那就直接返回
            if (len < n) {
                return head;
            }
            // 计算第二次遍历多少个节点
            int index = len - n;
            cur = dummy;
            // 第二次遍历，找到要删除节点的前一个节点
            while (index > 0) {
                cur = cur.next;
                index--;
            }
            // 删除节点
            cur.next = cur.next.next;
            return dummy.next;
        }

        /**
         * 遍历一次
         * <p>
         *     当链表总长度是 len 时，如果要删除倒数第 n 个节点(假设 n 小于 len)，那么首先要找到第 len - n 个节点，len - n 这个节点就是要删除的节点的前一个节点
         *     当找到 len - n 这个节点就好办了，直接将 len - n 的 next 指针指向下下一个节点，即可删除指定位置的节点
         *     需要两个指针 a 和 b，b 指针先走 n 步，接着 a 和 b 指针同时往前走，当 b 指针走到链表末尾时，a 指针就正好走到要删除的节点的前一个位置了，
         *     最后 a 节点的 next 指针指向下下一个节点，即可删除指定位置的节点
         */
        public ListNode removeNthFromEnd2(ListNode head, int n) {
            // 增加一个特殊节点方便边界判断
            ListNode dummy = new ListNode(-1);
            dummy.next = head;
            ListNode b = dummy, a = dummy;
            // 首先，b 指针先往前走 n 步
            while (b != null && n > 0) {
                b = b.next;
                n--;
            }
            // 假设整个链表长 5，n 是 10，那么第一次遍历完后 b 就等于空了，于是后面的判断就不用做了，直接返回
            if (b == null) {
                return head;
            }
            // 然后，b 指针走到链表最后，a 指针也跟着走，当遍历结束时，a 指针就指向要删除的节点的前一个位置
            while (b.next != null) {
                b = b.next;
                a = a.next;
            }
            // 删除节点
            a.next = a.next.next;
            return dummy.next;
        }

    }

}
