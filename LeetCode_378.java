import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/kth-smallest-element-in-a-sorted-matrix/solution/
 * @since 2020-07-02 13:13
 */
public class LeetCode_378 {

    class Solution {

        public int kthSmallest1(int[][] matrix, int k) {
            int rowLen = matrix.length, columnLen = matrix[0].length;
            int[] sorted = new int[rowLen * columnLen];
            int index = 0;
            for (int[] row : matrix) {
                for (int num : row) {
                    sorted[index++] = num;
                }
            }
            Arrays.sort(sorted);
            return sorted[k - 1];
        }

        public int kthSmallest2(int[][] matrix, int k) {
            // 1、定义小根堆的比较函数
            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0] - o2[0]);
            // 2、把每一列的第一个元素放入小根堆
            for (int i = 0; i < matrix.length; i++) {
                // 记录 数组（含三个元素） 数值 横坐标 纵坐标
                pq.offer(new int[]{matrix[i][0], i, 0});
            }
            // 3、找到小根堆堆顶（最小值）去除，然后最小值所在的列的下一个元素入堆，一直这样操作直到找到第k小。
            // 出栈 k-1 次 再取栈顶的时候就为第k个最小堆
            for (int i = 0; i < k - 1; i++) {
                int[] now = pq.poll();
                if (now[2] < matrix[0].length - 1) {
                    // 把栈顶旁边的数添加到队列进行比较
                    pq.offer(new int[]{matrix[now[1]][now[2] + 1], now[1], now[2] + 1});
                }
            }
            return pq.poll()[0];
        }

    }

}
