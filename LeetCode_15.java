import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/3sum/
 * @since 2020-06-12 13:18
 */
public class LeetCode_15 {

    class Solution {

        /**
         * 排序 + 双指针
         */
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            int len = nums.length;
            if (nums == null || nums.length < 3) {
                return res;
            }
            Arrays.sort(nums);
            for (int i = 0; i < len; i++) {
                if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                    if (nums[i] > 0) {
                        break;
                    }
                    int left = i + 1, right = len - 1, target = 0 - nums[i];
                    while (left < right) {
                        if (nums[left] + nums[right] == target) {
                            res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            left++;
                            right--;
                        } else if (nums[left] + nums[right] > target) {
                            while (left < right && nums[right] == nums[right - 1]) {
                                right--;
                            }
                            right--;
                        } else {
                            while (left < right && nums[left] == nums[left + 1]) {
                                left++;
                            }
                            left++;
                        }
                    }
                }
            }
            return res;
        }

    }

}
