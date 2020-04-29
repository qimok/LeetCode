/**
 * @author Xinshuai
 * @description 题目链接：https://leetcode-cn.com/problems/find-in-mountain-array/
 * @since 2020-04-29 13:02
 */
public class LeetCode_1095 {


    interface MountainArray {
        public int get(int index);
        public int length();
    }


    class MountainArrayImpl implements MountainArray {
        private int[] arr;
        private int size;

        public MountainArrayImpl(int[] arr) {
            this.arr = arr;
            this.size = this.arr.length;
        }

        @Override
        public int get(int index) {
            return this.arr[index];
        }

        @Override
        public int length() {
            return this.size;
        }

    }

    class Solution {

        /**
         * 二分查找法
         */
        public int findInMountainArray(int target, MountainArray mountainArr) {
            int size = mountainArr.length();
            // 步骤1：先找到山顶元素所在的索引
            int mountainTop = findMountainTop(mountainArr, 0, size - 1);
            // 步骤2：在前有序且升序数组中找 target 所在的索引
            int res = findFromSortedArr(mountainArr, 0, mountainTop, target);
            // 步骤3：若在 步骤2 中没找到，则再在后有序且降序数组中找 target 所在的索引
            return res != -1 ? res : findFromInversedArr(mountainArr, mountainTop + 1, size - 1, target);
        }

        private int findMountainTop(MountainArray mountainArr, int l, int r) {
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (mountainArr.get(mid) < mountainArr.get(mid + 1)) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return l;
        }

        private int findFromSortedArr(MountainArray mountainArr, int l, int r, int target) {
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (mountainArr.get(mid) < target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return mountainArr.get(l) == target ? l : -1;
        }

        private int findFromInversedArr(MountainArray mountainArr, int l, int r, int target) {
            while (l < r) {
                int mid = l + (r - l) / 2;
                if (mountainArr.get(mid) > target) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            return mountainArr.get(l) == target ? l : -1;
        }

    }

}
