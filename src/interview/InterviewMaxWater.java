package src.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InterviewMaxWater {

    public static void main(String[] args){
        int[] nums = new int[]{3,8,6,2,5,4,8,3,7};
        int max = max(nums);
        System.out.println("max:"+max);
    }

    private static int max(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int result = 0;
        while (left < right) {
            int newResult;
            if (nums[left] < nums[right]) {
                newResult = nums[left] * (right - left);
                left++;
            } else {
                newResult = nums[right] * (right - left);
                right--;
            }
            List<Integer> list = new ArrayList<>();
            Collections.reverse(list);
            if (newResult > result) {
                result = newResult;
            }
        }
        return result;
    }


}
