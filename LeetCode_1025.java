/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/divisor-game/
 * @since 2020-07-24
 */
public class LeetCode_1025 {

    class Solution {

        /**
         * 数学
         * <p>
         *     若 N 为奇数，则可以整除的为奇数，若可以整除，Alice 先手减去奇数，得到偶数，则 Bob 只需每次减一直到 2，Bob 胜；Alice 为奇数不能整除，则需每次减 1，Bob 先得到 2，Bob 胜，所以奇数的话 Alice 输
         *     若 N 为偶数，则其可以整除的为奇数或偶数，为保证胜利，Alice 只需每次减 1 先得到 2 即可。如果 Alice 减去 1 得到奇数，由规则 1 可知，奇数的话先手会输（此时 Bob 先手），所以偶数的话 Alice 会赢
         */
        public boolean divisorGame1(int N) {
            return N % 2 == 0;
        }

        /**
         * dp
         * <p>
         *    dp[] 代表一个长度为 n + 1 的数组，如果 dp[n - x] 为 false，则 Alice 会减去 x，即 Bob == dp[n - x] == false，Alice 胜；
         *    否则 Alice 输，因为不管 x 是多少，dp[n - x] 为 true, 则 dp[n] == Alice == false
         */
        public boolean divisorGame2(int N) {
            if (N == 1) {
                return false;
            }
            if (N == 2) {
                return true;
            }
            boolean[] dp = new boolean[N + 1];
            dp[1] = false;
            dp[2] = true;
            for (int i = 3; i <= N; i++) {
                dp[i] = false;
                for (int j = 1; j < i; j++) {
                    if (i % j == 0 && !dp[i - j]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[N];
        }

    }

}
