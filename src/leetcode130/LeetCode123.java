package src.leetcode130;

public class LeetCode123 {

    private int sellCount = 2;

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int length = prices.length;
        int[][][] dp = new int[length][sellCount + 1][2];
        for (int i = 0; i < length; i++) {
            for (int j = 1; j <= sellCount; j++) {
                if (i == 0) {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = prices[i];
                    continue;
                }
                dp[i][j][0] = Math.max(dp[i - 1][j][0], prices[i] - dp[i - 1][j][1]);
                dp[i][j][1] = Math.min(dp[i - 1][j][1], prices[i] - dp[i - 1][j - 1][0]);
            }
        }
        return dp[length - 1][sellCount][0];
    }


}
