package src.leetcode130;

/**
 * 买卖股票的最佳时机
 */
public class LeetCode121 {

    public static void main(String[] args) {

    }

    public int maxProfit(int[] prices){
        return maxProfitDp3(prices);
    }

    public int maxProfitDp3(int[] prices){
        if (prices == null || prices.length <= 1) return 0;
        int length = prices.length;
        int max = 0;
        int min = prices[0];
        for(int i = 1;i<length;i++){
            max = Math.max(max,prices[i] - min);
            min = Math.min(min,prices[i]);
        }
        return max;
    }

    public int maxProfitDp(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                dp[0][0] = 0;
                dp[0][1] = prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i - 1][0], prices[i] - dp[i - 1][1]);
            dp[i][1] = Math.min(dp[i - 1][1], prices[i]);
        }
        return dp[length - 1][0];
    }

    public int maxProfitDp2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int length = prices.length;
        //dp
        //一维记录最大的交易结果
        //二维记录遍历时最小的值
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for(int i = 1;i<length;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[length-1][0];
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int minIndex = 0;
        int maxIndex = -1;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < prices[minIndex]) {
                minIndex = i;
            }else if (maxIndex == -1 || prices[i] >= prices[maxIndex]) {
                maxIndex = i;
            }
            if (maxIndex > minIndex) {
                int temp = prices[maxIndex] - prices[minIndex];
                if (temp > max) {
                    max = temp;
                }
            } else {
                maxIndex = -1;
            }
        }
        return max;
    }

    private int maxProfit1(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int left = -1;
        int right = 0;
        int max = 0;
        while(left < prices.length - 1 && right < prices.length){
            left = findNextLow(prices,left+1);
            if(left == -1 || left == prices.length - 1) break;//没有找到下一个
            if(left>=right){
                right = findNextHigh(prices,left+1);
            }
            //System.out.println("left:"+left+" right:"+right);
            if(left != right){
                int temp = prices[right] - prices[left];
                if(temp > max){
                    max = temp;
                }
            }
        }
        return max;
    }
    //寻找递减最低值
    private int findNextLow(int[] prices,int start){
        int index = start;
        while(index < prices.length - 1){
            if(prices[index] > prices[index+1]){
                index++;
            }else{
                return index;
            }
        }
        return -1;
    }
    //在剩余的数组中寻找最大值
    private int findNextHigh(int[] prices,int start){
        int index = start;
        int maxIndex = start;
        while(index<prices.length-1){
            if(prices[index+1]>prices[maxIndex]){
                maxIndex = index+1;
            }
            index++;
        }
        return maxIndex;
    }


}
