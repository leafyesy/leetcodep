package src.offer;

import src.utils.Utils;

import java.util.*;

public class LongestSubstring {

    public static void main(String[] args) {
        //System.out.println("result:" + longestCommonPrefix(new String[]{""}));
        System.out.println("result:" + checkInclusion("abcdxabcde", "abcdeabcdx"));
    }

    public int maxPoints(int[][] points) {
        if(points == null) return 0;
        if(points.length == 1) return 1;
        int max = 0;
        HashMap<Line, List<Point>> cache = new HashMap<>();
        HashMap<Integer, Point> pointCache = new HashMap<>();

        for (int k = 0; k < points.length; k++) {
            Point point = new Point(points[k][0], points[k][1]);
            pointCache.put(k, point);
        }

        for (int i = 0; i < points.length - 1; i++) {
            Point point1 = pointCache.get(i);
            for (int j = i + 1; j < points.length; j++) {
                Point point2 = pointCache.get(j);
                Line newLine = new Line(point1, point2);
                List<Point> pointList = cache.get(newLine);
                if (pointList == null) {
                    //表示这是一条新的线
                    pointList = new ArrayList<>();
                    pointList.add(point1);
                    //验证所有的点
                    int count = i+1;
                    while(count<points.length){
                        Point point = pointCache.get(count);
                        //System.out.println("count:"+count + "isOnLine:"+newLine.isOnLine(point));
                        if (count == j || newLine.isOnLine(point)){
                            pointList.add(point);
                        }
                        count++;
                    }
                    cache.put(newLine, pointList);
                    if (pointList.size() > max) {
                        max = pointList.size();
                    }
                }
            }
        }
        return max;
    }

    private class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    private class Line {

        public int h1;//表示y的差
        public int w1;//表示x的差

        Point p1;
        Point p2;

        boolean isVLine = false;
        private int vLineX = 0;

        boolean isHLine = false;
        private int hLineY = 0;

        private boolean isDot = false;
        private int dotX = 0;
        private int dotY = 0;

        Line(Point point1, Point point2) {
            int x1 = point1.x;
            int y1 = point1.y;
            int x2 = point2.x;
            int y2 = point2.y;
            if (x1 == x2 && y1 == y2) {
                isDot = true;
                dotX = x1;
                dotY = y1;
                return;
            }
            if (x2 == x1) {
                isVLine = true;
                vLineX = x1;
            } else if (y2 == y1) {
                isHLine = true;
                hLineY = y1;
            } else {
                h1 = y2 - y1;
                w1 = x2 - x1;
                p1 = point1;
                p2 = point2;
                calMin(this);
            }
        }

        public boolean isOnLine(Point p) {
            if (isDot) {
                return p.x == dotX && p.y == dotY;
            }
            if (isVLine) {
                return p.x == vLineX;
            }
            if (isHLine) {
                return p.y == hLineY;
            }
            if (p1.equals(p) || p2.equals(p)) {
                return true;
            }
            Line newLine = new Line(p, p1);
            return newLine.h1 == h1 && newLine.w1 == w1;
        }

        private int calMin(int n, int m) {
            while (n != 0) {
                int r = m % n;
                m = n;
                n = r;
            }
            return m;
        }

