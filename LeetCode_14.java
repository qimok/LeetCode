/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/longest-common-prefix/
 * @since 2020-06-15 14:36
 */
public class LeetCode_14 {

    class Solution {

        /**
         * 按字符串的字符正向循环判断
         */
        public String longestCommonPrefix1(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            String ans = strs[0];
            for (int i = 0; i < strs.length; i++) {
                int j = 0;
                for (; j < ans.length() && j < strs[i].length(); j++) {
                    if (ans.charAt(j) != strs[i].charAt(j)) {
                        break;
                    }
                }
                ans = ans.substring(0, j);
                if (ans.equals("")) {
                    return ans;
                }
            }
            return ans;
        }

        /**
         * 循环截去字符串的最后一个字符，剩下的字符串作为公共前缀来判断
         */
        public String longestCommonPrefix2(String[] strs) {
            if(strs == null || strs.length == 0) {
                return "";
            }
            for(int i = 1; i < strs.length; i++){
                // 单次求取每个元素的公共前缀，将第一个元素作为参照，循环将第一个元素长度从后缩短一个字符判断
                while(strs[i].indexOf(strs[0]) != 0){
                    /**
                     * 强调 substring 用法：
                     * <p>
                     *      public String substring(int beginIndex, int endIndex)
                     *      第一个参数 int 为开始的索引，对应 String 数字中的开始位置，
                     *      第二个参数是截止的索引位置，对应 String 中的结束位置
                     *      1、取得的字符串长度为：endIndex - beginIndex
                     *      2、从 beginIndex 开始取，到 endIndex 结束，其中不包括endIndex位置的字符
                     */
                    strs[0] = strs[0].substring(0, strs[0].length() - 1);
                    if(strs[0].isEmpty()) {
                        return "";
                    }
                }
            }
            return strs[0];
        }

    }

}
