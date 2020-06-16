package src.interview1300;

import java.util.Arrays;

public class Interview1300 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = new int[]{4, 9, 3};
        int bestValue = s.findBestValue(arr, 10);
        System.out.println("baseValue:" + bestValue);
    }


    static class Solution {
        public static int findBestValue(int[] arr, int target) {
            return findBestValue1(arr, target);
        }

        private static int findBestValue1(int[] arr, int target) {
            int total = 0;
            int length = arr.length;
            Arrays.sort(arr);
            for (int i = 0; i < length; i++) {
                int temp = total + arr[i] * (length - i);
                int remainderLength = length - i;
                if (temp > target) {
                    int diff = target - total;
                    int c = diff / remainderLength;
                    int y = diff % remainderLength;
                    return c + (y <= (remainderLength >> 1) ? 0 : 1);
                }
                total = total + arr[i];
            }
            return arr[arr.length - 1];
        }

//        private static int findBestValue2(int[] arr, int target) {
//            Arrays.sort(arr);//排序
//            int length = arr.length;
//            int[][] qzh = new int[2][length];
//            qzh[0][0] = arr[0];
//            qzh[1][0] = qzh[0][0] * length;
//            for (int i = 1; i < length; i++) {
//                qzh[0][i] = qzh[0][i - 1] + arr[i];
//                qzh[1][i] = qzh[0][i - 1] + arr[i] * (length - i);
//            }
//            if (qzh[1][0] >= target) {
//                int d = target / length;
//                int c = target % length;
//                return c <= length >> 2 ? d : d + 1;
//            }
//            if (qzh[1][length - 1] < target) {
//                return arr[length - 1];
//            }
//            //根据前缀和寻找位置
//            int index = seach(qzh[1], 0, length - 1, target);
//        }

        private static int seach(int[] arr, int left, int right, int target) {
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (target == arr[mid]) {
                    return mid;
                } else if (target < arr[mid]) {
                    if (mid == 0) {
                        return mid;
                    }
                    if (target > arr[mid - 1]) {
                        return mid - 1;
                    }
                    left = mid + 1;
                } else {
                    if (mid == arr.length - 1) {
                        return mid;
                    }
                    if (target < arr[mid + 1]) {
                        return mid;
                    }
                    right = mid - 1;
                }
                return seach(arr, left, right, target);
            }
            return 0;
        }
    }
}
