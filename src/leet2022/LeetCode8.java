package src.leet2022;

public class LeetCode8 {

    public static void main(String[] args) {
        //2147483647
        //int i = new LeetCode8().myAtoi2("    -1111111111");
        //int i = new LeetCode8().myAtoi2("00000-42a1234");
        //int i = new LeetCode8().myAtoi2("   +0 123");
        int i = new LeetCode8().myAtoi3("2147483648");
        System.out.println("result:" + i);
    }

    public int myAtoi3(String s) {
        String realS = s.trim();
        if (realS.length() == 0) {
            return 0;
        }
        char firstC = realS.charAt(0);
        if (firstC != '-' && firstC != '+' && !Character.isDigit(firstC)) {
            return 0;
        }
        boolean isNegativeNum = firstC == '-';
        boolean isFirstDigit = Character.isDigit(firstC);
        int result = 0;
        int tmp = isNegativeNum ? (-(Integer.MIN_VALUE / 10)) : Integer.MAX_VALUE / 10;
        int last = isNegativeNum ? (-(Integer.MIN_VALUE % 10)) : Integer.MAX_VALUE % 10;
        for (int i = isFirstDigit ? 0 : 1; i < realS.length(); i++) {
            if (!Character.isDigit(realS.charAt(i))) {
                break;
            }
            int next = realS.charAt(i) - '0';
            if (result > tmp || (result == tmp && next > last)) {
                return isNegativeNum ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + next;
        }
        return isNegativeNum ? -result : result;
    }

    public int myAtoi2(String s) {
        int sign = 0;// -2 表示没有找到符号位 -1 表示负数 1 表示正数
        int zero = -1;// 第一个zero出现的位置
        int start = -1;// 开始真正计数的位置
        int result = 0;// 结果
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') {
                if (start >= 0 || zero >= 0 || sign != 0) {
                    break;
                }
                continue;
            } else if (c == '+' || c == '-') {
                if (start >= 0 || sign != 0) {
                    break;
                }
                sign = c == '+' ? 1 : -1;
                continue;
            } else if (c == '0' && start == -1) {
                if (sign == 0) {
                    sign = 1;
                }
                zero = i;
                continue;
            } else if (c > '9' || c < '0') {
                break;
            }
            // 处理正确的数字
            if (start == -1) {
                start = i;
            }
            // 处理溢出情况
            int overResult = checkWillOver(sign, start, result, i, c);
            if (overResult == -1) {
                result = result * 10 + (c - '0');
            } else {
                return overResult;
            }
        }
        return sign == -1 ? -result : result;
    }

    private int checkWillOver(int sign, int start, int result, int i, char c) {
        if (i - start + 1 > 10) {
            if (sign == -1) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        } else if (i - start + 1 == 10) {
            if (sign == -1) {
                if (-result < Integer.MIN_VALUE / 10) {
                    return Integer.MIN_VALUE;
                } else if (-result == Integer.MIN_VALUE / 10) {
                    if ((c - '0') > (-(Integer.MIN_VALUE % 10))) {
                        return Integer.MIN_VALUE;
                    }
                }
            } else {
                if (result > Integer.MAX_VALUE / 10) {
                    return Integer.MAX_VALUE;
                }
                if (result == Integer.MAX_VALUE / 10) {
                    if ((c - '0') > Integer.MAX_VALUE % 10) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
        }
        return -1;
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
