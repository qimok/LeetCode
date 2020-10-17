import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/coin-change/
 * @since 2020-10-17
 */
public class LeetCode_322 {

    class Solution {

        Map<Integer, Integer> map = new HashMap<>();

        /**
         * 递归 + 备忘录（map）
         */
        public int coinChange1(int[] coins, int amount) {
            if (map.containsKey(amount)) {
                return map.get(amount);
            }
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProblem = coinChange1(coins, amount - coin);
                if (subProblem == -1) continue;
                res = Math.min(res, subProblem + 1);
            }
            res = res == Integer.MAX_VALUE ? -1 : res;
            map.put(amount, res);
            return res;
        }

        int[] memo;

        /**
         * 递归 + 备忘录（memo）
         */
        public int coinChange2(int[] coins, int amount) {
            memo = new int[amount + 1];
            return helper(coins, amount);
        }

        private int helper(int[] coins, int amount) {
            if (amount == 0) return 0;
            if (amount < 0) return -1;
            if (memo[amount] != 0) return memo[amount];
            int res = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subProblem = helper(coins, amount - coin);
                if (subProblem == -1) continue;
                res = Math.min(res, subProblem + 1);
            }
            memo[amount] = res == Integer.MAX_VALUE ? -1 : res;
            return memo[amount];
        }

        /**
         * dp
         * <p>
         *     dp[i] 表示：当目标金额为 i 时，至少需要 dp[i] 枚 coin
         *     base case：dp[0] = 0
         *     <p>
         *         PS：为啥 dp 数组初始化为 amount + 1 呢，因为凑成 amount 金额的数最多只可能等于 amount（全用 1 元面值的），
         *             所以初始化为 amount + 1 就相当于初始化为正无穷，便于后续取最小值。
         */
        public int coinChange3(int[] coins, int amount) {
            // 数组大小为 amount + 1，初始值也为 amount + 1
            int[] dp = new int[amount + 1];
            Arrays.fill(dp, amount + 1);
            dp[0] = 0;
            // 外层 for 循环在遍历所有状态的所有取值
            // i 作为状态：表示目标金额
            for (int i = 0; i < dp.length; i++) {
                // 内层 for 循环在求所有选择的最小值
                for (int coin : coins) {
                    // 子问题无解，跳过
                    if (i - coin < 0) continue;
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
            return (dp[amount] == amount + 1) ? -1 : dp[amount];
        }

    }

}
