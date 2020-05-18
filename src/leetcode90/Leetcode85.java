package leetcode90;

public class Leetcode85 {

    public int maximalRectangle(char[][] matrix) {
        int max = -1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //顶点
                if (matrix[i][j] == '1') {
                    //表示这个点开始寻找最大面积
                    int cal = calRectangle(matrix, j, i);
                    if (cal > max) {
                        max = cal;
                    }

                }
            }
        }
        /*
        [
        ["1","1","1","1","1","1","1","1"],
        ["1","1","1","1","1","1","1","0"],
        ["1","1","1","1","1","1","1","0"],
        ["1","1","1","1","1","0","0","0"],
        ["0","1","1","1","1","0","0","0"]]


        [
        ["1","0","1","0","0"],
        ["1","0","1","1","1"],
        ["1","1","1","1","1"],
        ["1","0","0","1","0"]]
         */
        //[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        return max;
    }

    private int calRectangle(char[][] matrix, int j, int i) {
        //向下和向右开始计算最大面积
        int x = 1;
        int y = 1;
        boolean isRowCalculating = true;
        boolean isCloumCalculatinng = true;
        do {
            if (i + y < matrix.length) {
                if (isCloumCalculatinng) {
                    if (matrix[i + y][j] == '1') {
                        int row = 1;
                        boolean isCloumPass = true;
                        while (row < x) {
                            if (matrix[i + y][j + row] != '1') {
                                isCloumPass = false;
                                break;
                            }
                            row++;
                        }
                        if (isCloumPass) {
                            y++;
                        } else {
                            isCloumCalculatinng = false;
                        }
                    }else{
                        isCloumCalculatinng = false;
                    }
                }
            } else {
                isCloumCalculatinng = false;
            }
            if (j + x < matrix[i].length) {
                if (isRowCalculating) {
                    if (matrix[i][j + x] == '1') {
                        int cloum = 1;
                        boolean isRowPass = true;
                        while (cloum < y) {
                            if (matrix[i + cloum][j + x] != '1') {
                                isRowPass = false;
                                break;
                            }
                            cloum++;
                        }
                        if (isRowPass) {
                            x++;
                        } else {
                            isRowCalculating = false;
                        }
                    }else{
                        isRowCalculating = false;
                    }
                }
            } else {
                isRowCalculating = false;
            }
        } while (isRowCalculating && isCloumCalculatinng);

        return x * y;
    }

}
