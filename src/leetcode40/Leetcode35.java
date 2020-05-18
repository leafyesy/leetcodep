package leetcode40;

public class Leetcode35 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 6};
        int result = searchInsert(arr, 8);
        System.out.println("result:" + result);
    }

    /**
     * 折半查找,定义一个half/low/high,其中half永远等于(low+high)/2,交替寻找得到位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        int half;
        int low = 0;
        int high = nums.length;
        while (true) {
            half = (low + high) / 2;
            if (nums[half] == target) {
                return half;
            } else if (nums[half] > target) {
                if (half == 0) return 0;
                if (nums[half - 1] < target) return half;
                high = half;
            } else if (nums[half] < target) {
                if (half == nums.length - 1) return nums.length;
                if (nums[half + 1] > target) return half + 1;
                low = half;
            }
        }
    }

}
