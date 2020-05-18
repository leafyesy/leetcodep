package leetcode40;

public class Leetcode36 {


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        boolean isValidSudoku = isValidSudoku(board);
        System.out.println("isValidSudoku:" + isValidSudoku);
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '.') {
                    boolean isValidOne = isValid(board, i, j);
                    if (!isValidOne) return false;
                }
            }
        }
        return true;
    }

    //判断某个点上的数值是否正确
    public static boolean isValid(char[][] board, int x, int y) {
        for (int i = 0; i < 9; i++) {
            if (i != y) {
                char yy = board[x][i];
                if (yy != '.' && yy == board[x][y])
                    return false;
            }
            if (i != x) {
                int xx = board[i][y];
                if (xx != '.' && xx == board[x][y])
                    return false;
            }
            int _9x = x / 3 * 3 + i % 3;
            int _9y = y / 3 * 3 + i / 3;
            if (_9x != x && _9y != y) {
                int xxyy = board[_9x][_9y];
                if (xxyy != '.' && xxyy == board[x][y])
                    return false;
            }
        }
        return true;
    }


}
