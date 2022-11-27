package src.leet2022;

import java.util.*;

public class LeetCode200 {


    public static void main(String[] args) {
        char[][] arr = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] arr2 = new char[][]{
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        char[][] arr3 = new char[][]{
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '1', '1', '0'},
                {'0', '0', '0', '0', '1'}
        };
        char[][] arr4 = new char[][]{
                {'1', '1', '1', '1', '1', '0', '1', '1', '1', '1'},
                {'1', '0', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '1', '1', '1', '1'},
                {'1', '1', '0', '1', '1', '0', '0', '0', '0', '1'},
                {'1', '0', '1', '0', '1', '0', '0', '1', '0', '1'},
                {'1', '0', '0', '1', '1', '1', '0', '1', '0', '0'},
                {'0', '0', '1', '0', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '0', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '0', '1'},
                {'1', '0', '1', '1', '1', '1', '1', '1', '1', '0'}
        };
        char[][] arr5 = new char[][]{
                {'1', '0', '1', '1', '0', '0', '1', '0', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1', '0'},
                {'0', '1', '0', '0', '1', '0', '1', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1'},
                {'1', '0', '0', '1', '0', '1', '0', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0'},
                {'0', '1', '1', '0', '0', '1', '1', '0', '1', '1', '1', '1', '0', '0', '1', '0', '0', '0', '1', '1'},
                {'1', '1', '0', '1', '0', '0', '1', '0', '0', '0', '1', '0', '1', '0', '1', '1', '1', '0', '1', '1'},
                {'0', '0', '0', '0', '1', '0', '1', '1', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '0', '1', '1', '1', '0', '0', '1', '0'},
                {'0', '1', '1', '0', '0', '0', '1', '0', '0', '1', '0', '1', '1', '1', '0', '0', '1', '1', '0', '1'},
                {'0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '1', '0', '1', '0', '0', '1', '0', '1', '0'},
                {'0', '0', '1', '1', '1', '0', '1', '0', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '1', '0', '1', '1', '1', '0', '1', '1', '1', '0', '1', '0', '1', '0', '1', '0', '1', '1'},
                {'0', '0', '1', '1', '1', '1', '0', '1', '1', '1', '0', '1', '0', '0', '0', '1', '1', '1', '0', '1'},
                {'1', '1', '1', '0', '0', '0', '0', '0', '1', '1', '0', '1', '1', '1', '0', '1', '1', '1', '1', '0'},
                {'0', '0', '1', '1', '1', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0'},
                {'0', '0', '0', '1', '1', '0', '0', '0', '0', '1', '1', '0', '1', '0', '0', '1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '0', '1', '0', '0', '1', '1', '1', '1', '1', '0', '1', '1', '1', '0', '0', '1'},
                {'0', '0', '0', '0', '1', '1', '1', '1', '0', '0', '0', '0', '1', '0', '0', '0', '0', '1', '1', '0'},
                {'1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0', '1', '1', '1', '1', '1', '1'},
                {'0', '1', '0', '0', '1', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '0', '1', '1', '1', '1'},
                {'0', '0', '1', '1', '1', '1', '1', '0', '0', '0', '1', '1', '1', '1', '1', '1', '0', '1', '1', '0'}
        };
        int result1 = new LeetCode200().numIslands3(arr);
        int result2 = new LeetCode200().numIslands3(arr2);
        int result3 = new LeetCode200().numIslands3(arr3);
        int result4 = new LeetCode200().numIslands3(arr4);
        int result5 = new LeetCode200().numIslands3(arr5);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    // 无法解决因为记录的岛屿的个数错误导致的重复计算
    //dp[n][m] = if(v == 0) 0 else 上/左是否有值,如果有就用那个值,如果没有就把当前岛屿数加1,如果遇到都有数字且数字不一致的 需要改成小的
    public int numIslands(char[][] grid) {
        HashMap<Integer, Integer> countRecord = new HashMap<>();
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                char c = grid[i][j];
                if (c == '0') {
                    dp[i][j] = 0;
                } else {
                    // 判断上/左 是否存在岛屿
                    int left = i == 0 ? 0 : dp[i - 1][j];
                    int top = j == 0 ? 0 : dp[i][j - 1];
                    if (left == 0 && top == 0) {
                        dp[i][j] = countRecord.size() + 1;
                        countRecord.put(dp[i][j], 1);
                    } else if (left > 0 && top > 0) {
                        if (left == top) {
                            dp[i][j] = left;
                        } else {
                            if (left < top) {
                                dp[i][j] = left;
                                countRecord.put(top, 0);
                            } else {
                                dp[i][j] = top;
                                countRecord.put(left, 0);
                            }
                        }
                    } else {
                        // 其中一个为0
                        dp[i][j] = Math.max(left, top);
                    }
                }
            }
        }
        int count = 0;
        Set<Map.Entry<Integer, Integer>> set = countRecord.entrySet();
        for (Map.Entry<Integer, Integer> next : set) {
            count += next.getValue();
        }
        return count;
    }


    public int numIslands3(char[][] grid) {
        int count = 1;
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (expendPoint(i, j, grid, dp, count)) {
                    count++;
                }
            }
        }
        return count - 1;
    }

    private boolean expendPoint(int i, int j, char[][] grid, int[][] dp, int nums) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) { // 超出数组
            return false;
        }
        if (dp[i][j] > 0) { // 已经存储过岛屿的数字
            return false;
        }
        if (grid[i][j] == '1') {
            dp[i][j] = nums;
            // 从这个点开始外扩寻找
            expendPoint(i, j - 1, grid, dp, nums); // left
            expendPoint(i - 1, j, grid, dp, nums); // top
            expendPoint(i, j + 1, grid, dp, nums); // right
            expendPoint(i + 1, j, grid, dp, nums); // bottom
            return true;
        }
        return false;
    }


}
