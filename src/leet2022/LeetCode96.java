package src.leet2022;

import java.util.Arrays;

public class LeetCode96 {

    public class TreeNode {

        public TreeNode left;

        public TreeNode right;

    }

    public static void main(String[] args) {
        int trees = new LeetCode96().numTrees(4);
        System.out.println("trees:" + trees);
    }

    private int[] arr;

    public int numTrees(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        arr = new int[n];
        Arrays.fill(arr, -1);
        return innerNumTree(n);
    }

    private int innerNumTree(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        if (arr[n - 1] != -1) {
            return arr[n - 1];
        }
        int count = 0;
        for (int i = 1; i <= n; i++) {
            count = count + (innerNumTree(i - 1) * innerNumTree(n - i));
        }
        arr[n - 1] = count;
        return count;
    }


    int[] resultArr;

    public int numTrees2(int n) {
        if (n == 0 || n == 1) return 0;
        resultArr = new int[n + 1];
        resultArr[0] = 1;
        resultArr[1] = 1;
        for (int i = 2; i <= n; i++) { // 逐步增加i
            for (int j = 1; j < i + 1; j++) {
                resultArr[i] += resultArr[j - 1] * resultArr[i - j];
            }
        }
        return resultArr[n];
    }

}
