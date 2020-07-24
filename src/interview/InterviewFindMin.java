package src.interview;

/**
 * 旋转数组寻找最小值
 */
public class InterviewFindMin {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 4, 5, 6, 1};
        int a1[] = {4, 5, 6, 1, 2, 3};
        int a2[] = {1, 2, 3, 4, 5, 6};
        int a3[] = {2, 1};
        int a4[] = {1};
        int a5[] = {3, 1, 2};
        int result = findMin(arr);
        int result1 = findMin(a1);
        int result2 = findMin(a2);
        int result3 = findMin(a3);
        int result4 = findMin(a4);
        int result5 = findMin(a5);
        System.out.println("result:" + result);
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);
        System.out.println("result4:" + result4);
        System.out.println("result5:" + result5);
    }

    public static int findMin(int[] arr) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            int midValue = arr[mid];
            if (mid == 0) {
                return midValue;
            }
            if (mid == arr.length - 1) {
                return midValue;
            }
            if (arr[mid - 1] > midValue && arr[mid + 1] > midValue) {
                return midValue;
            }
            if (midValue > arr[arr.length - 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return -1;
    }


}
