package src.leetcode320;

import java.util.Arrays;

public class Leetcode319 {

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int result = blueSwitch2(i);
            System.out.println("i:" + i + " result:" + result);
        }
    }

    public static int blueSwitch2(int n) {
        if (n == 0) return 0;
        n = n - 1;
        int result = 1;
        while (true) {
            int size = result * 2 + 1;
            n = n - size;
            if (n < 0) {
                return result;
            }
            result++;
        }
    }

    public static int bulbSwitch(int n) {
        //n = Integer.MAX_VALUE;
        int[] arr = new int[n];
        int curIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                Arrays.fill(arr, 1);
                continue;
            }
            curIndex = i;
            while (curIndex < arr.length) {
                arr[curIndex] = (arr[curIndex] + 1) & 1;
                curIndex = curIndex + i + 1;
            }
        }
        //计算个数
        int count = 0;
        for (int i : arr) {
            if (i == 1) count++;
        }
        return count;
    }

}
