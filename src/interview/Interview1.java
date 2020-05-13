package interview;

/**
 * 顺时针打印一个矩阵
 */
public class Interview1 {


    public static void main(String[] args) {
        int n = 3;
        int count = 0;
        int[][] ints = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                ints[x][y] = ++count;
                System.out.print(count + "   ");
                if (count % n == 0) {
                    System.out.println("");
                }
            }
        }
        output(ints, 0, n - 1);
    }

    public static void output(int[][] arr, int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.println(arr[start][i]);
        }
        for (int j = start + 1; j <= end; j++) {
            System.out.println(arr[j][end]);
        }
        for (int k = end - 1; k >= start; k--) {
            System.out.println(arr[end][k]);
        }
        for (int l = end - 1; l >= start + 1; l--) {
            System.out.println(arr[l][start]);
        }
        if (start < end)
            output(arr, start + 1, end - 1);
    }


}
