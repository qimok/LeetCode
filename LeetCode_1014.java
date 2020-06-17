/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/best-sightseeing-pair/
 * @since 2020-06-17 13:23
 */
public class LeetCode_1014 {

    class Solution {

        /**
         * 根据公式res = A[i] + A[j] + i - j （i < j），
         * 求每个景点 j 的时候，A[j] - j 实际上是固定的，要想让 res 最大，我们就要想办法让 A[i] + i 尽可能大
         */
        public int maxScoreSightseeingPair1(int[] A) {
            int ans = 0, mx = A[0] + 0;
            for (int i = 1; i < A.length; i++) {
                ans = Math.max(ans, mx + A[i] - i);
                mx = Math.max(mx, A[i] + i);
            }
            return ans;
        }

        /**
         * 同上，只是换种写法
         * <p>
         *     mx：max(A[i]) - 1
         *     mx + a：mx + A[j]
         */
        public int maxScoreSightseeingPair2(int[] A) {
            int ans = 0, mx = 0;
            for (int a : A) {
                ans = Math.max(ans, mx + a);
                mx = Math.max(mx, a) - 1; // 相当于 j 的坐标右移一位，根据题目要求，需要减 1
            }
            return ans;
        }

    }

}
