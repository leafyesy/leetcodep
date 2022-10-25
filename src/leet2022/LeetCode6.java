package src.leet2022;

public class LeetCode6 {

    public static void main(String[] args) {
        LeetCode6 leetCode6 = new LeetCode6();
        String convert = leetCode6.convert("A", 1);
        System.out.println("result:" + convert);
    }

    public String convert(String s, int numRows) {
        if (s.length() <= numRows || numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int diff = ((numRows * 2) - 2);
        for (int i = 1; i <= numRows; i++) {
            int jumpCount = 0;
            if (i == 1 || i == numRows) {
                while (true) {
                    int index = i + diff * jumpCount - 1;
                    if (index >= s.length()) {
                        break;
                    }
                    sb.append(s.charAt(index));
                    jumpCount++;
                }
            } else {
                int start1 = numRows - (numRows - i);
                int start2 = numRows + (numRows - i);
                while (true) {
                    int index1 = start1 + diff * jumpCount - 1;
                    if (index1 >= s.length()) {
                        break;
                    }
                    sb.append(s.charAt(index1));

                    int index2 = start2 + diff * jumpCount - 1;
                    if (index2 >= s.length()) {
                        break;
                    }
                    sb.append(s.charAt(index2));
                    jumpCount++;
                }
            }
        }
        return sb.toString();
    }

}