        private void calMin(Line line) {
            int result = calMin(line.w1,line.h1);
            if (result != 0){
                line.w1 = line.w1 / result;
                line.h1 = line.h1/ result;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Line line = (Line) o;
            return h1 == line.h1 &&
                    w1 == line.w1 &&
                    isVLine == line.isVLine &&
                    vLineX == line.vLineX &&
                    isHLine == line.isHLine &&
                    hLineY == line.hLineY &&
                    isDot == line.isDot &&
                    dotX == line.dotX &&
                    dotY == line.dotY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(h1, w1, isVLine, vLineX, isDot, dotX, dotY);
        }
    }


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null) return "";
        if (strs.length == 1) return strs[0];
        int index = 0;
        boolean isBreak = false;
        while (true) {
            if (strs[0] == null || strs[0].length() <= index) break;
            char temp = strs[0].charAt(index);
            for (int i = 1; i < strs.length; i++) {
                String str = strs[i];
                if (str == null) {
                    isBreak = true;
                    break;
                }
                if (str.length() <= index) {
                    isBreak = true;
                    break;
                }
                char ic = str.charAt(index);
                if (ic != temp) {
                    isBreak = true;
                    break;
                }
            }
            if (isBreak) break;
            index++;
        }
        if (index == 0) return "";
        return strs[0].substring(0, index);
    }

    public static boolean checkInclusion(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s2 == null) return false;
        if (s1 == null) return true;
        if (s1.length() == 0) return true;
        if (s1.length() > s2.length()) return false;
        StrInfo s1Info = new StrInfo();
        for (int index = 0; index < s1.length(); index++) {
            s1Info.inc(s1.charAt(index));
        }
        StrInfo s2Info = new StrInfo();
        for (int index = 0; index < s2.length(); index++) {
            s2Info.inc(s2.charAt(index));
            if (index < s1.length() - 1) {
                continue;
            }
            if (s2Info.equals(s1Info)) {
                return true;
            }
            s2Info.des(s2.charAt(index - s1.length() + 1));
        }
        return false;
    }

    public static boolean checkInclusion2(String s1, String s2) {
        if (s1 == null && s2 == null) return true;
        if (s2 == null) return false;
        if (s1 == null) return true;
        if (s1.length() == 0) return true;
        if (s1.length() > s2.length()) return false;
        HashMap<Character, List<Integer>> s1Cache = new HashMap<>();//用于存储s1
        for (int s1Index = 0; s1Index < s1.length(); s1Index++) {
            char c = s1.charAt(s1Index);
            List<Integer> indexList = s1Cache.get(c);
            if (indexList == null) {
                indexList = new ArrayList<>();
            }
            indexList.add(s1Index);
            s1Cache.put(s1.charAt(s1Index), indexList);
        }
        for (int i = 0; i < s2.length() - s1.length() + 1; i++) {
            char s2Char = s2.charAt(i);
            List<Integer> indexList = s1Cache.get(s2Char);
            if (indexList == null || indexList.size() == 0) {
                if (s2.length() - i <= s1.length()) {
                    return false;
                }
            } else {
                //存在
                if (isMatching(s1, s2, s1Cache, i, s2Char, indexList)) return true;
            }
        }
        return false;
    }

    private static boolean isMatching(String s1, String s2, HashMap<Character, List<Integer>> s1Cache, int i, char s2Char, List<Integer> indexList) {
        Character[] cArr = new Character[s1.length()];
        cArr[indexList.get(0)] = s2Char;
        //取出后续的s1.length位char,进行比较
        String compareStr = s2.substring(i + 1, i + s1.length());
        System.out.println("compareStr:" + compareStr);
        for (int j = 0; j < compareStr.length(); j++) {
            char c = compareStr.charAt(j);
            List<Integer> cIndexList = s1Cache.get(c);
            if (cIndexList == null) {
                return false;
            }
            boolean isHasIndex = false;
            for (Integer index : cIndexList) {
                if (cArr[index] == null) {
                    isHasIndex = true;
                    cArr[index] = c;
                    break;
                }
            }
            if (!isHasIndex) {
                return false;
            }
            Utils.printCharArr(cArr);
        }
        return true;
    }

    static class StrInfo {

        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        int e = 0;
        int f = 0;
        int g = 0;
        int h = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int m = 0;
        int n = 0;
        int o = 0;
        int p = 0;
        int q = 0;
        int r = 0;
        int s = 0;
        int t = 0;
        int u = 0;
        int v = 0;
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;

        public void inc(char c) {
            switch (c) {
                case 'a':
                    a++;
                    break;
                case 'b':
                    b++;
                    break;
                case 'c':
                    c++;
                    break;
                case 'd':
                    d++;
                    break;
                case 'e':
                    e++;
                    break;
                case 'f':
                    f++;
                    break;
                case 'g':
                    g++;
                    break;
                case 'h':
                    h++;
                    break;
                case 'i':
                    i++;
                    break;
                case 'j':
                    j++;
                    break;
                case 'k':
                    k++;
                    break;
                case 'l':
                    l++;
                    break;
                case 'm':
                    m++;
                    break;
                case 'n':
                    n++;
                    break;
                case 'o':
                    o++;
                    break;
                case 'p':
                    p++;
                    break;
                case 'q':
                    q++;
                    break;
                case 'r':
                    r++;
                    break;
                case 's':
                    s++;
                    break;
                case 't':
                    t++;
                    break;
                case 'u':
                    u++;
                    break;
                case 'v':
                    v++;
                    break;
                case 'w':
                    w++;
                    break;
                case 'x':
                    x++;
                    break;
                case 'y':
                    y++;
                    break;
                case 'z':
                    z++;
                    break;
            }
        }

        public void des(char c) {
            switch (c) {
                case 'a':
                    a--;
                    break;
                case 'b':
                    b--;
                    break;
                case 'c':
                    c--;
                    break;
                case 'd':
                    d--;
                    break;
                case 'e':
                    e--;
                    break;
                case 'f':
                    f--;
                    break;
                case 'g':
                    g--;
                    break;
                case 'h':
                    h--;
                    break;
                case 'i':
                    i--;
                    break;
                case 'j':
                    j--;
                    break;
                case 'k':
                    k--;
                    break;
                case 'l':
                    l--;
                    break;
                case 'm':
                    m--;
                    break;
                case 'n':
                    n--;
                    break;
                case 'o':
                    o--;
                    break;
                case 'p':
                    p--;
                    break;
                case 'q':
                    q--;
                    break;
                case 'r':
                    r--;
                    break;
                case 's':
                    s--;
                    break;
                case 't':
                    t--;
                    break;
                case 'u':
                    u--;
                    break;
                case 'v':
                    v--;
                    break;
                case 'w':
                    w--;
                    break;
                case 'x':
                    x--;
                    break;
                case 'y':
                    y--;
                    break;
                case 'z':
                    z--;
                    break;
            }
        }

        @Override
        public boolean equals(Object o1) {
            if (this == o1) return true;
            if (o1 == null || getClass() != o1.getClass()) return false;
            StrInfo strInfo = (StrInfo) o1;
            return a == strInfo.a &&
                    b == strInfo.b &&
                    c == strInfo.c &&
                    d == strInfo.d &&
                    e == strInfo.e &&
                    f == strInfo.f &&
                    g == strInfo.g &&
                    h == strInfo.h &&
                    i == strInfo.i &&
                    j == strInfo.j &&
                    k == strInfo.k &&
                    l == strInfo.l &&
                    m == strInfo.m &&
                    n == strInfo.n &&
                    o == strInfo.o &&
                    p == strInfo.p &&
                    q == strInfo.q &&
                    r == strInfo.r &&
                    s == strInfo.s &&
                    t == strInfo.t &&
                    u == strInfo.u &&
                    v == strInfo.v &&
                    w == strInfo.w &&
                    x == strInfo.x &&
                    y == strInfo.y &&
                    z == strInfo.z;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z);
        }
    }


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length <= 2) return result;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i >= 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = nums.length - 1; j > i + 1; j--) {
                int threeIndex = halfSearch(nums, i + 1, j - 1, -nums[i] - nums[j]);
                if (threeIndex != -1) {
                    List<Integer> per = new ArrayList<>();
                    per.add(nums[i]);
                    per.add(threeIndex);
                    per.add(nums[j]);
                    result.add(per);
                }
            }
        }
        return result;
    }

    private int halfSearch(int[] nums, int start, int end, int target) {
        if (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
            return halfSearch(nums, start, end, target);
        }
        return -1;
    }


    class Solution {
        public List<List<Integer>> threeSum(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            if (nums == null || nums.length <= 2) return result;
            Arrays.sort(nums);
            HashMap<Integer, ArrayList<PositionInfo>> cache = new HashMap<>();
            for (int i = 1; i < nums.length - 1; i++) {
                if (i > 1 && nums[i] == nums[i - 1]) {
                    continue;
                }
                for (int j = i + 1; j < nums.length; j++) {
                    if (i > 2 && nums[j] == nums[j - 1]) {
                        continue;
                    }
                    int target = nums[i] + nums[j];
                    ArrayList<PositionInfo> positionInfos = cache.get(target);
                    PositionInfo info = new PositionInfo(i, nums[i], j, nums[j]);
                    if (positionInfos == null) {
                        positionInfos = new ArrayList<>();
                    }
                    positionInfos.add(info);
                    System.out.println("per:" + nums[j] + " " + nums[i]);
                    cache.put(nums[i] + nums[j], positionInfos);
                }
            }
            for (int j = 0; j < nums.length - 2; j++) {
                if (nums[j] > 0) break;
                //这里要跳过相同项
                if (j > 0 && nums[j] == nums[j - 1]) {
                    continue;
                }
                twoSum(nums[j], j, result, cache);
            }
            return result;
        }

        private void twoSum(int one, int start, List<List<Integer>> list, HashMap<Integer, ArrayList<PositionInfo>> cache) {
            System.out.println("one:" + one + " target:" + -one);
            ArrayList<PositionInfo> resultList = cache.get(-one);
            if (resultList != null) {
                for (PositionInfo positionInfo : resultList) {
                    System.out.println("positionInfo:" + positionInfo);
                    if (positionInfo.index1 > start && positionInfo.index2 > start) {
                        List<Integer> result = new ArrayList<>();
                        result.add(one);
                        result.add(positionInfo.value1);
                        result.add(positionInfo.value2);
                        list.add(result);
                    }
                }
            }
        }

        private class PositionInfo {
            int index1;
            int value1;

            int index2;
            int value2;

            private PositionInfo(int index1, int value1,
                                 int index2, int value2) {
                this.index1 = index1;
                this.value1 = value1;
                this.index2 = index2;
                this.value2 = value2;
            }

            @Override
            public String toString() {
                return "PositionInfo{" +
                        "index1=" + index1 +
                        ", value1=" + value1 +
                        ", index2=" + index2 +
                        ", value2=" + value2 +
                        '}';
            }
        }
    }
}
