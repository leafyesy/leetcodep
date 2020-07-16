package src.leetcode130;

import java.util.Stack;

public class LeetCode122 {


    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int left = 0;
        int right = 0;
        int result = 0;
        while (right < prices.length) {
            if (prices[right] <= prices[right + 1]) {
                right++;
            } else {
                result += (prices[right] - prices[left]);
                left = right = right + 1;
            }
            if (right == prices.length - 1) {
                if (right > left) {
                    result += (prices[right] - prices[left]);
                }
                break;
            }
        }
        return result;
    }

    public int maxProfitDp(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int length = prices.length;
        //0:最大值
        //1:当前区域内最大差值
        //2:当前区域内最小值
        int[][] dp = new int[length][2];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] - dp[i - 1][1]);
            dp[i][1] = Math.min(dp[i - 1][1], prices[i] - dp[i - 1][0]);
        }
        return dp[length - 1][0];
    }


    public int maxProfit3(int[] prices) {
        if (prices.length == 0 || prices.length == 1) return 0;
        int left = 0;
        int right = 0;
        int result = 0;
        while (right < prices.length) {
            if (prices[right] <= prices[right + 1]) {
                right++;
            } else {
                result += (prices[right] - prices[left]);
                left = right = right + 1;
            }
            if (right == prices.length - 1) {
                if (right > left) {
                    result += (prices[right] - prices[left]);
                }
                break;
            }
        }
        return result;
    }

    public int maxProfit2(int[] prices) {
        Stack<Integer> stack = new Stack<>();
        int start = 0;
        int index = 1;
        int result = 0;
        stack.push(prices[start]);
        while (index < prices.length) {
            if (prices[index] >= stack.peek()) {
                if (stack.size() > 1) {
                    stack.pop();
                }
            } else if (prices[index] < stack.peek()) {
                if (stack.size() == 1) {
                    stack.pop();
                } else {
                    int m = stack.pop();
                    int n = stack.pop();
                    result += (m - n);
                }
            }
            stack.push(prices[index]);
            index++;
        }
        if (stack.size() == 2) {
            int m = stack.pop();
            int n = stack.pop();
            result += (m - n);
        }
        return result;
    }
}
