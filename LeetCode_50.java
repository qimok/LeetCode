/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/powx-n/
 * @since 2020-05-11 13:35
 */
public class LeetCode_50 {

    class Solution {

        /**
         * 快速幂 + 递归
         */
        public double myPow1(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul1(x, N) : 1.0 / quickMul1(x, -N);
        }

        private double quickMul1(double x, long N) {
            // 终止条件
            if (N == 0) {
                return 1.0;
            }
            // 下探
            double res = quickMul1(x, N / 2);
            // 处理当前层逻辑
            return N % 2 == 0 ? res * res : res * res * x;
        }

        /**
         * 快速幂 + 迭代
         * <p>
         *     二进制法，最低位为1的时候计算贡献，否则不计算贡献(只是将贡献的值自乘)，后舍弃最低位的1，重复此步骤
         */
        public double myPow2(double x, int n) {
            long N = n;
            return N >= 0 ? quickMul2(x, N) : 1.0 / quickMul2(x, -N);
        }

        private double quickMul2(double x, long N) {
            double ans = 1.0;
            // 贡献的初始值为 x
            double x_contribute = x;
            // 在对 N 进行二进制拆分的同时计算答案
            while (N > 0) {
                if (N % 2 == 1) {
                    // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                    ans *= x_contribute;
                }
                // 将贡献不断地平方
                x_contribute *= x_contribute;
                // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
                N /= 2;
            }
            return ans;
        }

    }

}
