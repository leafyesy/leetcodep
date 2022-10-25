package src.leet2022;

public class LeetCode5 {

    public static void main(String[] args) {
        String result = longestPalindrome("ccc");
        System.out.println("result:" + result);
    }

    // 从中心进行遍历
    public static String longestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        String longStr = "";
        int center = 0;
        int longStrLength = 0;
        int max = s.length() * 2 - 2;
        while (center + longStrLength <= max) {
            String newLongStr = getLongPalindrome(s, center);
            if (newLongStr.length() > longStr.length()) {
                longStr = newLongStr;
                longStrLength = longStr.length();
            }
            center++;
        }
        return longStr;
    }

    public static boolean isCenterTwoNums(int center) {
        return center % 2 == 1;
    }

    public static String getLongPalindrome(String s, int center) {
        String longStr;
        int left;
        int right;
        if (isCenterTwoNums(center)) {
            left = center >> 1;
            right = left + 1;
            longStr = "";
        } else {
            left = (center >> 1) - 1;
            right = (center >> 1) + 1;
            longStr = s.substring(left, left + 1);
        }
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            longStr = s.substring(left, right + 1);
            left--;
            right++;
        }
        return longStr;
    }


    // 从左到右进行遍历
    public static String longestPalindrome_1(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s;
        }
        int start = 0;
        String longestStr = "";
        while (start + longestStr.length() < s.length()) {
            int end = s.length() - 1;
            while (start + longestStr.length() <= end) {
                if (isPalindrome(s, start, end)) {
                    if (end - start + 1 > longestStr.length()) {
                        longestStr = s.substring(start, end + 1);
                    }
                    break;
                } else {
                    end--;
                }
            }
            start++;
        }
        return longestStr;
    }

    private static boolean isPalindrome(String s, int start, int end) {
        int l = start;
        int r = end;
        while (l < r) {
            char cl = s.charAt(l);
            char cr = s.charAt(r);
            if (cl == cr) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }


}
