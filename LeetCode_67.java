/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/add-binary/
 * @since 2020-06-23 18:35
 */
public class LeetCode_67 {

    class Solution {

        /**
         * 在进行计算时直接拼接字符串，得到一个反向字符串，最后进行翻转
         */
        public String addBinary(String a, String b) {
            StringBuilder ans = new StringBuilder();
            int ca = 0;
            for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
                int sum = ca;
                sum += i >= 0 ? a.charAt(i) - '0' : 0;
                sum += j >= 0 ? b.charAt(j) - '0' : 0;
                ans.append(sum % 2); // 进位取 0，不进位取 1
                ca = sum / 2; // 进位取 1，不进位取 0
            }
            ans.append(ca == 1 ? ca : "");
            return ans.reverse().toString();
        }

    }

}
