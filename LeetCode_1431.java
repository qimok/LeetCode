import java.util.ArrayList;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/kids-with-the-greatest-number-of-candies/
 * @since 2020-06-01 13:39
 */
public class LeetCode_1431 {

    class Solution {

        /**
         * 枚举
         */
        public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
            int maxCandyNum = 0;
            int len = candies.length;
            for (int i = 0; i < len; i++) {
                maxCandyNum = Math.max(maxCandyNum, candies[i]);
            }
            List<Boolean> res = new ArrayList<>();
            for (int i = 0; i < len; i++) {
                res.add(candies[i] + extraCandies >= maxCandyNum);
            }
            return res;
        }

    }

}
