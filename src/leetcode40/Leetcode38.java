package leetcode40;

import java.util.ArrayList;
import java.util.List;

public class Leetcode38 {

    public static void main(String[] args) {
        String result = countAndSay(10);
        System.out.println("result:" + result);
    }

    public static String countAndSay(int n) {
        if (n == 1) return "1";
        StringBuilder nextResult = new StringBuilder();
        nextResult.append("1");
        for (int i = 1; i < n; i++) {
            String preResult = nextResult.toString();
            //char[] chars = nextResult.toString().toCharArray();
            nextResult.delete(0, nextResult.length());
            for (int j = 0; j < preResult.length(); j++) {
                char c = preResult.charAt(j);
                int count = 1;
                while (true) {
                    if (j + count >= preResult.length()) break;
                    char nextC = preResult.charAt(j + count);
                    if (nextC == c) {
                        count++;
                    } else {
                        break;
                    }
                }
                nextResult.append(count).append(c);
                j += (count - 1);
            }
        }
        return nextResult.toString();
    }

}
