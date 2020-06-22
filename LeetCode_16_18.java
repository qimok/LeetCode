/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/pattern-matching-lcci/
 * @since 2020-06-22 13:48
 */
public class LeetCode_16_18 {

    class Solution {

        int aCount;
        int bCount;

        /**
         * 分情况讨论
         */
        public boolean patternMatching(String pattern, String value) {
            int patLen = pattern.length();
            int valLen = value.length();
            // 1、pattern 为""
            if (patLen == 0) {
                return valLen == 0;
            }
            // 2、记录 pattern 中 a、b 的数量
            for (int i = 0; i < patLen; i++) {
                if (pattern.charAt(i) == 'a') {
                    aCount++;
                } else {
                    bCount++;
                }
            }
            // 3、pattern 不为""，value 为 ""，判断 pattern 是否为一种字符组成
            if (valLen == 0) {
                return aCount == 0 || bCount == 0;
            }
            // 4、value 不为 ""，处理 pattern 只有一种字符串的情况
            if (aCount == 0) {
                return helper(value, bCount);
            } else if (bCount == 0) {
                return helper(value, aCount);
            }
            // 5、value 不为 ""，处理 pattern 中 'a' 或 'b' 可为 "" 的情况
            if (helper(value, aCount)) {
                return true;
            }
            if (helper(value, bCount)) {
                return true;
            }
            // 6、value 不为 ""，枚举 'a'、'b' 所代表的字符串长度
            // 使得 aCount * aLen + bCount * bLen = valLen
            for (int aLen = 1; aLen * aCount <= valLen - bCount; aLen++) {
                // valLen 减去所有 a 代表的字符串，个数不够为 bCount 的倍数
                if ((valLen - aLen * aCount) % bCount != 0) {
                    continue;
                }
                // 通过 aLen 计算 bLen
                int bLen = (valLen - aLen * aCount) / bCount;
                if (check(pattern, value, aLen, bLen)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * pattern 不为空，value 不为空. 判断是否可以 k 次切分 value
         */
        private boolean helper(String val, int k) {
            int len = val.length();
            if (len % k != 0) {
                return false;
            }
            int perStrLen = len / k;
            for (int i = perStrLen; i < len; i += perStrLen) {
                if (!val.substring(i, i + perStrLen).equals(val.substring(0, perStrLen))) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 字符串匹配映射
         */
        private boolean check(String pattern, String value, int aLen, int bLen) {
            String a = null;
            String b = null;
            for (int i = 0, j = 0; i < pattern.length(); i++) {
                // j 每次移动 aLen 或 bLen 的长度
                if (pattern.charAt(i) == 'a') {
                    if (a == null) {
                        a = value.substring(j, j + aLen);
                    } else if (!value.substring(j, j + aLen).equals(a)) {
                        return false;
                    }
                    j += aLen;
                } else {
                    if (b == null) {
                        b = value.substring(j, j + bLen);
                    } else if (!value.substring(j, j + bLen).equals(b)) {
                        return false;
                    }
                    j += bLen;
                }
            }
            return true;
        }

    }

}
