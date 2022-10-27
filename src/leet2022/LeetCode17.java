package src.leet2022;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LeetCode17 {

    public static void main(String[] args) {
        List<String> strings = new LeetCode17().letterCombinations("23");
        for (String string : strings) {
            System.out.println(string);
        }
    }

    private static HashMap<Integer, char[]> sPhoneMap = new HashMap<>();

    static {
        sPhoneMap.put(2, new char[]{'a', 'b', 'c'});
        sPhoneMap.put(3, new char[]{'d', 'e', 'f'});
        sPhoneMap.put(4, new char[]{'g', 'h', 'i'});
        sPhoneMap.put(5, new char[]{'j', 'k', 'l'});
        sPhoneMap.put(6, new char[]{'m', 'n', 'o'});
        sPhoneMap.put(7, new char[]{'p', 'q', 'r', 's'});
        sPhoneMap.put(8, new char[]{'t', 'u', 'v'});
        sPhoneMap.put(9, new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        ArrayList<String> result = new ArrayList<String>();
        if (digits.length() == 0) {
            return result;
        }
        for (int i = 0; i < digits.length(); i++) {
            int key = digits.charAt(i) - '0';
            char[] chars = sPhoneMap.get(key);
            if (chars == null) {
                continue;
            }
            if (chars.length > 0) {
                ArrayList<String> temp = result;
                result = new ArrayList<>();
                for (char aChar : chars) {
                    if (temp.size() == 0) {
                        result.add("" + aChar);
                    } else {
                        for (String s : temp) {
                            result.add(s + aChar);
                        }
                    }
                }
            }
        }
        return result;
    }

}
