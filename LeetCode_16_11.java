/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/diving-board-lcci/submissions/
 * @since 2020-07-08 10:24
 */
public class LeetCode_16_11 {

    class Solution {

        /**
         * 数学：等差数列
         */
        public int[] divingBoard(int shorter, int longer, int k) {
            if (k == 0) {
                return new int[0];
            }
            if (shorter == longer) {
                return new int[]{shorter * k};
            }
            int[] res = new int[k + 1];
            for (int i = 0; i <= k; i++) {
                res[i] = shorter * (k - i) + longer * i;
            }
            return res;
        }

    }

}
