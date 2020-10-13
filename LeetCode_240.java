/**
 * @author qimok
 * @description 题目链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/
 * @since 2020-10-13
 */
public class LeetCode_240 {

    class Solution {

        /**
         * 暴力法
         */
        public boolean searchMatrix1(int[][] matrix, int target) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] == target) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * 二分查找法
         */
        public boolean searchMatrix2(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                return false;
            }
            int shortDim = Math.min(matrix.length, matrix[0].length);
            for (int i = 0; i < shortDim; i++) {
                boolean verticalFound = binarySearch(matrix, i, target, true);
                boolean rowFound = binarySearch(matrix, i, target, false);
                if (verticalFound || rowFound) {
                    return true;
                }
            }
            return false;
        }

        private boolean binarySearch(int[][] matrix, int start, int target, boolean vertical) {
            int lo = start;
            int hi = vertical ? matrix[0].length - 1 : matrix.length - 1;
            while (hi >= lo) {
                int mid = lo + (hi - lo) / 2;
                if (vertical) {
                    if (matrix[start][mid] < target) {
                        lo = mid + 1;
                    } else if (matrix[start][mid] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                } else {
                    if (matrix[mid][start] < target) {
                        lo = mid + 1;
                    } else if (matrix[mid][start] > target) {
                        hi = mid - 1;
                    } else {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * 缩减搜索空间
         * <p>
         *     将已排序的二维矩阵划分为四个子矩阵，其中两个可能包含目标，其中两个肯定不包含
         *     算法：
         *     由于该算法是递归操作的，因此可以通过它的基本情况和递归情况的正确性来判断它的正确性
         *
         *     基本情况 ：
         *     对于已排序的二维数组，有两种方法可以确定一个任意元素目标是否可以用常数时间判断
         *     第一，如果数组的区域为零，则它不包含元素，因此不能包含目标。其次，如果目标小于数组的最小值或大于数组的最大值，那么矩阵肯定不包含目标值
         *
         *     递归情况：
         *     如果目标值包含在数组内，因此沿着索引行的矩阵中间列 ，matrix[row-1][mid] < target < matrix[row][mid]（很明显，如果找到 target ，立即返回 true）
         *     现有的矩阵可以围绕这个索引分为四个子矩阵；左上和右下子矩阵不能包含目标（通过基本情况部分来判断），所以可以从搜索空间中删除它们
         *     另外，左下角和右上角的子矩阵是二维矩阵，因此可以递归地将此算法应用于它们
         */
        public boolean searchMatrix3(int[][] matrix, int target) {
            if (matrix == null || matrix.length == 0) {
                // 如果数组的区域为零，则它不包含元素
                return false;
            }
            return searchRec(matrix, target, 0, 0, matrix[0].length - 1, matrix.length - 1);
        }

        private boolean searchRec(int[][] matrix, int target, int left, int up, int right, int down) {
            if (left > right || up > down) {
                // 如果位置坐标超过了限制，那么矩阵肯定不包含目标值
                return false;
            } else if (target < matrix[up][left] || target > matrix[down][right]) {
                // 如果目标小于数组的最小值或大于数组的最大值，那么矩阵肯定不包含目标值
                return false;
            }
            int mid = left + (right - left) / 2;
            int row = up;
            while (row <= down && matrix[row][mid] <= target) {
                if (matrix[row][mid] == target) {
                    return true;
                }
                row++;
            }
            // 如果中间索引列的 matrix[row][mid] 大于 target，则 target 存在也只可能存在于以当前 matrix[row][mid] 为分界点的`左下`和`右上`区域
            return searchRec(matrix, target, left, row, mid - 1, down) || searchRec(matrix, target, mid + 1, up, right, row - 1);
        }

        /**
         * 算法：
         * <p>
         *     首先，初始化一个指向矩阵左下角的（row，col）指针。然后，直到找到目标并返回 true（或者指针指向矩阵维度之外的（row，col）为止，执行以下操作：
         *     如果当前指向的值大于目标值，则可以`向上`移动一行
         *     如果当前指向的值小于目标值，则可以`向右`移动一列
         * 解释：
         * <p>
         *     因为行是从左到右排序的，所以当前值右侧的每个值都较大。
         *     因此，如果当前值已经大于目标值，它右边的每个值会比较大。也可以对列进行非常类似的论证，因此这种搜索方式将始终在矩阵中找到目标（如果存在）。
         */
        public boolean searchMatrix4(int[][] matrix, int target) {
            // 从矩阵的左下角开始
            int row = matrix.length - 1;
            int col = 0;
            while (row >= 0 && col < matrix[0].length) {
                if (matrix[row][col] > target) {
                    // 如果当前指向的值大于目标值，则可以`向上`移动一行
                    row--;
                } else if (matrix[row][col] < target) {
                    // 如果当前指向的值小于目标值，则可以`向右`移动一列
                    col++;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}
