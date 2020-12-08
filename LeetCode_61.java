import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/rotate-list/
 * @since 2020-12-08
 */
public class LeetCode_61 {

    class Solution {

        public ListNode rotateRight(ListNode head, int k) {
            if (head == null) return null;
            if (head.next == null) return head;
            ListNode oldTail = head, newTail = head;
            // 1、得到链表的长度
            int n = 1;
            while (oldTail.next != null) {
                oldTail = oldTail.next;
                n++;
            }
            // 2、使链表的尾部指向链表的头部，形成闭环
            oldTail.next = head;
            // 3、由于 k 有可能大于 n，所以 n - k % n 就是新链表的头，n - k % n - 1 就是新链表的尾
            for (int i = 0; i < n - k % n - 1; i++) {
                newTail = newTail.next;
            }
            // 4、由于当前链表还是闭环，所以新链表尾部的下一个节点肯定是新链表的头部
            ListNode newHead = newTail.next;
            // 5、使新链表的尾指向空
            newTail.next = null;
            // 6、返回新的链表头
            return newHead;
        }

    }

}
