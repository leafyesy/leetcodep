public class LeetCode6 {

    public static void main(String[] args) {
//        Solution solution = new Solution();
//        String test = "ABC";
//        System.out.print(solution.convert(test, 1));
        S s = new S();
    }

    static class F {
        //静态变量value
        public static int value = 11111;
        public static final int fValue = 33333;

        {
            System.out.println("f 1");
            System.out.println("s :"+value);
            System.out.println("s :"+fValue);
        }
        static {
            System.out.println("f 2");
            System.out.println("s :"+value);
            System.out.println("s :"+fValue);
        }

        F(){
            System.out.println("f 3");
            System.out.println("s :"+value);
            System.out.println("s :"+fValue);
        }
    }

    static class S extends F{
        //静态变量value
        public static int value = 22222;
        public static final int sValue = 44444;
        {
            System.out.println("s 1");
            System.out.println("s :"+value);
            System.out.println("s :"+sValue);
        }
        static {
            System.out.println("s 2");
            System.out.println("s :"+value);
            System.out.println("s :"+sValue);
        }

        S(){
            System.out.println("s 3");
            System.out.println("s :"+value);
            System.out.println("s :"+sValue);
        }
    }

    static class Solution {
        public String convert(String s, int numRows) {
            if (s == null || s.length() == 0) return "";
            if (s.length() == 1) return s;
            if (s.length() == 2) return s;
            if (numRows == 1) return s;
            int step = numRows * 2 - 2;
            int index;
            int left, right;
            StringBuilder ret = new StringBuilder();
            for (int i = 0; i < numRows; i++) {
                for (index = 0; index < s.length() + step; index += step) {
                    left = index - i;
                    right = index + i;
                    if (left >= 0 && left < s.length()) {
                        ret.append(s.charAt(left));
                    }
                    if (left != right && i < numRows - 1) {
                        if (right < s.length()) {
                            ret.append(s.charAt(right));
                        }
                    }
                }
            }
            return ret.toString();
        }
    }


}
