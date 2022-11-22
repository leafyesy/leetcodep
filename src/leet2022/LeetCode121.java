package src.leet2022;

public class LeetCode121 {

    public static void main(String[] args) {
        int[] arr = new int[]{7, 1, 5, 3, 6, 4};
        int result = new LeetCode121().maxProfit(arr);
        System.out.println(result);
    }

    public int maxProfit(int[] prices) {
        int result = 0;
        if (prices.length < 1) {
            return result;
        }
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int next = prices[i];
            if (next < min) {
                min = next;
            } else if (next > min) {
                int diff = next - min;
                if (diff > result) {
                    result = diff;
                }
            }
        }
        return result;
    }

}
