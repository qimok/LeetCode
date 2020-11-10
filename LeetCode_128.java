import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-consecutive-sequence/
 * @since 2020-06-07 11:19
 */
public class LeetCode_128 {

    class Solution {

        /**
         * HashSet
         */
        public int longestConsecutive1(int[] nums) {
            Set<Integer> numSet = new HashSet<>();
            for (int num : nums) {
                numSet.add(num);
            }
            int ans = 0;
            for (int num : nums) {
                // 只枚举假定最长序列的初始元素
                // 由于要枚举的数 x 一定是在数组中不存在前驱数 x−1 的，不然按照上面的分析我们会从 x−1 开始尝试匹配，因此我们每次在哈希表中检查是否存在 x−1 即能判断是否需要跳过了
                if (!numSet.contains(num - 1)) {
                    int currNum = num;
                    int currLen = 1;
                    while (numSet.contains(currNum + 1)) {
                        currNum += 1;
                        currLen += 1;
                    }
                    ans = Math.max(ans, currLen);
                }
            }
            return ans;
        }

        Set<Integer> checkInNums = new HashSet<>(); // 初始化 Set 用来判断数据是否在 nums 中
        Map<Integer, Integer> map = new HashMap<>(); // 备忘录（key：当前数，value：小于等于当前数的最大连续序列的长度）

        /**
         * dfs
         */
        public int longestConsecutive2(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            for (int num : nums) {
                checkInNums.add(num);
            }
            int ans = 0;
            for (int num : nums) {
                ans = Math.max(ans, dfs(num));
            }
            return ans;
        }

        // 返回小于等于当前数的最大连续序列的长度
        public int dfs(int currNum) {
            if (map.containsKey(currNum)) {
                // 返回等于当前数的最大连续序列的长度
                return map.get(currNum);
            }
            int len = 1; // 初始长度为 1
            if (checkInNums.contains(currNum - 1)) {
                len += dfs(currNum - 1);
            }
            map.put(currNum, len);
            return len;
        }

        class UF {
            // 记录树的“重量”
            private int maxValue = 1;
            // 存储⼀棵树
            private Map<Integer, Integer> parent = new HashMap<>();
            private Map<Integer, Integer> size = new HashMap<>();

            public UF(int[] nums) {
                for (int i = 0; i < nums.length; i++) {
                    parent.put(nums[i], nums[i]);
                    size.put(nums[i], 1);
                }
            }

            public void union(int p, int q) {
                if(!checkInNums.contains(p) || !checkInNums.contains(q)) {
                    return;
                }
                int rootP = find(p);
                int rootQ = find(q);
                if (rootP == rootQ) {
                    return;
                }
                // ⼩树接到⼤树下⾯，较平衡
                if (size.get(rootP) > size.get(rootQ)) {
                    // 小树链接到大树上
                    parent.put(rootQ, rootP);
                    size.put(rootP, size.get(rootP) + size.get(rootQ));
                    maxValue = Math.max(maxValue, size.get(rootP));
                } else {
                    // 小树链接到大树上
                    parent.put(rootP, rootQ);
                    size.put(rootQ, size.get(rootP) + size.get(rootQ));
                    maxValue = Math.max(maxValue, size.get(rootQ));
                }
            }

            private int find(int x) {
                while (parent.get(x) != x) {
                    int tmp = parent.get(x);
                    //进⾏路径压缩
                    parent.put(x, parent.get(tmp));
                    x = tmp;
                }
                return x;
            }

            public int findMax() {
                return maxValue;
            }

        }

        /**
         * 并查集：不推荐
         */
        public int longestConsecutive3(int[] nums) {
            if(nums.length == 0) {
                return 0;
            }
            UF uf = new UF(nums);
            for(int i = 0; i < nums.length; i++) {
                checkInNums.add(nums[i]);
            }
            for(int i = 0; i < nums.length; i++) {
                uf.union(nums[i], nums[i]-1);
            }
            return uf.findMax();
        }

    }

}

