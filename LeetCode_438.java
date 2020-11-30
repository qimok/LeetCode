/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 * @since 2020-07-22
 */
public class LeetCode_11 {

    class Solution {

        /**
         * 二分查找
         * <p>
         *     思考： 是否可以用 numbers[m] 和 numbers[i] 比较做代替？
         *     解析： 不可以。因为做比较的目的是判断 m 在哪个排序数组中。但在 numbers[m] > numbers[i] 情况下，无法判断 m 在哪个排序数组中。
         *     本质是因为 j 初始值肯定在右排序数组中； i 初始值无法确定在哪个排序数组中。
         *     示例： 当 i = 0, j = 4, m = 2 时，有 numbers[m] > numbers[i] ，以下两示例得出不同结果。
         *     numbers = [1, 2, 3, 4 ,5] 旋转点 x = 0 ： m 在右排序数组（此示例只有右排序数组）；
         *     numbers = [3, 4, 5, 1 ,2] 旋转点 x = 3 ： m 在左排序数组
         */
        public int minArray(int[] numbers) {
            int i = 0, j = numbers.length - 1;
            while (i < j) {
                int m = i + (j - i) / 2;
                if (numbers[m] > numbers[j]) {
                    i = m + 1;
                } else if (numbers[m] < numbers[j]) {
                    j = m;
                } else {
                    j--;
                }
            }
            return numbers[i]; // 返回 i, j 都可以
        }

    }

}
