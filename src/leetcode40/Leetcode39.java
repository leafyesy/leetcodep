package leetcode40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode39 {

    public static void main(String[] args) {
        int[] ints = {2, 3, 5};
        List<List<Integer>> lists = combinationSum(ints, 8);
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.print("[");
            for (Integer integer : list) {
                System.out.print("" + integer + ",");
            }
            System.out.println("],");
        }
        System.out.println("]");
    }


    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> list = new ArrayList<>();
        findIndex(0, candidates, target, list);
        return list;
    }

    /**
     * 返回-1,表示没找到
     **/
    public static void findIndex(int start, int[] candidates, int target, List<List<Integer>> list, int... indexArgs) {
        for (int i = start; i < candidates.length; i++) {
            int candidate = candidates[i];
            if (candidate == target) {
                ArrayList<Integer> integerArrayList = new ArrayList<>();
                for (int indexArg : indexArgs) {
                    integerArrayList.add(indexArg);
                }
                integerArrayList.add(candidate);
                list.add(integerArrayList);
            } else if (candidate < target) {
                int[] newIndexArgs = new int[indexArgs.length + 1];
                System.arraycopy(indexArgs, 0, newIndexArgs, 0, indexArgs.length);
                newIndexArgs[newIndexArgs.length - 1] = candidate;
                findIndex(i, candidates, target - candidate, list, newIndexArgs);
            }
        }
    }


}
