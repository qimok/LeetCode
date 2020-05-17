import java.util.*;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/course-schedule-ii/
 * @since 2020-05-17 16:44
 */
public class LeetCode_210 {

    class Solution {

        /**
         * 拓扑排序：bfs
         */
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            int[] inDegree = new int[numCourses]; // 入度动态数组
            Map<Integer, List<Integer>> map = new HashMap<>(); // 键：课程编号，值：依赖它的后续课程列表
            Queue<Integer> queue = new LinkedList<>(); // 只有入度为 0 的课程会入队
            for (int i = 0; i < prerequisites.length; i++) {
                inDegree[prerequisites[i][0]]++;
                if (map.containsKey(prerequisites[i][1])) {
                    map.get(prerequisites[i][1]).add(prerequisites[i][0]);
                } else {
                    List<Integer> list = new ArrayList<>();
                    list.add(prerequisites[i][0]);
                    map.put(prerequisites[i][1], list);
                }
            }
            // 筛选入度为 0 的课程，进行入队
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                }
            }
            List<Integer> res = new ArrayList<>();
            while (!queue.isEmpty()) {
                Integer curr = queue.poll();
                res.add(curr);
                if (map.containsKey(curr)) {
                    for (Integer num : map.get(curr)) { // 遍历依赖它的其它课程
                        inDegree[num]--;
                        if (inDegree[num] == 0) {
                            queue.add(num);
                        }
                    }
                }
            }
            return res.size() == numCourses ? res.stream().mapToInt(Integer::valueOf).toArray() : new int[0];
        }

    }

}
