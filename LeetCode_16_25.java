import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/lru-cache-lcci/
 * @since 2020-11-26
 */
public class LeetCode_16_25 {

    /**
     * LinkedHashMap
     */
    class Solution1 {

        public class LRUCache {

            int capacity;
            Map<Integer, Integer> map;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                map = new LinkedHashMap<>();
            }

            public int get(int key) {
                if (!map.containsKey(key)) {
                    return -1;
                }
                // 先删除旧的位置，再放入新位置
                Integer value = map.remove(key);
                map.put(key, value);
                return value;
            }

            public void put(int key, int value) {
                if (map.containsKey(key)) {
                    map.remove(key);
                    map.put(key, value);
                    return;
                }
                map.put(key, value);
                // 超出 capacity，删除最久没用的,利用迭代器删除第一个
                if (map.size() > capacity) {
                    map.remove(map.entrySet().iterator().next().getKey());
                }
            }
        }

    }

    /**
     * LinkedHashMap 的 accessOrder 模式
     */
    class Solution2 {

        public class LRUCache {

            int capacity;
            Map<Integer, Integer> map;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                // accessOrder：当 accessOrder 为 true 时，每次调用 get 方法时都会将我们访问的 Node 节点移动到最后，
                //              使之成为尾部节点，从而改变了数据在 LinkedHashMap 中的存储顺序
                map = new LinkedHashMap<>((int)((float)capacity / 0.75f + 1.0f), 0.75f, true);
            }

            public int get(int key) {
                Integer value = map.get(key);
                if (value == null)
                    return -1;
                return value;
            }


            public void put(int key, int value) {
                map.put(key, value);
                if (map.size() > capacity) {
                    map.remove(map.entrySet().iterator().next().getKey());
                }
            }

        }
    }

    /**
     * 双链表 + HashMap
     */
    class Solution3 {

        public class LRUCache {

            private class ListNode {
                int key;
                int value;
                ListNode pre;
                ListNode next;

                public ListNode(int key, int value) {
                    this.key = key;
                    this.value = value;
                    pre = null;
                    next = null;
                }

            }

            private int capacity;
            private Map<Integer, ListNode> map;
            private ListNode head;
            private ListNode tail;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.map = new HashMap<>();
                this.head = new ListNode(-1, -1);
                this.tail = new ListNode(-1, -1);
                head.next = tail;
                tail.pre = head;
            }

            public int get(int key) {
                if (!map.containsKey(key)) {
                    return -1;
                }
                ListNode node = map.get(key);
                node.pre.next = node.next;
                node.next.pre = node.pre;
                moveToTail(node);
                return node.value;
            }

            public void put(int key, int value) {
                if (get(key) != -1) {
                    map.get(key).value = value;
                    return;
                }
                ListNode node = new ListNode(key, value);
                map.put(key, node);
                moveToTail(node);
                if (map.size() > capacity) {
                    map.remove(head.next.key);
                    head.next = head.next.next;
                    head.next.pre = head;
                }
            }

            private void moveToTail(ListNode node) {
                node.pre = tail.pre;
                tail.pre = node;
                node.pre.next = node;
                node.next = tail;
            }

        }


    }

    /**
     * 单链表 + HashMap
     */
    class Solution4 {

        public class LRUCache {

            private class ListNode {
                int key;
                int value;
                ListNode next;

                public ListNode(int key, int value) {
                    this.key = key;
                    this.value = value;
                    this.next = null;
                }

            }

            private int capacity;
            private Map<Integer, ListNode> map;
            private ListNode head;
            private ListNode tail;

            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.map = new HashMap<>();
                head = new ListNode(-1, -1);
                tail = head;
            }

            public int get(int key) {
                if (!map.containsKey(key)) {
                    return -1;
                }
                // map中存放的是要找的节点的前驱
                ListNode pre = map.get(key);
                ListNode cur = pre.next;
                if (cur != tail) {
                    pre.next = cur.next;
                    map.put(cur.next.key, pre);
                    map.put(cur.key, tail);
                    moveToTail(cur);
                }
                return cur.value;
            }

            public void put(int key, int value) {
                if (get(key) != -1) {
                    map.get(key).next.value = value;
                    return;
                }
                ListNode node = new ListNode(key, value);
                map.put(key, tail);
                moveToTail(node);
                if (map.size() > capacity) {
                    // 移出操作，一定不能忘记
                    map.remove(head.next.key);
                    head.next = head.next.next;
                    map.put(head.next.key, head);
                }
            }

            private void moveToTail(ListNode node) {
                node.next = null;
                tail.next = node;
                tail = tail.next;
            }

        }

    }

}
