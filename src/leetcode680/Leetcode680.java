package src.leetcode680;

public class Leetcode680 {

    public static void main(String[] args) {
        boolean result = validPalindrome("acdcbca");
        System.out.println("result:" + result);
    }

    public static boolean validPalindrome(String s) {
        return validPalindrome(s, true);
    }

    public static boolean validPalindrome(String s, boolean isCanDelete) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                if (isCanDelete) {
                    String vs1 = s.substring(left, right);
                    String vs2 = s.substring(left + 1, right + 1);
                    System.out.println("vs1:" + vs1);
                    System.out.println("vs2:" + vs2);
                    return validPalindrome(vs1, false) || validPalindrome(vs2, false);
                }
                return false;
            }
        }
        return true;
    }
}
