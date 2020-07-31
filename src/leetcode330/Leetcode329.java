package src.leetcode330;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Leetcode329 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
                {19, 18, 17, 16, 15, 14, 13, 12, 11, 10},
                {20, 21, 22, 23, 24, 25, 26, 27, 28, 29},
                {39, 38, 37, 36, 35, 34, 33, 32, 31, 30},
                {40, 41, 42, 43, 44, 45, 46, 47, 48, 49},
                {59, 58, 57, 56, 55, 54, 53, 52, 51, 50},
                {60, 61, 62, 63, 64, 65, 66, 67, 68, 69},
                {79, 78, 77, 76, 75, 74, 73, 72, 71, 70},
                {80, 81, 82, 83, 84, 85, 86, 87, 88, 89},
                {99, 98, 97, 96, 95, 94, 93, 92, 91, 90},
                {100, 101, 102, 103, 104, 105, 106, 107, 108, 109},
                {119, 118, 117, 116, 115, 114, 113, 112, 111, 110},
                {120, 121, 122, 123, 124, 125, 126, 127, 128, 129},
                {139, 138, 137, 136, 135, 134, 133, 132, 131, 130},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
        Solution solution = new Solution();

        int result = solution.longestIncreasingPath(arr);
        System.out.println("result:" + result);
    }

    static class Solution {
        /*
        [[10,6,3,5,7,9,5],[2,3,5,7,3,2,7],[2,4,8,3,2,6,3],[5,6,7,4,3,5,7]]

        [[0,1,2,3,4,5,6,7,8,9],
        [19,18,17,16,15,14,13,12,11,10],
        [20,21,22,23,24,25,26,27,28,29],
        [39,38,37,36,35,34,33,32,31,30],
        [40,41,42,43,44,45,46,47,48,49],
        [59,58,57,56,55,54,53,52,51,50],
        [60,61,62,63,64,65,66,67,68,69],
        [79,78,77,76,75,74,73,72,71,70],
        [80,81,82,83,84,85,86,87,88,89],
        [99,98,97,96,95,94,93,92,91,90],
        [100,101,102,103,104,105,106,107,108,109],
        [119,118,117,116,115,114,113,112,111,110],
        [120,121,122,123,124,125,126,127,128,129],
        [139,138,137,136,135,134,133,132,131,130],
        [0,0,0,0,0,0,0,0,0,0]]
         */


        public int longestIncreasingPath(int[][] matrix) {
            if (matrix == null) {
                return 0;
            }
            if (matrix.length == 0) {
                return 0;
            }
            Real[][] arr = new Real[matrix.length][matrix[0].length];
            int result = 0;
            Integer[][] dp = new Integer[matrix.length][matrix[0].length];
            calFromLocation(arr, matrix);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    int cal = cal(arr, dp, i, j, dp[i][j] == null ? 1 : dp[i][j]);
                    result = Math.max(result, cal);
                }
            }
            return result;
        }

        private void calFromLocation(Real[][] arr, int[][] matrix) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    Real real = arr[i][j];
                    if (real == null) {
                        real = new Real();
                    }
                    if (i > 0) {
                        if (matrix[i - 1][j] > matrix[i][j]) {
                            real.add(i - 1, j);
                        }
                    }
                    if (j > 0) {
                        if (matrix[i][j - 1] > matrix[i][j]) {
                            real.add(i, j - 1);
                        }
                    }
                    //向下和向右查看是否减少
                    if (i < matrix.length - 1) {
                        if (matrix[i + 1][j] > matrix[i][j]) {
                            real.add(i + 1, j);
                        }
                    }
                    if (j < matrix[i].length - 1) {
                        if (matrix[i][j + 1] > matrix[i][j]) {
                            real.add(i, j + 1);
                        }
                    }
                    //ystem.out.println("real:" + "i:" + i + " j:" + j + ":" + real);
                    arr[i][j] = real;
                }
            }
        }

        private int cal(Real[][] arr, Integer[][] dp, int startX, int startY, int count) {
            int max = count;
            Real cur = arr[startX][startY];
            if (cur != null) {
                for (Point point : cur.pointList) {
                    System.out.println("x:" + point.x + " y:" + point.y);
                    if (dp[point.x][point.y] != null) {
                        max = Math.max(max, dp[point.x][point.y] + 1);
                    } else {
                        int cal = cal(arr, dp, point.x, point.y, count + 1);
                        max = Math.max(max, cal);
                        dp[point.x][point.y] = cal;
                    }
                    //max = Math.max(max, cal(arr, dp, point.x, point.y, count + 1));
                }
            }
            dp[startX][startY] = max;
            System.out.println("x:" + startX + " y:" + startY + " v:" + dp[startX][startY]);
            return max;
        }

        class Real {
            List<Point> pointList = new ArrayList<>();

            void add(int x, int y) {
                pointList.add(new Point(x, y));
            }

            @Override
            public String toString() {
                return "Real{" +
                        "pointList=" + pointList +
                        '}';
            }
        }

        class Point {
            int x;
            int y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Point point = (Point) o;
                return x == point.x &&
                        y == point.y;
            }

            @Override
            public int hashCode() {
                return Objects.hash(x, y);
            }

            @Override
            public String toString() {
                return "Point{" +
                        "x=" + x +
                        ", y=" + y +
                        '}';
            }
        }

    }
}