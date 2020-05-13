package interview;

/**
 * 数组组成的最小数
 */
public class Interview4 {

    public static void main(String[] args) {
//        int[] arr = new int[]{4, 32, 3, 2, 5, 6, 44};
//        int[] arr = new int[]{1,2,3,4,5};
        int[] arr = new int[]{5,4432,3,2,1111};
        findMin(arr);
    }

    private static void findMin(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                //对比前面和后面的大小
                compareAndSwap(arr, i, j);
            }
        }
        for (int arrS : arr) {
            System.out.print(arrS);
        }
    }

    private static void compareAndSwap(int[] arr, int x, int y) {
        String s1 = getNumByArr(arr);
        swap(arr, x, y);
        String s2 = getNumByArr(arr);
        System.out.println("first:"+s1+"    second:"+s2);
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) < s2.charAt(i)) {
                swap(arr, x, y);
                break;
            }else if (s1.charAt(i) > s2.charAt(i)){
                break;
            }
        }
    }

    private static String getNumByArr(int[] arr) {
        StringBuilder s = new StringBuilder();
        for (int anArr : arr) {
            s.append(anArr);
        }
        return s.toString();
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }


}
