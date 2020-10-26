import java.util.HashMap;
import java.util.Map;

/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * @since 2020-05-23 15:21
 */
public class LeetCode_105 {

    class Solution {

        /**
         * 递归
         */
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            int preLen = preorder.length;
            int inLen = inorder.length;
            if (preLen != inLen) {
                throw new RuntimeException("Incorrect input data.");
            }
            Map<Integer, Integer> map = new HashMap<>(preLen);
            for (int i = 0; i < inLen; i++) {
                map.put(inorder[i], i);
            }
            return buildTree(preorder, 0, preLen - 1, map, 0, inLen - 1);
        }

        /**
         * [ 根节点,                       [左子树的前序遍历结果],                       [右子树的前序遍历结果] ]
         * preLeft                  preLeft + 1   pIndex - inLeft + preLeft  pIndex - inLeft + preLeft + 1      preRight
         *
         * [ [左子树的中序遍历结果],                       根节点,                       [右子树的中序遍历结果] ]
         * inLeft         pIndex - 1                    pIndex                     pIndex + 1         inRight
         *
         * 假如左子树序列的右边界为x，得 x - (preLeft + 1) = pIndex - 1 - inLeft，得 x = pIndex - inLeft + preLeft
         *
         * @param preorder 前序遍历序列
         * @param preLeft  前序遍历序列子区间的左边界，可以取到
         * @param preRight 前序遍历序列子区间的右边界，可以取到
         * @param map      在中序遍历序列里，数值与下标的对应关系
         * @param inLeft   中序遍历序列子区间的左边界，可以取到
         * @param inRight  中学遍历序列子区间的右边界，可以取到
         * @return
         */
        private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                                   Map<Integer, Integer> map, int inLeft, int inRight) {
            if (preLeft > preRight || inLeft > inRight) {
                return null;
            }
            int rootVal = preorder[preLeft];
            TreeNode root = new TreeNode(rootVal);
            int pIndex = map.get(rootVal);
            root.left = buildTree(preorder, preLeft + 1, pIndex - inLeft + preLeft,
                    map, inLeft, pIndex - 1);
            root.right = buildTree(preorder, pIndex - inLeft + preLeft + 1, preRight,
                    map, pIndex + 1, inRight);
            return root;
        }

    }

}
