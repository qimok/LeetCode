/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/magic-index-lcci/
 * @since 2020-07-31
 */
public class LeetCode_08_03 {

    class Solution {

        public int findMagicIndex(int[] nums) {
            int offset = 0;
            while (offset < nums.length) {
                if (nums[offset] == offset) {
                    return offset;
                } else if (nums[offset] > offset) {
                    offset = nums[offset];
                } else {
                    offset++;
                }
            }
            return -1;
        }

    }
}
