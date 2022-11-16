package src.leet2022;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class LeetCode41 {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 2};
        int[] arr1 = new int[]{1, 2, 0};
        int[] arr2 = new int[]{3, 4, -1, 1};
        int[] arr3 = new int[]{7, 8, 9, 11, 12};
        int[] arr4 = new int[]{3, 4, -1, 1};
        int[] arr5 = new int[]{1};
        int result = new LeetCode41().firstMissingPositive6(arr);
        int result1 = new LeetCode41().firstMissingPositive6(arr1);
        int result2 = new LeetCode41().firstMissingPositive6(arr2);
        int result3 = new LeetCode41().firstMissingPositive6(arr3);
        int result4 = new LeetCode41().firstMissingPositive6(arr4);
        int result5 = new LeetCode41().firstMissingPositive6(arr5);
        System.out.println("result:" + result);
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);
        System.out.println("result3:" + result3);
        System.out.println("result4:" + result4);
        System.out.println("result5:" + result5);
    }

    public int firstMissingPositive(int[] nums) {
        HashMap<Integer, Integer> numsMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num < min && num > 0) {
                min = num;
            }
            if (num > 0) {
                numsMap.put(num, 0);
            }
        }
        if (min > 1) {
            return 1;
        }
        for (int i = 0; i < numsMap.size(); i++) {
            Integer maybe = numsMap.get(min + i);
            if (maybe == null) {
                return min + i;
            }
        }
        return min + numsMap.size();
    }

    public int firstMissingPositive2(int[] nums) {
        HashSet<Integer> numsMap = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num < min && num > 0) {
                min = num;
            }
            if (num > max && num > 0) {
                max = num;
            }
            if (num > 0) {
                numsMap.add(num);
            }
        }
        if (min > 1) {
            return 1;
        }
        if ((max - min + 1) <= numsMap.size()) {
            return max + 1;
        }
        for (int i = 0; i < numsMap.size(); i++) {
            if (!numsMap.contains(min + i)) {
                return min + i;
            }
        }
        return min + numsMap.size();
    }

    public int firstMissingPositive3(int[] nums) {
        int[] arr = new int[nums.length];
        Arrays.fill(arr, -1);
        for (int num : nums) {
            if (num > nums.length || num <= 0) {
                continue;
            }
            arr[num - 1] = 1;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == -1) {
                return i + 1;
            }
        }
        return arr.length + 1;
    }

    //3, 4, -1, 1
    public int firstMissingPositive4(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (true) {
                // 表示num合法
                if (num <= nums.length && num > 0) {
                    if (nums[i] == nums[num - 1]) {
                        nums[i] = -1;
                        nums[num - 1] = num;
                        break;
                    }
                    nums[i] = nums[num - 1];
                    nums[num - 1] = num;
                    num = nums[i];
                } else {
                    nums[i] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1) {
                return i + 1;
            }
        }
        return nums[nums.length - 1] + 1;
    }

    public int firstMissingPositive5(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (num <= nums.length && num > 0) {
                // 表示num合法
                nums[i] = nums[i] == nums[num - 1] ? -1 : nums[num - 1];
                nums[num - 1] = num;
                num = nums[i];
                if (nums[i] == -1 || nums[i] == i + 1) break;
            }
            if (nums[i] != i + 1) {
                nums[i] = -1;
            }
        }
        int result = 1;
        for (int num : nums) {
            if (num == -1) break;
            result++;
        }
        return result;
    }


    public int firstMissingPositive6(int[] nums) {
        if (nums.length == 0) {
            return 1;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                // 交换位置
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == -1 || nums[i] != i + 1) {
                return i + 1;
            }
        }
        return nums[nums.length - 1] + 1;
    }

}
