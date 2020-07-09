import java.util.*;

/**
 * @author qimok
 * @description 题目链接
 * @since 2020-07-09 19:18
 */
public class LeetCode_17_13 {

    class Solution {

        /**
         * 暴力法
         * <p>
         *     假设当前已经考虑完了前 i 个字符，对于前 i + 1 个字符对应的最少未匹配数：
         *         第 i + 1 个字符未匹配，则 dp[i + 1] = dp[i] + 1，即不匹配数加 1
         *         遍历前 i 个字符，若以其中某一个下标 idx 为开头、以第 i + 1 个字符为结尾的字符串正好在词典里，则 dp[i] = min(dp[i], dp[idx]) 更新 dp[i]
         */
        public int respace1(String[] dictionary, String sentence) {
            Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
            int len = sentence.length();
            int[] dp = new int[len + 1];
            for (int i = 1; i <= len; i++) {
                dp[i] = dp[i - 1] + 1;
                for (int idx = 0; idx < i; idx++) {
                    if (dict.contains(sentence.substring(idx, i))) {
                        dp[i] = Math.min(dp[i], dp[idx]);
                    }
                }
            }
            return dp[len];
        }

        /**
         * 字典树
         */
        public int respace2(String[] dictionary, String sentence) {
            Trie trie = new Trie();
            for (String word : dictionary) {
                trie.insert(word);
            }
            // 状态转移，dp[i] 表示字符串的前 i 个字符的最少未匹配数
            int len = sentence.length();
            int[] dp = new int[len + 1];
            for (int i = 1; i <= len; i++) {
                dp[i] = dp[i - 1] + 1;
                for (Integer idx : trie.search(sentence, i - 1)) {
                    dp[i] = Math.min(dp[i], dp[idx]);
                }
            }
            return dp[len];
        }

        class Trie {

            TrieNode root;

            public Trie() {
                root = new TrieNode();
            }

            // 将单词倒序插入字典树
            public void insert(String word) {
                TrieNode curr = root;
                for (int i = word.length() - 1; i >= 0; i--) {
                    int c = word.charAt(i) - 'a';
                    if (curr.children[c] == null) {
                        curr.children[c] = new TrieNode();
                    }
                    curr = curr.children[c];
                }
                curr.isWord = true;
            }

            // 找到 sentence 中以 endPos 为结尾的单词，返回这些单词的开头下标。
            public List<Integer> search(String sentence, int endPos) {
                List<Integer> indices = new ArrayList<>();
                TrieNode curr = root;
                for (int i = endPos; i >= 0; i--) {
                    int c = sentence.charAt(i) - 'a';
                    if (curr.children[c] == null) {
                        break;
                    }
                    curr = curr.children[c];
                    if (curr.isWord) {
                        indices.add(i);
                    }
                }
                return indices;
            }
        }

        class TrieNode {
            boolean isWord;
            TrieNode[] children = new TrieNode[26];
            public TrieNode(){}
        }

    }

}
