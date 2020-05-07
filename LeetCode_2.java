/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/add-two-numbers/
 * @since 2020-05-07 13:47
 */
public class LeetCode_2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {

        /**
         * 对于链表问题，返回结果为头结点时：
         * <p>
         *     通常需要先初始化一个预先指针 pre，该指针的下一个节点指向真正的头结点head，
         *     再初始化一个代表当前位置的可以移动的指针 curr，该指针的初始值等于预先指针 pre
         */
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode pre = new ListNode(0); // 预先指针
            ListNode curr = pre; // 用于指针移动
            int carry = 0;
            while (l1 != null || l2 != null) {
                int x = l1 == null ? 0 : l1.val;
                int y = l2 == null ? 0 : l2.val;

                int sum = x + y + carry;
                carry = sum / 10;
                sum %= 10;

                curr.next = new ListNode(sum);
                curr = curr.next;
                if (l1 != null) {
                    l1 = l1.next;
                }
                if (l2 != null) {
                    l2 = l2.next;
                }
            }
            // 两个一位的数字的和肯定小于20（最大为18），故 carry 只有可能等于 0 或者 1
            if (carry == 1) {
                curr.next = new ListNode(carry);
            }
            return pre.next;
        }

    }

}
