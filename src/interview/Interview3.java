package interview;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 字符数组组成的所有字符串
 */
public class Interview3 {

    public static void main(String[] args) {
        String str = "abc";
        char[] chars = str.toCharArray();
        recursionSwap(chars, 0, chars.length);
    }

    //只管字符不相同的情况
    public static void swap(char[] cs, int index1, int index2) {
        char temp = cs[index1];
        cs[index1] = cs[index2];
        cs[index2] = temp;
    }

    public static void recursionSwap(char[] cs, int start, int length) {
        if (start >= length - 1) {
            print(cs);
            return;
        }
        for (int i = start; i < length; i++) {
            swap(cs, start, i);
            recursionSwap(cs, start + 1, length);
            swap(cs, start, i);
        }
    }

//    //直接寻找数字排列
//    public static void printAll(char[] cs) {
//        ArrayList<LinkedList<Integer>> arrayList = new ArrayList<>();
//        int index = 0;
//        for (int start = 0; start < cs.length; start++){
//            LinkedList<Integer> list = new LinkedList<>();
//            index = start;
//            while (list.size() < cs.length) {
//                if (!list.contains(index)) {
//                    list.add(index);
//                } else {
//                    index++;
//                    if (index > cs.length) {
//                        index = 0;
//                    }
//                }
//            }
//            System.out.println(list);
//            arrayList.add(list);
//        }
//
//    }


    public static void print(char[] cs) {
        for (int i = 0; i < cs.length; i++) {
            System.out.print(cs[i]);
        }
        System.out.println();
    }
}
