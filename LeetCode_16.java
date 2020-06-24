import java.util.Arrays;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/3sum-closest/
 * @since 2020-06-24 13:02
 */
public class LeetCode_16 {

    class Solution {

        /**
         * 双指针
         */
        public int threeSumClosest1(int[] nums, int target) {
            Arrays.sort(nums);
            int result = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left != right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                    if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
            return result;
        }

        /**
         * 双指针 + 优化重复元素
         */
        public int threeSumClosest2(int[] nums, int target) {
            Arrays.sort(nums);
            int result = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left != right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                    if (sum > target) {
                        // 解决nums[right]重复
                        right--;
                        while (left != right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else {
                        left++;
                        // 解决nums[left]重复
                        while (left != right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    }
                }
                // 解决nums[i]重复
                while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            return result;
        }

        /**
         * 双指针 + 优化界限 + 优化重复元素
         */
        public int threeSumClosest3(int[] nums, int target) {
            Arrays.sort(nums);
            int result = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left != right) {
                    // 如果 target 的值比 nums[i] + nums[left] + nums[left + 1] 的值还小，那么双指针无论怎么取，最后都会取到 nums[i] + nums[left] + nums[left + 1]
                    // 同理可证 target 的值比nums[i] + nums[right] + nums[right - 1] 的值还大的情况
                    // 所以可以增加一个判断，满足条件的情况下就可以直接取值，而不需要双指针一步步的判断来进行取值，减少了双指针的移动
                    int min = nums[i] + nums[left] + nums[left + 1];
                    if (target < min) {
                        if (Math.abs(min - target) < Math.abs(result - target)) {
                            result = min;
                            break;
                        }
                    }
                    int max = nums[i] + nums[right] + nums[right - 1];
                    if (target > max) {
                        if (Math.abs(max - target) < Math.abs(result - target)) {
                            result = max;
                            break;
                        }
                    }
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == target) {
                        return sum;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                    if (sum > target) {
                        // 解决nums[right]重复
                        right--;
                        while (left != right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else {
                        left++;
                        // 解决nums[left]重复
                        while (left != right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                    }
                }
                // 解决nums[i]重复
                while (i < nums.length - 2 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
            return result;
        }

    }

}
