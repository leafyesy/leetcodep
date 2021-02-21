package leetcode40;

import src.utils.Utils;

import java.util.*;

public class Leetcode39 {

    public static void main(String[] args) {
        int[] ints = {8, 7, 4, 3};
        List<List<Integer>> lists = combinationSum2(ints, 11);
        System.out.println("[");
        int n = 4096;
        int i = n & 0x55555555;
        System.out.println("" + i);

        for (List<Integer> list : lists) {
            Utils.printList(list);
        }
    }


    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        Item[] dp = new Item[target + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = new Item();
        }
        for (int i = 1; i <= target; i++) {
            for (int candidate : candidates) {
                int temp = 0;
                while (true) {
                    int newValue = temp + candidate;
                    System.out.println(">>temp:" + temp + " candidate:" + candidate + " newValue:" + newValue);
                    if (newValue == i) {
                        System.out.println("temp:" + temp + " candidate:" + candidate + " newValue:" + newValue);
                        addToResult(dp, i, i - candidate, candidate);
                        temp = newValue;
                        continue;
                    }
                    break;
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        Collections.sort(new ArrayList<>(set));
        System.out.println("-----------------------------");
        for (Item item : dp) {
            Utils.printList(item.list);
        }
        System.out.println("-----------------------------");
        return dp[target].list;
    }

    private static void addToResult(Item[] dp, int target, int pre, int candidate) {
        System.out.println("target:" + target + " pre:" + pre);
        Item targetItem = dp[target];
        Item preItem = dp[pre];
        if (preItem.list.isEmpty() || targetItem == preItem) {
            List<Integer> list = new ArrayList<>();
            list.add(candidate);
            targetItem.list.add(list);
        } else {
            for (List<Integer> intList : preItem.list) {
                List<Integer> list = new ArrayList<>(intList);
                list.add(candidate);
                targetItem.list.add(list);
            }
        }
    }

    static class Item {
        List<List<Integer>> list = new ArrayList<>();
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
