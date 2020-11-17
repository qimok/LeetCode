import java.util.HashSet;
import java.util.Set;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/open-the-lock/
 * @since 2020-11-17
 */
public class LeetCode_752 {

    class Solution {

        /**
         * 双向 BFS
         * <p>
         *     一般 BFS 用队列解决，此题为了方便判断两个集合是否有交集，使用 HashSet
         */
        public int openLock(String[] deadends, String target) {
            Set<String> deads = new HashSet<>();
            // 记录需要跳过的死亡密码
            for (String dead : deadends) deads.add(dead);
            int step = 0;
            // 记录已经穷举过的密码，防止走回头路
            Set<String> visited = new HashSet<>();
            // 用集合不用队列，可以快速判断元素是否存在
            Set<String> q1 = new HashSet<>();
            Set<String> q2 = new HashSet<>();
            q1.add("0000");
            q2.add(target);
            while (!q1.isEmpty() && !q2.isEmpty()) {
                if (q1.size() > q2.size()) {
                    /**
                     * 按照 BFS 的逻辑，队列（集合）中的元素越多，扩散之后新的队列（集合）中的元素就越多；
                     * 在双向 BFS 算法中，如果每次都选择一个较小的集合进行扩散，那么占用的空间增长速度就会慢一些，效率就会高一些
                     */
                    Set set = q1;
                    q1 = q2;
                    q2 = set;
                }
                Set<String> temp = new HashSet<>();
                for (String cur : q1) {
                    // 判断是否到达终点 start
                    if (deads.contains(cur)) continue;
                    if (q2.contains(cur)) return step;
                    // 判断是否到达终点 end
                    visited.add(cur);
                    for (int i = 0; i < 4; i++) {
                        String upOne = plusOne(cur, i);
                        if (!visited.contains(upOne)) {
                            temp.add(upOne);
                        }
                        String downOne = minusOne(cur, i);
                        if (!visited.contains(downOne)) {
                            temp.add(downOne);
                        }
                    }
                }
                step++;
                // temp 相当于 q1
                // 这里交换 q1、q2，下一轮 while 就是扩散 q2
                q1 = q2;
                q2 = temp;
            }
            return -1;
        }

        /**
         * 指定位置 +1
         */
        private String plusOne(String cur, Integer offset) {
            char[] c = cur.toCharArray();
            if (c[offset] == '9') {
                c[offset] = '0';
            } else {
                c[offset] += 1;
            }
            return new String(c);
        }

        /**
         * 指定位置 -1
         */
        private String minusOne(String cur, Integer offset) {
            char[] c = cur.toCharArray();
            if (c[offset] == '0') {
                c[offset] = '9';
            } else {
                c[offset] -= 1;
            }
            return new String(c);
        }

    }

}
