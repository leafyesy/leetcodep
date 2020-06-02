package leetcode980;

import java.util.HashMap;

public class Leetcode973 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3}, {4, 5, 6}, {7, 8, 9}
        };
        int[][] qianzuihe = qianzuihe(arr);
        if (qianzuihe == null) return;
        for (int i = 0; i < qianzuihe.length; i++) {
            for (int j = 0; j < qianzuihe[i].length; j++) {
                System.out.println(qianzuihe[i][j]);
            }
        }
    }


    public static int subarraysDivByK(int[] A, int K) {
        int result = 0;
        int sum = 0;
        HashMap<Integer, Integer> cache = new HashMap<>();
        cache.put(0, 1);
        for (int elem : A) {
            sum += elem;
            int remainder = (sum % K + K) % K;
            int value = cache.getOrDefault(remainder, 0);
            result += value;
            cache.put(remainder, value + 1);
        }
        return result;
    }


    public static int[][] qianzuihe(int[][] arr) {
        if (arr.length <= 0 || arr[0].length <= 0) return null;
        int[][] newArr = new int[arr.length][arr[0].length];
        newArr[0][0] = arr[0][0];
        for (int zeroRIndex = 1; zeroRIndex < arr.length; zeroRIndex++) {
            newArr[zeroRIndex][0] = arr[zeroRIndex][0] + newArr[zeroRIndex - 1][0];
        }
        for (int zeroCIndex = 1; zeroCIndex < arr[0].length; zeroCIndex++) {
            newArr[0][zeroCIndex] = arr[0][zeroCIndex] + newArr[0][zeroCIndex - 1];
        }
        for (int rIndex = 1; rIndex < arr.length; rIndex++) {
            for (int cIndex = 1; cIndex < arr[rIndex].length; cIndex++) {
                newArr[rIndex][cIndex] = arr[rIndex][cIndex]
                        + newArr[rIndex - 1][cIndex]
                        + newArr[rIndex][cIndex - 1]
                        - newArr[rIndex - 1][cIndex - 1];
            }
        }
        return newArr;
    }

}
