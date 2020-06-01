package src.leetcode1140;

public class Leetcode1139 {

    class Solution {
        public int largest1BorderedSquare(int[][] grid) {
            if (grid.length == 1) {
                for (int row = 0; row < grid.length; row++) {
                    if (grid[0][row] == 1) return 1;
                }
                return 0;
            }
            if (grid[0].length == 1) {
                for (int[] ints : grid) {
                    if (ints[0] == 1) return 1;
                }
                return 0;
            }
            //先写一个前缀和
            int[][] hGrid = new int[grid.length][grid[0].length];
            hGrid[0][0] = grid[0][0];
            for (int row = 1; row < grid.length; row++) {
                hGrid[0][row] = hGrid[0][row - 1] + grid[0][row];
            }
            for (int cloumn = 1; cloumn < grid[0].length; cloumn++) {
                hGrid[cloumn][0] = hGrid[cloumn - 1][0] + grid[cloumn][0];
            }
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[i].length; j++) {
                    hGrid[i][j] = hGrid[i - 1][j] + hGrid[i][j - 1] + grid[i][j] - hGrid[i - 1][j - 1];
                }
            }
            printArr(hGrid);
            int max = -1;//用于记录最大边正方形的边长
            int x = -1;//用于记录最大边正方形的x
            int y = -1;//用于记录最大边正方形的y
            //根据前缀和数组进行判断正方形是否成立
            for (int row = 0; row < grid.length; row++) {
                for (int cloumn = 0; cloumn < grid[row].length; cloumn++) {
                    int length = 1;
                    int value = grid[row][cloumn];
                    if (value == 1) {
                        //从这里开始寻找
                        if (max == -1) {
                            max = 1;
                            x = cloumn;
                            y = row;
                        }
                        while (row + length < grid.length && cloumn + length < grid[row].length) {
                            if (isSqure(hGrid, row, cloumn, length)) {
                                //表示这个正方形成立
                                if (length > max) {
                                    x = cloumn;
                                    y = row;
                                    max = length;
                                }
                            }
                            length++;
                        }
                    }
                }
            }
            if (max == -1) {
                return 0;
            }
            return hGrid[x + max][y + max] - hGrid[x][y];
        }

        /**
         * 判断是否为边正方形
         */
        private boolean isSqure(int[][] hGrid, int row, int cloumn, int length) {
            int diff = hGrid[row + length - 1][cloumn + length - 1] - hGrid[row][cloumn + length] - hGrid[row + length][cloumn] + hGrid[row][cloumn];
            int target = length * length;
            return hGrid[row + length][cloumn + length] - hGrid[row - 1][cloumn] - hGrid[row][cloumn - 1] + hGrid[row - 1][cloumn - 1] - diff == target;
        }

        private void printArr(int[][] grid) {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    System.out.print("" + grid[i][j]);
                }
            }
        }
    }
}
