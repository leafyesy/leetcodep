package src.leet2022;

public class LeetCode8 {

    public static void main(String[] args) {
        //2147483647
        int i = new LeetCode8().myAtoi("1095502006p8");
        System.out.println("result:" + i);
    }

    public int myAtoi(String s) {
        // 1.filter valid string
        StringBuilder validStr = new StringBuilder();
        boolean isStartZero = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            String curStr = validStr.toString();
            if (curStr.length() == 0 && c == ' ') {// 空格跳过
                if (isStartZero) {
                    break;
                }
            } else if (curStr.length() == 0 && (c == '-' || c == '+')) {
                if (isStartZero) {
                    break;
                }
                validStr.append(c);// 找到第一个 '-' 或者 '+'
            } else if (c == '0') {
                if (curStr.length() == 0) {
                    isStartZero = true;
                    continue;
                }
                if (curStr.length() == 1) {
                    if (curStr.charAt(0) == '+' || curStr.charAt(0) == '-') {
                        continue;
                    }
                }
                validStr.append(c);
            } else if (c > '0' && c <= '9') {
                validStr.append(c);
            } else {
                break;
            }
        }
        // 2.check string
        String finalValidStr = validStr.toString();
        if (finalValidStr.length() == 0) {
            return 0;
        }
        boolean isSignStart = finalValidStr.charAt(0) == '-' || finalValidStr.charAt(0) == '+';
        if (isSignStart && finalValidStr.length() == 1) {
            return 0;
        }
        // 表示负数
        boolean isNegativeNum = finalValidStr.charAt(0) == '-';
        if (isSignStart) {
            finalValidStr = finalValidStr.substring(1);
        }
        int finalLen = finalValidStr.length();
        String maxNum = (isNegativeNum ? ("" + Integer.MIN_VALUE).substring(1) : "" + Integer.MAX_VALUE);
        // 3.cover to num
        int result = 0;
        if (finalLen > maxNum.length()) {
            if (isNegativeNum) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        boolean preEqual = true;
        for (int i = 0; i < finalLen; i++) {
            //char maxNumC = maxNum.charAt(maxNum.length() - i);
            int indexInt = finalValidStr.charAt(i) - '0';
            if (finalLen == maxNum.length() && preEqual) {
                // 进行大小是否溢出判断
                if (indexInt > (maxNum.charAt(i) - '0')) {
                    // 表示溢出
                    if (isNegativeNum) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                } else {
                    preEqual = indexInt == (maxNum.charAt(i) - '0');
                }
            }
            result += indexInt * pow10(finalLen - 1 - i);
        }
        return isNegativeNum ? -result : result;
    }

    private int pow10(int p) {
        if (p == 0) {
            return 1;
        }
        if (p == 1) {
            return 10;
        }
        int result = 10;
        for (int i = 1; i < p; i++) {
            result *= 10;
        }
        return result;
    }


}
