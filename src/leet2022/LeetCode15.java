package src.leet2022;

import java.util.*;

public class LeetCode15 {

    public static void main(String[] args) {
        int[] ar = new int[]{
                34, 55, 79, 28, 46, 33, 2, 48, 31, -3, 84, 71, 52, -3, 93, 15, 21, -43, 57, -6,
                86, 56, 94, 74, 83, -14, 28, -66, 46, -49, 62, -11, 43, 65, 77, 12, 47, 61, 26,
                1, 13, 29, 55, -82, 76, 26, 15, -29, 36, -29, 10, -70, 69, 17, 49};
        //int[] ar = new int[]{-1, 0, 1, 2, -1, -4};
        //int[] ar = new int[]{0,1,1};
        //int[] ar = new int[]{0, 0, 0, 0};
//        int[] ar = new int[]{-1,0,1,0};
        List<List<Integer>> lists = new LeetCode15().threeSum(ar);
        StringBuilder str = new StringBuilder();
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                str.append(integer).append(",");
            }
            str.append("\n");
        }
        System.out.println(str);
    }

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length == 0) {
            return result;
        }
        // 数组不是有序的,先排序
        Arrays.sort(nums);
        int left = 0;
        while (left <= nums.length - 3 && nums[left] <= 0) {
            int innerLeft = left + 1;
            int innerRight = nums.length - 1;
            while (innerLeft < innerRight) {
                int innerItemResult = nums[innerLeft] + nums[innerRight];
                if (innerItemResult + nums[left] == 0) { // 表示正好为0
                    List<Integer> resultItem = new ArrayList<>();
                    resultItem.add(nums[left]);
                    resultItem.add(nums[innerLeft]);
                    resultItem.add(nums[innerRight]);
                    result.add(resultItem);
                }
                if (innerItemResult + nums[left] > 0) {
                    innerRight--;
                    while (innerRight > innerLeft && nums[innerRight] == nums[innerRight + 1]) {
                        innerRight--;
                    }
                } else {
                    innerLeft++;
                    while (innerLeft < innerRight && nums[innerLeft] == nums[innerLeft - 1]) {
                        innerLeft++;
                    }
                }
            }
            left++;
            // 进行去重
            while (left <= nums.length - 1 && nums[left] == nums[left - 1]) {
                left++;
            }
        }
        return result;
    }


}
