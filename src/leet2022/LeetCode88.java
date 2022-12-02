package src.leet2022;

public class LeetCode88 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[]{2, 5, 6};
        new LeetCode88().merge(arr1, arr1.length - arr2.length, arr2, arr2.length);
        for (int i : arr1) {
            System.out.println(i);
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        for (int i = nums1.length - 1; i >= 0; i--) {
            int num1 = left < 0 ? Integer.MIN_VALUE : nums1[left];
            int num2 = right < 0 ? Integer.MIN_VALUE : nums2[right];
            if (num2 > num1) {
                nums1[i] = num2;
                right--;
            } else {
                nums1[i] = num1;
                left--;
            }
        }
    }

}
