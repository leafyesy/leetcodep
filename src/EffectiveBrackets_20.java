import java.util.Stack;

public class EffectiveBrackets_20 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String test = "{}[()";
        System.out.print(solution.isValid(test));
    }


    static class Solution {
        public boolean isValid(String s) {
            if (s.equals("")) {
                return true;
            }
            Stack<Character> stack = new Stack<Character>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == '(' || c == '[' || c == '{') {
                    stack.push(c);
                    continue;
                }
                if (stack.isEmpty()) {
                    return false;
                }
                char p = stack.pop();
                if (c == ')' && p != '(') {
                    return false;
                } else if (c == ']' && p != '[') {
                    return false;
                } else if (c == '}' && p != '{') {
                    return false;
                }
            }
            return stack.isEmpty();
        }
    }


}
