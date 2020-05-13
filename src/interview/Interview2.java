package interview;

import java.util.LinkedHashMap;

/**
 * ����һ������õ������һ������������������Ԫ�صĺ͵�����������������
 */
public class Interview2 {


    public static void main(String[] args) {
        int[] ints = new int[]{1, 2, 3, 4, 3, 5, 6};
        findSum(ints, 7);
    }

    private static void findSum(int[] arr, int n) {
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<Integer, Integer>();
        int left, right;
        int sum;
        for (int index = 0; index < arr.length; index++) {
            left = index;
            right = index;
            sum = arr[index];
            if (sum == n) {
                map.put(left, right);
                continue;
            }
            right++;
            while (right < arr.length) {
                sum += arr[right];
                if (sum < n) {
                    right++;
                } else if (sum > n) {
                    break;
                } else {
                    //���
                    map.put(left, right);
                    break;
                }
            }
        }
        System.out.println(map);
    }


}
