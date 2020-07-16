package src.leetcode120;

import java.util.Arrays;

public class LeetCode115 {

    public static void main(String[] args) {
        Solution s = new Solution();
        int value = s.numDistinct("rabbbit", "rabit");
        System.out.println("value:" + value);
    }


    static class Solution {
        public int numDistinct(String s, String t) {
            if (s == null || t == null) return 0;
            if (s.length() < t.length()) return 0;
            int[][] d = new int[t.length() + 1][s.length() + 1];
            Arrays.fill(d[0], 1);
            for (int i = 1; i < d.length; i++) {
                for (int j = 1; j < d[i].length; j++) {
                    //System.out.println("s.charAt(j - 1) :" + s.charAt(j - 1) + "  t.charAt(i - 1):" + t.charAt(i - 1));
                    if (s.charAt(j - 1) == t.charAt(i - 1)) {
                        d[i][j] = d[i][j - 1] + d[i - 1][j - 1];
                    } else {
                        d[i][j] = d[i][j - 1];
                    }
                    //System.out.println("i:" + i + " j:" + j + "  " + d[i][j]);
                    //d[i][j] = d[i][j - 1] + (s.charAt(i - 1) == t.charAt(j - 1) ? d[i - 1][j - 1] : 0);
                }
            }
            return d[t.length()][s.length()];
        }
    }

}
