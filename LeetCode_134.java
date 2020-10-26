/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/gas-station/
 * @since 2020-10-26
 */
public class LeetCode_134 {

    class Solution {

        /**
         * 贪心算法
         */
        public int canCompleteCircuit(int[] gas, int[] cost) {
            // 当前车里剩余的油量
            int currSum = 0;
            // 总的剩余的可用油量（跟车里的剩余油量没有关系，目的：用于最后判断所有加油站的总油量是否大于等于总的花费在路上的油量，大于等于才有解，否则返回 - 1）
            int total = 0;
            // 出发的加油站编号
            int start = 0;
            for (int i = 0; i < gas.length; i++) {
                total += gas[i] - cost[i];
                if (currSum < 0) {
                    // 当前车内剩余的油量小于 0，则代表从 start 出发，不能支撑走到加油站 i
                    /**
                     * 定义：第一个加油站：加油站1；第二个加油站：加油站2；第三个加油站：加油站3...
                     * 问题：假如初次走到这个代码判断（假定 i > 2，当前加油站：加油站i，状态：未加油），为什么不是在 加油站1 的下一个加油站，即 加油站2 开始，而是从 加油站i 开始呢？
                     * 回答：既然从 加油站1 开始能够走到 加油站i，那么从 加油站1 开始走到 加油站2 剩余的油量肯定大于等于 0，而从 加油站2 走的话，车的初始油量是 0，
                     *      故从 加油站2 开始走的话，铁定无法走到 当前加油站 i，故此时从 加油站i 开始走即可
                     */
                    // 更新出发的加油站编号
                    start = i;
                    // 重新计算走到下一个加油站后的剩余油量
                    currSum = gas[i] - cost[i];
                } else {
                    currSum += gas[i] - cost[i];
                }
            }
            return total >= 0 ? start : -1;
        }

    }

}
