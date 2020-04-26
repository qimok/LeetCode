import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Xinshuai
 * @description
 * @since 2020-04-26 14:00
 */
public class LeetCode_23 {

    public class ListNode {
       int val;
       ListNode next;
       ListNode() {}
       ListNode(int x) { val = x; }
   }

    class Solution {

        /**
         * 借助优先级队列
         * <p>
         *     时间复杂度：O(n * log(k))：n是所有链表中元素的总和，k是链表个数
         *     空间复杂度：O(n)
         */
        public ListNode mergeKLists1(ListNode[] lists) {
            ListNode dummy = new ListNode();
            ListNode head = dummy;
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
            for (ListNode l : lists) {
                if (l != null) {
                    pq.add(l);
                }
            }
            while (!pq.isEmpty()) {
                ListNode curr = pq.poll();
                head.next = curr;
                head = head.next;
                if (curr.next != null) {
                    pq.add(curr.next);
                }
            }
            return dummy.next;
        }

        /**
         * 分治
         */
        public ListNode mergeKLists2(ListNode[] lists) {
            if (lists == null || lists.length == 0) {
                return null;
            }
            return merge(lists, 0, lists.length - 1);
        }

        private ListNode merge(ListNode[] lists, int left, int right) {
            if (left == right) {
                return lists[left];
            }
            // 一分为二，分而治之
            int mid = left + (right - left) / 2;
            ListNode l1 = merge(lists, left, mid);
            ListNode l2 = merge(lists, mid + 1, right);
            // 合并
            return mergeTwo(l1, l2);
        }

        private ListNode mergeTwo(ListNode l1, ListNode l2) {
            if (l1 == null) return l2;
            if (l2 == null) return l1;
            if (l1.val < l2.val) {
                l1.next = mergeTwo(l1.next, l2);
                return l1;
            } else {
                l2.next = mergeTwo(l1, l2.next);
                return l2;
            }
        }

    }

}
