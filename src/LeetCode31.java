
public class LeetCode31 {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 5, 2};
        Solution s = new Solution();
        s.nextPermutation(arr);
        printArr(arr);
    }

    /**
     * leetcode
     */
    static class Solution2 {
        public void nextPermutation(int[] nums) {
            int i = nums.length - 2;
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--;
            }
            if (i >= 0) {
                int j = nums.length - 1;
                while (j >= 0 && nums[j] <= nums[i]) {
                    j--;
                }
                swap(nums, i, j);
            }
            reverse(nums, i + 1);
        }

        private void reverse(int[] nums, int start) {
            int i = start, j = nums.length - 1;
            while (i < j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

    }

    static class Solution {
        public void nextPermutation(int[] nums) {
            if (nums.length == 1) return;
            int cL = -1;
            int cR = -1;
            int min = Integer.MAX_VALUE;
            for (int i = nums.length - 2; i >= 0; i--) {
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > nums[i]) {
                        if (nums[j] < min) {
                            min = nums[j];
                            cR = j;
                        }
                    }
                }
                if (cR != -1) {
                    cL = i;
                    swap(cL, cR, nums);
                    break;
                }
            }
            int left = cL + 1;
            for (; left < nums.length - 1; left++) {
                for (int index = nums.length - 1; index > left && index > 0; index--) {
                    if (nums[index] < nums[index - 1])
                        swap(index, index - 1, nums);
                }
            }
            if (left == -1) {
                //进行整个数组翻转
                int count = nums.length / 2;
                for (int n = 0; n < count; n++) {
                    swap(n, nums.length - 1 - n, nums);
                }
            }
        }

        private void swap(int left, int right, int[] arr) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
    }

    static void printArr(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");
    }

}
