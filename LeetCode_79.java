/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/word-search/
 * @since 2020-09-16
 */
public class LeetCode_79 {

    class Solution {

        // 用于剪纸
        boolean flag;

        /**
         * DFS + 回溯 + 剪纸
         */
        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0 || board[0].length == 0) {
                return false;
            }
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(char[][] board, String word, int i, int j, int curr) {
            if (curr == word.length()) {
                flag = true;
                return true;
            }
            if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(curr)) {
                return false;
            }
            if (!flag) {
                char c = board[i][j];
                board[i][j] = '.';
                boolean res1 = dfs(board, word, i - 1, j, curr + 1);
                boolean res2 = dfs(board, word, i + 1, j, curr + 1);
                boolean res3 = dfs(board, word, i, j - 1, curr + 1);
                boolean res4 = dfs(board, word, i, j + 1, curr + 1);
                // 回溯
                board[i][j] = c;
                return res1 || res2 || res3 || res4;
            } else {
                return true;
            }
        }

    }

}
