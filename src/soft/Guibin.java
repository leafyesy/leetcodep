package src.soft;

import src.utils.Utils;

/**
 * 归并排序,使用递归的方法进行排序
 * 时间复杂度为O(nlogn)
 * 空间复杂度为O(n+logn)
 * 属于稳定算法
 */
public class Guibin {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 7, 89, 1, 3, 6, 4, 2, 5, 76};
        Utils.printIntArr(arr);
        soft(arr, 0, arr.length - 1);
        Utils.printIntArr(arr);
    }


    public static void soft(int[] arr, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            soft(arr, low, mid);
            soft(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int lowIndex = low;
        int highIndex = mid + 1;
        int tempIndex = 0;
        //把数据组合存入临时变量中
        while (lowIndex <= mid || highIndex <= high) {
            if (lowIndex <= mid && highIndex <= high) {
                if (arr[lowIndex] < arr[highIndex]) {
                    temp[tempIndex++] = arr[lowIndex++];
                } else {
                    temp[tempIndex++] = arr[highIndex++];
                }
            } else if (lowIndex > mid) {
                temp[tempIndex++] = arr[highIndex++];
            } else {
                temp[tempIndex++] = arr[lowIndex++];
            }
        }
        int arrIndex = 0;
        for (int elem : temp) {
            arr[low + arrIndex] = elem;
            arrIndex++;
        }
    }

}
