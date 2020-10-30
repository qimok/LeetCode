/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * @since 2020-05-01 22:17
 */
public class LeetCode_21 {

    class Solution {

        /**
         * 类似归并排序中合并的步骤
         */
        public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode curr = dummyHead;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    curr.next = l1;
                    l1 = l1.next;
                } else {
                    curr.next = l2;
                    l2 = l2.next;
                }
                curr = curr.next;
            }
            if (l1 == null) {
                curr.next = l2;
            }
            if (l2 == null) {
                curr.next = l1;
            }
            return dummyHead.next;
        }

        /**
         * 递归
         */
        public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (l1.val < l2.val) {
                l1.next = mergeTwoLists2(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwoLists2(l1, l2.next);
                return l2;
            }
        }

    }
    
}
