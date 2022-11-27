package src.leet2022;

import java.util.Stack;

public class LeetCode20 {

    public static void main(String[] args) {
        boolean result = new LeetCode20().isValid("([)[]{}");
        System.out.println("result: " + result);
    }

    private static final char PARENTHESIS_LEFT = '(';
    private static final char PARENTHESIS_RIGHT = ')';
    private static final char BRACKET_LEFT = '[';
    private static final char BRACKET_RIGHT = ']';
    private static final char BRACE_LEFT = '{';
    private static final char BRACE_RIGHT = '}';

    public boolean isValid(String s) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (isLeftChar(s.charAt(i))) { // 判断是否左边的字符
                stack.add(s.charAt(i));
                continue;
            } else if (stack.size() > 0 && isPairs(stack.get(stack.size() - 1), s.charAt(i))) { // 判断是否成对
                stack.pop();
                continue;
            }
            return false;
        }
        return stack.size() == 0;
    }

    private boolean isLeftChar(char c) {
        return c == PARENTHESIS_LEFT || c == BRACKET_LEFT || c == BRACE_LEFT;
    }

    private boolean isPairs(Character left, char right) {
        return (left == PARENTHESIS_LEFT && right == PARENTHESIS_RIGHT)
                || (left == BRACKET_LEFT && right == BRACKET_RIGHT)
                || (left == BRACE_LEFT && right == BRACE_RIGHT);
    }


}
