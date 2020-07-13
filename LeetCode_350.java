import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * @since 2020-07-13 16:50
 */
public class LeetCode_350 {

    class Solution {

        /**
         * 双指针
         */
        public int[] intersect1(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            Arrays.sort(nums2);
            int len1 = nums1.length;
            int len2 = nums2.length;
            int[] res = new int[Math.min(nums1.length, nums2.length)];
            int index1 = 0, index2 = 0, index = 0;
            while (index1 < len1 && index2 < len2) {
                if (nums1[index1] < nums2[index2]) {
                    index1++;
                } else if (nums1[index1] > nums2[index2]) {
                    index2++;
                } else {
                    res[index++] = nums1[index1];
                    index1++;
                    index2++;
                }
            }
            return Arrays.copyOfRange(res, 0, index);
        }

        /**
         * 哈希表
         * <p>
         *     首先遍历第一个数组，并在哈希表中记录第一个数组中的每个数字以及对应出现的次数，
         *     然后遍历第二个数组，对于第二个数组中的每个数字，
         *     如果在哈希表中存在这个数字，则将该数字添加到答案，并减少哈希表中该数字出现的次数
         *
         *     为了降低空间复杂度，首先遍历较短的数组并在哈希表中记录每个数字以及对应出现的次数，然后遍历较长的数组得到交集
         */
        public int[] intersect2(int[] nums1, int[] nums2) {
            if (nums1.length > nums2.length) {
                return intersect2(nums2, nums1);
            }
            Map<Integer, Integer> map = new HashMap<>();
            for (int num : nums1) {
                int count = map.getOrDefault(num, 0) + 1;
                map.put(num, count);
            }
            int[] res = new int[nums1.length];
            int index = 0;
            for (int num : nums2) {
                int count = map.getOrDefault(num, 0);
                if (count > 0) {
                    res[index++] = num;
                    count--;
                    if (count > 0) {
                        map.put(num, count);
                    } else {
                        map.remove(num);
                    }
                }
            }
            return Arrays.copyOfRange(res, 0, index);
        }

    }

}
