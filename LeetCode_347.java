import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @since 2020-09-19
 */
public class LeetCode_347 {

    class Solution {

        /**
         * 小根堆
         */
        public int[] topKFrequent1(int[] nums, int k) {
            Map<Integer, Integer> counter = IntStream.of(nums).boxed()
                    .collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
            Queue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(counter::get));
            counter.forEach((num, cnt) -> {
                if (pq.size() < k) {
                    pq.offer(num);
                } else if (counter.get(pq.peek()) < cnt) {
                    pq.poll();
                    pq.offer(num);
                }
            });
            int[] res = new int[k];
            int idx = 0;
            for (int num : pq) {
                res[idx++] = num;
            }
            return res;
        }

        public int[] topKFrequent2(int[] nums, int k) {
            Map<Integer, Integer> counter = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
            List<Integer>[] freqList = new List[nums.length + 1];
            for (int i = 0; i < freqList.length; i++) {
                freqList[i] = new ArrayList<>();
            }
            counter.forEach((num, cnt) -> {
                freqList[cnt].add(num);
            });
            int[] res = new int[k];
            int idx = 0;
            for (int i = freqList.length - 1; i > 0; i--) {
                for (int num : freqList[i]) {
                    res[idx++] = num;
                    if (idx == k) {
                        return res;
                    }
                }
            }
            return res;
        }

    }

}
