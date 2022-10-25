package src.leet2022;

public class LeetCode7 {

    public static void main(String[] args) {
        //2147483647
        int reverse = new LeetCode7().reverse(-2147483648);
        System.out.println("reverse:" + reverse);
    }


    public int reverse(int x) {
        int len = ("" + x).length();
        if (len == 1) { // 边界判断
            return x;
        }
        int maxLen = 10; // 取最大位数
        boolean overZero = x > 0;
        if (!overZero) {
            len -= 1;
        }
        int result = 0;
        int step = 1;
        boolean isNeedJudgeOverMaxMinValue = true;
        while (step <= len) {
            int remainder = getIndexNum(x, step);
            if (isNeedJudgeOverMaxMinValue) {
                int calNum = 0;
                if (overZero) { // 判断是否超出
                    calNum = Integer.MAX_VALUE;
                } else { // 判断是否太小
                    calNum = Integer.MIN_VALUE;
                }
                if (len == maxLen) {
                    int maxRemainder = getIndexNum(calNum, len - step + 1);
                    if (remainder > maxRemainder) {
                        return 0;
                    } else if (remainder < maxRemainder) {
                        isNeedJudgeOverMaxMinValue = false;
                    }
                }
            }
            // 处理溢出的情况
            result += remainder * pow(len - step);
            step++;
        }
        if (!overZero) {
            result = -result;
        }
        return result;
    }

    public int getIndexNum(int x, int index) {
        return Math.abs(x / pow(index - 1) % 10);
    }

    public int pow(int times) {
        if (times == 0) {
            return 1;
        }
        int result = 10;
        if (times == 1) {
            return result;
        }
        for (int i = 1; i < times; i++) {
            result *= 10;
        }
        return result;
    }

}
