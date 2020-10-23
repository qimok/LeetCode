import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/path-sum-iii/
 * @since 2020-10-23
 */
public class LeetCode_437 {

    public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
    }

    class Solution {

        /**
         * 前缀和 + 递归 + 回溯
         * <p>
         *     O --
         *        \
         *         \
         *          \
         *           \
         *         A --  sum（前缀和） = currSum - target（前缀和） -- 起点
         *             \
         *              \       target
         *               \
         *                \
         *             B  --   currSum（前缀和）-- 当前节点
         *     题目要求：只能从父节点到子节点
         *     即从根往任一节点的路径上(不走回头路)，有且仅有一条路径，因为不存在环（如果存在环，前缀和就不能用了，需要改造算法）
         */
        public int pathSum(TreeNode root, int sum) {
            // key 是前缀和, value 是大小为 key 的前缀和出现的次数
            Map<Integer, Integer> prefixSumCount = new HashMap<>();
            // 前缀和为 0 的一条路径
            prefixSumCount.put(0, 1);
            // 前缀和的递归回溯思路
            return recursionPathSum(root, prefixSumCount, sum, 0);
        }

        private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
            if (node == null) {
                // 递归终止条件
                return 0;
            }
            // 本层要做的事情
            int res = 0;
            // 当前路径上的和
            currSum += node.val;
            /**
             * 看看 O 节点 -> 当前节点 这条路上是否存在节点前缀和加 target 为 currSum 的路径
             * 当前节点 -> O 节点反推，有且仅有一条路径，如果此前有前缀和为 currSum - target，而当前的和又为 currSum，两者的差就肯定为 target
             * currSum - target 相当于找路径的 起点，起点 的 sum + target = currSum，当前节点 到 起点 的距离就是target
             */
            res += prefixSumCount.getOrDefault(currSum - target, 0);
            // 更新路径上当前节点前缀和的个数
            prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
            // 进入下一层
            res += recursionPathSum(node.left, prefixSumCount, target, currSum);
            res += recursionPathSum(node.right, prefixSumCount, target, currSum);
            // 回到本层，恢复状态，去除当前节点的前缀和数量
            prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
            return res;
        }

    }

}
