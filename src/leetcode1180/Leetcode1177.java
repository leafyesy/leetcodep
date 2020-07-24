package src.leetcode1180;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Leetcode1177 {

    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> result = new ArrayList<>();
        List<CharCount> cacheList = new ArrayList<>();
        CharCount pre = new CharCount();
        cacheList.add(pre);
        for (int i = 0; i < s.length(); i++) {
            pre = pre.copy();
            pre.add(s.charAt(i));
            cacheList.add(pre);
        }
        for (int[] query : queries) {
            result.add(isCanMakePaliQuerie(s, query, cacheList));
        }
        return result;
    }

    private boolean isCanMakePaliQuerie(String s, int[] querie, List<CharCount> cacheList) {
        int left = querie[0];
        int right = querie[1];
        int k = querie[2];
        int length = right - left + 1;
        if (length == 1) {
            return true;
        }
        int doubleK = 2 * k;
        boolean isJishu = length % 2 == 1;
        int maxRemainCount = doubleK + (isJishu ? 1 : 0);
        CharCount leftCharCount = cacheList.get(left);
        CharCount rightCharCount = cacheList.get(right + 1);
        int remainCount = CharCount.remainCount(leftCharCount, rightCharCount);
        return maxRemainCount >= remainCount;
    }

    static class CharCount {
        int[] charCountArr = new int[26];

        CharCount() {

        }

        CharCount(int[] charCountArr) {
            System.arraycopy(charCountArr, 0, this.charCountArr, 0, 26);
        }

        public void add(char ch) {
            charCountArr[ch - 'a'] = charCountArr[ch - 'a'] + 1;
        }

        public CharCount copy() {
            CharCount c = null;
            try {
                c = (CharCount) clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return c;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return new CharCount(this.charCountArr);
        }

        public static int remainCount(CharCount left, CharCount right) {
            int remainCount = 0;
            for (int i = 0; i < left.charCountArr.length; i++) {
                remainCount += ((right.charCountArr[i] - left.charCountArr[i]) % 2);
            }
            return remainCount;
        }
    }

}
