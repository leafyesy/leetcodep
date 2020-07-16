package src.interview;

import src.utils.Utils;

import java.util.*;

public class Interview1713 {

    public static void main(String[] args) {
        String[] d = new String[]{
                "abc", "de", "fg"
        };
        String str = "abcdeddfg";
        respace(d, str);
    }


    public static int respace(String[] dictionary, String sentence) {
        int[] dp = new int[sentence.length() + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= sentence.length(); i++) {
            for (String t : dictionary) {
                if (t.length() > i) {
                    continue;
                }
                String str = sentence.substring(i - t.length(), i);
                System.out.println("str:" + str + " t:" + t);
                if (equalsStr(str, t)) {
                    dp[i] = Math.min(dp[i], dp[i - t.length()]);
                }
            }
            if (dp[i] == Integer.MAX_VALUE) {
                dp[i] = dp[i - 1] + 1;
            }
        }
        Utils.printIntArr(dp);
        return dp[sentence.length()];
    }

    private static boolean equalsStr(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        if (str1.length() != str2.length()) return false;
        for (int i = 0; i < str1.length(); i++) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(i);
            if (c1 == c2) {
                continue;
            }
            return false;
        }
        return true;
    }


    class Solution {
        public int respace(String[] dictionary, String sentence) {
            HashMap<Integer, List<String>> cache = new HashMap<>();
            List<Integer> countList = dictionaryToCountStringMap(cache, dictionary);
            //开始进行遍历
            return startRespace(sentence, 0, cache, countList);
        }

        private int startRespace(String sentence, int start, HashMap<Integer, List<String>> cache, List<Integer> countList) {
            int count = 0;
            for (int index = start; index < sentence.length(); ) {
                if (index + countList.get(0) > sentence.length()) {
                    count += (sentence.length() - index - 1);
                    break;
                }
                int min = 0;
                boolean isExit = false;
                for (int j = countList.size() - 1; j >= 0; j--) {
                    int end = index + countList.get(j);
                    if (end > sentence.length()) {
                        continue;
                    }
                    String str = sentence.substring(index, end);
                    List<String> targetStrList = cache.get(countList.get(j));
                    if (targetStrList != null) {
                        for (String s : targetStrList) {
                            if (equalsStr(s, str)) {
                                int temp = startRespace(sentence, index + countList.get(j), cache, countList);
                                if (temp <= min) {

                                }
                            }
                        }
                    }


//                    List<String> stringList = equalsStrList(targetStrList, str);
//
//                    if (!stringList.isEmpty()) {
//                        index += countList.get(j);
//                        isExit = true;
//                        break;
//                    }
                }
//                if (!isExit) {
//                    index++;
//                    count++;
//                }
            }
            return count;
        }

        //把数据存入hashMap中 并获取单词的最小长度和最大长度
        private List<Integer> dictionaryToCountStringMap(HashMap<Integer, List<String>> cache, String[] dictionary) {
            Set<Integer> countSet = new HashSet();
            for (String s : dictionary) {
                List<String> list = cache.get(s.length());
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(s);
                cache.put(s.length(), list);
                countSet.add(s.length());
            }
            List<Integer> list = new ArrayList<>(countSet);
            Collections.sort(list);
            return list;
        }

        private List<String> equalsStrList(List<String> strList, String str) {
            List<String> result = new ArrayList<>();
            for (String target : strList) {
                if (equalsStr(str, target)) {
                    result.add(str);
                }
            }
            return result;
        }

        private boolean equalsStr(String str1, String str2) {
            if (str1 == null || str2 == null) return false;
            if (str1.length() != str2.length()) return false;
            for (int i = 0; i < str1.length(); i++) {
                char c1 = str1.charAt(i);
                char c2 = str2.charAt(i);
                if (c1 == c2) {
                    continue;
                }
                return false;
            }
            return true;
        }

    }

}
