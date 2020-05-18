package leetcode10;

import java.util.HashMap;

public class Leetcode1 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] arr = {2, 7, 11, 15};
        int[] ints = s.twoSum(arr, 18);
        for (int anInt : ints) {
            System.out.println("" + anInt);
        }
    }


    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            if (nums.length == 0 || nums.length == 1) {
                return new int[2];
            }
            if (nums.length == 2) {
                if (nums[0] + nums[1] == target) {
                    return new int[]{0, 1};
                }
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                Integer value = map.get(target - num);
                if (value == null) {
                    map.put(num, i);
                } else {
                    return new int[]{value, i};
                }
            }
            return new int[2];
        }
    }

}
