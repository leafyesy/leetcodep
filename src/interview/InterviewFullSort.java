package src.interview;

import java.util.*;

public class InterviewFullSort {

    public static void main(String[] args) {
        String calculate = calculate2("4231");
        System.out.println("calculate:" + calculate);
    }

    private static String calculate2(String nums) {
        if (nums.length() == 1) return nums;
        //Deque<Character> deque = new ArrayDeque<>();
        List<Character> list = new ArrayList<>();
        char[] chars = nums.toCharArray();
        int index;
        for (index = nums.length() - 1; index >= 0; index--) {
            if (list.isEmpty()) {
                list.add(chars[index]);
            } else if (list.get(list.size() - 1) < chars[index]) {
                list.add(chars[index]);
            } else {
                break;
            }
        }
        //从单调栈中弹出
        for (int listIndex = 0; listIndex < list.size(); listIndex++) {
            char c = list.get(listIndex);
            if (c > chars[index]) {
                list.remove(listIndex);
                list.add(listIndex, chars[index]);
                swap(chars, index, nums.length() - 1 - listIndex);
            }
        }
        String pre = String.copyValueOf(chars, 0, index);
        StringBuilder sb = new StringBuilder();
        sb.append(pre)
                .append(chars[index]);
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }


    private static String calculate(String nums) {
        if (nums.length() == 1) return nums;
        char[] array = nums.toCharArray();
        for (int i = nums.length() - 2; i >= 0; i--) {
            char t = array[i];
            for (int j = nums.length() - 1; j > i; j--) {
                char c = array[j];
                System.out.println("t:" + t + " c:" + c);
                if (c > t) {
                    //先交换i和j的位置
                    swap(array, i, j);
                    //把j的数据放到指定位置
                    String pre = String.copyValueOf(array, 0, i);
                    String tail = String.copyValueOf(sort(array, i + 1));
                    return pre + array[i] + tail;
                }
            }
        }
        return nums;
    }

    //对数据进行排序
    private static char[] sort(char[] arr, int index) {
        if (index >= arr.length - 1) return new char[]{arr[index]};
        char[] newArr = new char[arr.length - index];
        System.arraycopy(arr, index, newArr, 0, arr.length - index);
        //排序
        Arrays.sort(newArr);
//        for (int i = 0; i < newArr.length; i++) {
//            for (int j = i; j < newArr.length; j++) {
//                if (newArr[i] > newArr[j]) {
//                    swap(newArr, i, j);
//                }
//            }
//        }
        return newArr;
    }

    private static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }


}
