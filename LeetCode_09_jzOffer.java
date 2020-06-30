import java.util.LinkedList;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 * @since 2020-06-30 12:52
 */
public class LeetCode_09_jzOffer {

    class CQueue {

        LinkedList<Integer> a, b;

        /**
         * 栈 A 用于加入队尾操作，栈 B 用于将元素倒序，从而实现删除队首元素
         */
        public CQueue() {
            a = new LinkedList<>();
            b = new LinkedList<>();
        }

        /**
         * 加入队尾 appendTail()函数：
         *      将数字 val 加入栈 A 即可。
         */
        public void appendTail(int value) {
            a.addLast(value);
        }

        /**
         * 删除队首deleteHead()函数： 有以下三种情况：
         *      当栈 B 不为空： B中仍有已完成倒序的元素，因此直接返回 B 的栈顶元素
         *      否则，当 A 为空： 即两个栈都为空，无元素，因此返回 -1
         *      否则，将栈 A 元素全部转移至栈 B 中，实现元素倒序，并返回栈 B 的栈顶元素
         */
        public int deleteHead() {
            if (!b.isEmpty()) {
                return b.removeLast();
            }
            if (a.isEmpty()) {
                return -1;
            }
            while (!a.isEmpty()) {
                b.addLast(a.removeLast());
            }
            return b.removeLast();
        }

    }

}
