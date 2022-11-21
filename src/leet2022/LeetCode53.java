package src.leet2022;

import java.util.Arrays;

public class LeetCode53 {

    public static void main(String[] args) {
        int[] arr = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] arr2 = new int[]{5, 4, -1, 7, 8};
        int[] arr3 = new int[]{-1};
        int[] arr4 = new int[]{-2, 1};
        int[] arr5 = new int[]{-2, -1};
        int[] arr6 = new int[]{-3, -2, 1, 2, 2, 0, 1, 0};
        int[] arr7 = new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000};
        int[] arr8 = new int[100000];
        Arrays.fill(arr8, 10000);
        int[] arr9 = new int[]{-3, -2, 0, -1};
        int result = new LeetCode53().maxSubArray2(arr);
        int result2 = new LeetCode53().maxSubArray2(arr2);
        int result3 = new LeetCode53().maxSubArray2(arr3);
        int result4 = new LeetCode53().maxSubArray2(arr4);
        int result5 = new LeetCode53().maxSubArray2(arr5);
        int result6 = new LeetCode53().maxSubArray2(arr6);
        int result7 = new LeetCode53().maxSubArray2(arr7);
        int result8 = new LeetCode53().maxSubArray2(arr8);
        int result9 = new LeetCode53().maxSubArray2(arr9);
        System.out.println("result:" + result);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);
        System.out.println("result4:" + result4);
        System.out.println("result5:" + result5);
        System.out.println("result6:" + result6);
        System.out.println("result7:" + result7);
        System.out.println("result8:" + result8);
        System.out.println("result9:" + result9);
    }


    //f(x) = Math.max(f(x - 1) + nums[x], nums[x])
    public int maxSubArray2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int result = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            if (dp[i] > result) result = dp[i];
        }
        return result;
    }


    public int maxSubArray3(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int result = nums[0];
        int total;
        for (int i = 0; i < nums.length; ) {
            left = i;
            total = 0;
            for (int j = i; j < nums.length; j++) {
                total = total + nums[j];
                if (total > result) {
                    result = total;
                    right++;
                } else if (total < 0) {
                    left = j;
                    break;
                }
            }
            if (right == nums.length - 1) {
                break;
            }
            if (i == left) {
                i = left + 1;
            } else {
                i = left;
            }
        }
        return result;
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
