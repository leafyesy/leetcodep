package src.leet2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCode1 {

    public static void main(int[] args) {

    }


    // 数组需要不同元素
    public int[] twoSum_1(int[] nums, int target) {
        HashMap<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numsMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int needNum = target - num;
            if (needNum == num) {
                continue;
            }
            Integer secondIndex = numsMap.get(needNum);
            if (secondIndex != null) {
                int[] result = new int[2];
                result[0] = i;
                result[1] = secondIndex;
                return result;
            }
        }
        return null;
    }

    // 使用hash算法
    public int[] twoSum_hash(int[] nums, int target) {
        HashMap<Integer, List<Integer>> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> indexList = numsMap.get(nums[i]);
            if (indexList == null) {
                indexList = new ArrayList<>();
                numsMap.put(nums[i], indexList);
            }
            indexList.add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            int secondIndex = -1;
            int needNum = target - nums[i];
            List<Integer> indexList = numsMap.get(needNum);
            if (needNum == nums[i]) {
                if (indexList.size() == 1) {
                    continue;
                }
                secondIndex = indexList.get(1);
            } else {
                if (indexList != null) {
                    secondIndex = indexList.get(0);
                }
            }
            if (secondIndex != -1) {
                int[] result = new int[2];
                result[0] = i;
                result[1] = secondIndex;
                return result;
            }
        }
        return null;
    }

    // 两层for循环
    public int[] twoSum_two_for(int[] nums, int target) {
        for (int leftIndex = 0; leftIndex < nums.length - 1; leftIndex++) {
            int leftValue = nums[leftIndex];
            for (int rightIndex = leftIndex + 1; rightIndex < nums.length; rightIndex++) {
                int rightValue = nums[rightIndex];
                if (leftValue + rightValue == target) {
                    int[] result = new int[2];
                    result[0] = leftIndex;
                    result[1] = rightIndex;
                    return result;
                }
            }
        }
        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> numsMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int remain = target - nums[i];
            Integer secondIndex = numsMap.get(remain);
            if (secondIndex != null) {
                return new int[]{i, secondIndex};
            } else {
                numsMap.put(nums[i], i);
            }
        }
        return null;
    }


}
