package src.leet2022;

public class LeetCode33 {

    public static void main(String[] args) {
//        int[] arr = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int[] arr = new int[]{1, 3};
        //int[] arr = new int[]{4, 5, 1, 2, 3};
        int[] arr = new int[]{3, 5, 1};
        int search = new LeetCode33().search(arr, 3);
        System.out.println("result: " + search);
    }

    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (true) {
            System.out.println("left: " + left + " right: " + right);
            int middle = (left + right) / 2;
            int rightVal = nums[right];
            int middleVal = nums[middle];
            if (middleVal == target) {
                return middle;
            }
            if ((rightVal > middleVal && target > rightVal)
                    || (rightVal > middleVal && target < middleVal)
                    || (target < middleVal && target > rightVal)) { // 1.右边都比target大 2.右边都比target小 3.target比rightVal大,却小于middleVal
                right = middle - 1;
            } else {
                left = middle + 1;
            }
            if (left > right) {
                return -1;
            }
        }
    }


}
