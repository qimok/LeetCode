import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/two-sum/submissions/
 * @since 2020-05-07 13:36
 */
public class LeetCode_1 {

    class Solution {

        /**
         * 借助Map
         */
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int other = target - nums[i];
                if (map.containsKey(other)) {
                    return new int[]{i, map.get(other)};
                }
                map.put(nums[i], i);
            }
            return null;
        }

    }

}

