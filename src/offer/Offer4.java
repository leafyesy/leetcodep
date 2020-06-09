package src.offer;

public class Offer4 {

    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {1, 2, 3, 4, 5, 6},
                {2, 3, 4, 5, 6, 7},
                {3, 4, 5, 6, 7, 8},
                {4, 5, 6, 7, 8, 9}
        };
        boolean result = exitNum(arr, 7);
        System.out.println("result:" + result);
    }

    private static boolean exitNum(int[][] arr, int target) {
        for (int[] intArr : arr) {
            //boolean result = half(intArr, target, 0, intArr.length);
            boolean result = half2(intArr,target);
            if (result) return true;
        }
        return false;
    }

    private static boolean half2(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return false;
    }

    private static boolean half(int[] arr, int target, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return true;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
            return half(arr, target, left, right);
        }
        return false;
    }


}
