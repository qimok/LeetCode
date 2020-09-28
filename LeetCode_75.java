/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/sort-colors/
 * @since 2020-09-28
 */
public class LeetCode_75 {

    /**
     * 初始化0的最右边界：p0 = 0。在整个算法执行过程中 nums[idx < p0] = 0
     * 初始化2的最左边界 ：p2 = n - 1。在整个算法执行过程中 nums[idx > p2] = 2
     * 初始化当前考虑的元素序号 ：curr = 0
     *
     * While curr <= p2:
     *     若 nums[curr] = 0 ：交换第 curr个 和 第p0个 元素，并将指针都向右移
     *     若 nums[curr] = 2 ：交换第 curr个和第 p2个元素，并将 p2指针左移
     *     若 nums[curr] = 1 ：将指针curr右移
     */
    public void sortColors(int[] nums) {
        int p0 = 0, curr = 0, p2 = nums.length - 1;
        int temp;
        while (curr <= p2) {
            if (nums[curr] == 0) {
                // 交换第 p0个和第curr个元素
                // i++，j++
                temp = nums[curr];
                nums[curr++] = nums[p0];
                nums[p0++] = temp;
            } else if (nums[curr] == 2) {
                // 交换第k个和第curr个元素
                // p2--
                temp = nums[curr];
                nums[curr] = nums[p2];
                nums[p2--] = temp;
            } else {
                curr++;
            }
        }

    }

}
