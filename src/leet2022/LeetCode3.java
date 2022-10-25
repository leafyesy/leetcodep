package src.leet2022;

import java.util.*;

public class LeetCode3 {

    public int lengthOfLongestSubstring_hash(String s) {
        if (s.length() == 1) {
            return 1;
        }
        int left = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int maxLen = 0;
        while (left < s.length()) {
            char leftVal = chars[left];
            Integer index = hashMap.get(leftVal);
            if (index == null) {
                hashMap.put(leftVal, left);
                maxLen = Math.max(hashMap.size(), maxLen);
                left++;
            } else {
                left = index + 1;
                if (left + maxLen > s.length()) {
                    break;
                }
                // 存在
                hashMap.clear();
            }
        }
        return maxLen;
    }

    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int maxLen = 0;
        while (right < s.length()) {
            char leftVal = chars[right];
            Integer index = hashMap.get(leftVal);
            if (index == null) { // map中没存储
                hashMap.put(leftVal, right);
                maxLen = Math.max(hashMap.size(), maxLen);
                right++;
            } else {
                for (int i = left; i <= index; i++) {
                    hashMap.remove(chars[i]);
                }
                left = index + 1;
                if (left + maxLen > s.length()) {
                    break;
                }
            }
        }
        return maxLen;
    }


    public int lengthOfLongestSubstring_2(String s) {
        int start = 0;
        char[] chars = s.toCharArray();
        int maxLen = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>(chars.length);
        while (start + maxLen < s.length()) {
            int index = start;

            while (index < s.length()) {
                char leftVal = chars[index];
                Integer indexValue = hashMap.get(leftVal);
                if (indexValue == null) {
                    hashMap.put(leftVal, index);
                    maxLen = Math.max(hashMap.size(), maxLen);
                    index++;
                } else {
                    hashMap.clear();
                    start = indexValue + 1;
                    break;
                }
            }
        }
        return maxLen;
    }


    public int lengthOfLongestSubstring_x(String s) {
        int start = 0;
        int maxLen = 0;
        HashMap<Character, Integer> hashMap = new HashMap<>(s.length());
        while (start + maxLen < s.length()) {
            int index = start;
            while (index < s.length()) {
                char charAt = s.charAt(index);
                Integer indexValue = hashMap.get(charAt);
                if (indexValue == null) {
                    hashMap.put(charAt, index);
                    maxLen = Math.max(hashMap.size(), maxLen);
                    index++;
                } else {
                    hashMap.clear();
                    start = indexValue + 1;
                    break;
                }
            }
        }
        return maxLen;
    }

}
