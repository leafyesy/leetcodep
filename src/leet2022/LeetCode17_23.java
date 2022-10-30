package src.leet2022;

public class LeetCode17_23 {

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        int[][] matrix = new int[][]{{0, 0, 1}, {1, 0, 1}, {1, 1, 0}};
//        int[][] matrix = new int[][]{{1, 0, 1}, {0, 0, 1}, {0, 0, 0}};

        int[][] matrix = new int[][]{{0, 1, 1}, {1, 0, 1}, {1, 1, 0}};
        int[][] matrix2 = new int[][]{
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 0},
                {0, 1, 0, 1, 1, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 0, 0, 1, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 1, 0},
                {1, 1, 0, 1, 1, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 0, 0, 1, 1, 0},
                {1, 0, 0, 0, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}};
        /*
        [
        [1, 1, 1, 1, 0, 1, 0, 1, 1, 1],
        [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 1, 1, 0, 1, 0, 1, 0, 1],
        [1, 1, 1, 1, 0, 0, 0, 0, 0, 0],
        [1, 0, 1, 0, 1, 1, 1, 1, 1, 1],
        [1, 1, 0, 0, 1, 0, 1, 0, 0, 1],
        [0, 0, 0, 1, 1, 1, 0, 1, 0, 1],
        [0, 0, 0, 1, 0, 1, 0, 1, 0, 1],
        [1, 0, 1, 0, 1, 1, 0, 1, 1, 1],
        [1, 1, 1, 0, 1, 0, 0, 1, 1, 1]]
         */
        int[] square = new LeetCode17_23().findSquare3(matrix);
        if (square.length == 0) {
            System.out.println("none");
        } else {
            System.out.println("r:" + square[0] + " c:" + square[1] + " size:" + square[2]);
        }
    }

    public int[] findSquare(int[][] matrix) {
        //[r, c, size]
        int r = 0;
        int c = 0;
        int size = 0;
        for (int y = 0; y < matrix.length; y++) {
            if (y + size >= matrix.length) {
                break;
            }
            for (int x = 0; x < matrix[y].length; x++) {
                if (x + size >= matrix[y].length) {
                    break;
                }
                if (matrix[y][x] != 0) {
                    continue;
                }
                // 寻找适合的方块
                int f = size;
                while (y + f < matrix.length && x + f < matrix[y].length) {
                    boolean isBreak = false;
                    boolean isValid = true;
                    for (int i = 0; i <= f; i++) {
                        if (matrix[y][x + i] != 0 || matrix[y + i][x] != 0) {
                            isValid = false;
                            isBreak = true;
                            break;
                        }
                        if (matrix[y + f][x + i] != 0 || matrix[y + i][x + f] != 0) {
                            isValid = false;
                            break;
                        }
                    }
                    if (isBreak) {
                        break;
                    }
                    if (isValid) {
                        r = y;
                        c = x;
                        size = f + 1;
                    }
                    f++;
                }
            }
        }
        if (size == 0) {
            return new int[]{};
        } else {
            return new int[]{r, c, size};
        }
    }

    public int[] findSquare2(int[][] matrix) {
        //[r, c, size]
        int r = 0;
        int c = 0;
        int size = 0;
        for (int y = 0; y < matrix.length; y++) {
            if (y + size >= matrix.length) {
                break;
            }
            for (int x = 0; x < matrix[y].length; x++) {
                if (x + size >= matrix[y].length) {
                    break;
                }
                if (matrix[y][x] != 0) {
                    continue;
                }
                if (size == 0) {
                    r = y;
                    c = x;
                    size = 1;
                }
                // 寻找适合的方块
                int f = size;
                // 判断上边和左侧是否正确
                if (!(y + f < matrix.length && x + f < matrix[y].length)) {
                    break;
                }
                boolean isContinue = false;
                for (int i = 1; i <= f; i++) {
                    if (matrix[y][x + i] != 0 || matrix[y + i][x] != 0) {
                        isContinue = true;
                        break;
                    }
                }
                if (isContinue) {
                    continue;
                }
                while (y + f < matrix.length && x + f < matrix[y].length) {
                    if (matrix[y][x + f] != 0 || matrix[y + f][x] != 0) {
                        break;
                    }
                    boolean isValid = true;
                    for (int i = 0; i <= f; i++) {
                        if (matrix[y + f][x + i] != 0 || matrix[y + i][x + f] != 0) {
                            isValid = false;
                            break;
                        }
                    }
                    if (isValid) {
                        r = y;
                        c = x;
                        size = f + 1;
                    }
                    f++;
                }
            }
        }
        if (size == 0) {
            return new int[]{};
        } else {
            return new int[]{r, c, size};
        }
    }


    public int[] findSquare3(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[]{};
        }
        int yLen = matrix.length;
        int xLen = matrix[0].length;
        int[][] matrixRightZero = new int[yLen][xLen];
        int[][] matrixDownZero = new int[yLen][xLen];
        // 得到每个点向右/向下的最长边大小
        for (int y = yLen - 1; y >= 0; y--) {
            for (int x = xLen - 1; x >= 0; x--) {
                matrixRightZero[y][x] = matrix[y][x] == 0 ? (1 + (x == xLen - 1 ? 0 : matrixRightZero[y][x + 1])) : 0;
                matrixDownZero[y][x] = matrix[y][x] == 0 ? (1 + (y == yLen - 1 ? 0 : matrixDownZero[y + 1][x])) : 0;
            }
        }
        // 遍历上述的点,得到正方形
        int r = -1;
        int c = -1;
        int size = 0;

        for (int y = 0; y < yLen; y++) {
            for (int x = 0; x < xLen; x++) {
                if (matrixRightZero[y][x] >= size && matrixDownZero[y][x] >= size) {
                    // 得到一个边长可能大于原来大小的正确上边和左边的线
                    int max = Math.min(matrixRightZero[y][x], matrixDownZero[y][x]);
                    for (int maybeSize = size; maybeSize <= max; maybeSize++) {
                        if (matrixRightZero[y + maybeSize - 1][x] >= maybeSize && matrixDownZero[y][x + maybeSize - 1] >= maybeSize) {
                            //表示x,y点能形成正方形
                            size = maybeSize;
                            r = y;
                            c = x;
                        }
                    }
                }
            }
        }
        if (size > 0) {
            return new int[]{r, c, size};
        } else {
            return new int[]{};
        }

    }


}
