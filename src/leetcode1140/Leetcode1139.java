package src.leetcode1140;

public class Leetcode1139 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] arr = new int[][]
                {{1, 1, 0}, {1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        //{{0}, {0}, {1}};
        //{{0, 0, 0, 1}};
        //{{1, 1, 1, 1}, {1, 0, 1, 1}, {1, 1, 0, 1}, {1, 1, 1, 1}};
        int result = solution.largest1BorderedSquare(arr);
        System.out.print("result:" + result);
    }

    static class Solution {

//        public int largest1BorderedSquare2(int[][] grid) {
//
//        }


        public int largest1BorderedSquare(int[][] grid) {
            if (grid.length == 1) {
                for (int c = 0; c < grid[0].length; c++) {
                    //System.out.println("c:" + c + " grid[0][c]:" + grid[0][c]);
                    if (grid[0][c] == 1) return 1;
                }
                return 0;
            }
            if (grid[0].length == 1) {
                for (int[] ints : grid) {
                    //System.out.println("ints[0]:" +ints[0]);
                    if (ints[0] == 1) return 1;
                }
                return 0;
            }
            //先写一个前缀和
            int[][] hGrid = new int[grid.length][grid[0].length];
            hGrid[0][0] = grid[0][0];
            for (int row = 1; row < grid.length; row++) {
                hGrid[row][0] = hGrid[row - 1][0] + grid[row][0];
            }
            for (int cloumn = 1; cloumn < grid[0].length; cloumn++) {
                hGrid[0][cloumn] = hGrid[0][cloumn - 1] + grid[0][cloumn];
            }
            for (int i = 1; i < grid.length; i++) {
                for (int j = 1; j < grid[i].length; j++) {
                    hGrid[i][j] = hGrid[i - 1][j] + hGrid[i][j - 1] + grid[i][j] - hGrid[i - 1][j - 1];
                }
            }
            //printArr(hGrid);
            int max = -1;//用于记录最大边正方形的边长
            int x = -1;//用于记录最大边正方形的x
            int y = -1;//用于记录最大边正方形的y
            //根据前缀和数组进行判断正方形是否成立
            for (int row = 0; row < grid.length; row++) {
                for (int cloumn = 0; cloumn < grid[row].length; cloumn++) {
                    if (row + max >= grid.length || cloumn + max > grid[row].length) break;
                    int length = 0;
                    int value = grid[row][cloumn];
                    if (value == 1) {
                        //从这里开始寻找
                        if (max == -1) {
                            max = length;
                            x = cloumn;
                            y = row;
                        }
                        while (row + length < grid.length && cloumn + length < grid[row].length) {
                            if (isSquare(hGrid, row, cloumn, length)) {
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
            System.out.println("x:" + x + " y:" + y + " max:" + max);
            //return getTotal(hGrid, x, y, max);
            return (max + 1) * (max + 1);
        }

        /**
         * 判断是否为边正方形
         */
        private boolean isSquare(int[][] hGrid, int row, int cloumn, int length) {
            if (length == 0) return true;
            System.out.println("row:" + row + " cloumn:" + cloumn + " length:" + length);
            int diff = hGrid[row + length - 1][cloumn + length - 1] - hGrid[row][cloumn + length - 1] - hGrid[row + length - 1][cloumn] + hGrid[row][cloumn];
            int target = (length + 1) * (length + 1) - (length - 1) * (length - 1);
            int total = getTotal(hGrid, row, cloumn, length);
            System.out.println("diff:" + diff + " total:" + total + " target:" + target);
            return total - diff == target;
        }

        private int getTotal(int[][] hGrid, int row, int cloumn, int length) {
            return hGrid[row + length][cloumn + length] - (row == 0 ? 0 : (hGrid[row - 1][cloumn + length])) - (cloumn == 0 ? 0 : (hGrid[row + length][cloumn - 1])) + ((row == 0 || cloumn == 0) ? 0 : (hGrid[row - 1][cloumn - 1]));
        }

//        private void printArr(int[][] grid) {
//            for (int i = 0; i < grid.length; i++) {
//                for (int j = 0; j < grid[i].length; j++) {
//                    System.out.print(grid[i][j] + ",");
//                }
//            }
//            System.out.println("");
//        }
    }
}
