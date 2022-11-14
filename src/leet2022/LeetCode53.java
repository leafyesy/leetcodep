package src.leet2022;

public class LeetCode53 {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = new int[]{5, 4, -1, 7, 8};
        int[] arr3 = new int[]{-1};
        int[] arr4 = new int[]{-2, 1};
        int[] arr5 = new int[]{-2, -1};
        int[] arr6 = new int[]{-3, -2, 1, 2, 2, 0, 1, 0};
        int result = new LeetCode53().maxSubArray(arr);
        int result2 = new LeetCode53().maxSubArray(arr2);
        int result3 = new LeetCode53().maxSubArray(arr3);
        int result4 = new LeetCode53().maxSubArray(arr4);
        int result5 = new LeetCode53().maxSubArray(arr5);
        int result6 = new LeetCode53().maxSubArray(arr6);
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);
        System.out.println("result4:" + result4);
        System.out.println("result5:" + result5);
        System.out.println("result6:" + result6);
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int result = nums[0];
        int total = 0;
        for (int i = 0; i < nums.length; ) {
            int nextTotal = total + nums[i];
            if (total < 0 && nums[i] >= 0 || (nextTotal < result && nextTotal < 0)) {
                i = left + 1;
                left = i;
                total = 0;
                continue;
            }
            if (nextTotal >= result) {
                result = nextTotal;
                total = nextTotal;
                right = i;
            } else {
                total = nextTotal;
            }
            i++;
        }
        System.out.println("left:" + left + " right:" + right);
        return result;
    }


}
