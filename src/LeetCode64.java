import java.util.Stack;

public class LeetCode64 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 0, 3, 2, 4, 3, 4};
        Solution s = new Solution();
        System.out.println("area:" + s.largestRectangleArea(arr));
    }

    /**
     * 使用单调栈的方式求解
     */
    static class Solution {
        public int largestRectangleArea(int[] heights) {
            if (heights.length == 1) return heights[0];
            int maxArea = -1;
            Stack<Integer> stack = new Stack<>();
            stack.push(-1);
            for (int i = 0; i < heights.length; i++) {
                for (; stack.peek() != -1 && heights[i] < heights[stack.peek()]; ) {
                    maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
                }
                stack.push(i);
            }
            while (stack.peek()!=-1){
                maxArea = Math.max(maxArea, heights[stack.pop()] * (heights.length - stack.peek() -1));
            }
            return maxArea;
        }
    }


}
