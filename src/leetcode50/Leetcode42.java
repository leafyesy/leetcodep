package src.leetcode50;

public class Leetcode42 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 4, 1, 2};
        int result = Solution.trap(arr);
        System.out.println("result:" + result);
    }

    static class Solution {

        public static int trap(int[] height) {
            int left = 0;
            int right = 0;
            int result = 0;
            for (; ; ) {
                //寻找递减的第一个数
                while (true) {
                    if (left + 1 < height.length && height[left] < height[left + 1]) {
                        left++;
                    } else {
                        break;
                    }
                }
                if (left >= height.length - 2) break;
                //从这里向下寻找最小值,递减的最小数
                right = left + 1;
                int tempRightPosition = right;
                int nextMaxRight = right;
                while (true) {
                    if (tempRightPosition < height.length) {
                        if (height[tempRightPosition] >= height[left]) {
                            nextMaxRight = tempRightPosition;
                            break;
                        } else {
                            if (height[tempRightPosition] >= height[nextMaxRight]) {
                                nextMaxRight = tempRightPosition;
                            }
                        }
                        tempRightPosition++;
                    } else {
                        break;
                    }
                }
                System.out.println("1 left:" + left + " right:" + right);
                right = nextMaxRight;
                if (right < left + 2) {
                    left = right;
                    continue;
                }
                //根据left low right来计算该区域雨水的量
                int min = Math.min(height[left], height[right]);
                System.out.println("left:" + left + " right:" + right + " min:" + min);
                for (int j = left + 1; j < right; j++) {
                    if (height[j] < min)
                        result += min - height[j];
                }
                if (right >= height.length - 1) break;
                left = right;
            }
            return result;
        }
    }


}
