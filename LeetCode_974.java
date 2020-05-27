import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/
 * @since 2020-05-27 23:23
 */
public class LeetCode_974 {

    class Solution {

        /**
         * 前缀和 + Map
         * <p>
         *     令 P[i] = A[0] + A[1] + ... + A[i]，
         *     那么每个连续子数组的和 sum(i,j) 就可以写成 P[j] - P[i-1]（其中 0 < i < j）的形式
         *     此时，判断子数组的和能否被 K 整除就等价于判断 (P[j] - P[i-1]) mod K == 0
         *     根据 同余定理，只要 P[j] mod K == P[i-1] mod K，就可以保证上面的等式成立
         */
        public int subarraysDivByK(int[] A, int K) {
            // 前缀和模 K 的值为键，出现次数为值的哈希表
            Map<Integer, Integer> record = new HashMap<>();
            // 初始化，前缀和本身被 K 整除的情况
            record.put(0, 1);
            int sum = 0, ans = 0;
            for (int elem : A) {
                sum += elem;
                // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
                int modulo = (sum % K + K) % K;
                int count = record.getOrDefault(modulo, 0);
                ans += count;
                record.put(modulo, count + 1);
            }
            return ans;
        }

    }

}
