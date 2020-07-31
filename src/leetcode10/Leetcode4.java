package src.leetcode10;

import src.utils.Utils;

import java.util.*;

public class Leetcode4 {
    public static void main(String[] args) {
        int[] arr2 = new int[]{5, 6, 7, 8, 9, 10};
        int[] arr1 = new int[]{1, 2, 3, 4};
        List<List<Integer>> lists = combinationSum(arr1, 4);
        for (List<Integer> list : lists) {
            Utils.printList(list);
        }

//        double result;
//        result = findMedianSortedArrays(arr2, arr1);
//        System.out.println("result:" + result);
    }

    public int hammingDistance(int m, int n) {
        int count = 0;
        int tempM = m;
        int tempN = n;
        while (tempM != tempN) {
            if ((tempM & 0x1) == (tempN & 0x1)) {
                count++;
            }
            tempM = tempM >> 1;
            tempN = tempN >> 1;
        }
        int i = 0b1111;
        return count;
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        Arrays.sort(candidates);
        if (target < candidates[0]) {
            return result;
        }
        Item[] dp = new Item[target + 1];
        for (int k = 0; k < dp.length; k++) {
            dp[k] = new Item();
        }
        for (int v = 1; v <= target; v++) {
            for (int candidate : candidates) {
                if (candidate == v) {
                    List<Integer> list = new ArrayList<>();
                    list.add(candidate);
                    dp[v].set.add(list);
                    //dp[v].list.add(list);
                } else if (candidate < v) {
                    if (!dp[v - candidate].set.isEmpty()) {
                        Item preItem = dp[v - candidate];
                        for (List<Integer> list : preItem.set) {
                            List<Integer> newList = new ArrayList<>(list);
                            newList.add(candidate);
                            Collections.sort(newList);
                            dp[v].set.add(newList);
                        }
                    }
                    continue;
                }
                break;
            }
        }
        return new ArrayList<>(dp[target].set);
    }

    static class Item {
        Set<List<Integer>> set = new HashSet<>();
    }


    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        return arraySplit(nums1, nums2);
    }

    private static double getMiddleArr(int[] arr) {
        int i = arr.length & 1;
        if (i == 0) {
            return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2.0;
        } else {
            return arr[arr.length / 2];
        }
    }

    private static double arraySplit(int[] nums1, int[] nums2) {
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0 && length2 == 0) {
            return 0;
        } else if (length1 == 0) {
            return getMiddleArr(nums2);
        } else if (length2 == 0) {
            return getMiddleArr(nums1);
        }
        if (length1 < length2) {
            return arraySplit(nums2, nums1);
        }

        System.out.println("length1:" + length1 + " length2:" + length2);
        int left = 0;
        int right = length1;
        int leftMax = 0;
        int rightMin = 0;
        int leftSize = 0;
        int rightSize = 0;
        while (left <= right) {
            System.out.println("left:" + left + " right:" + right);
            int mid = (left + right) / 2;
            int secondIndex = (length1 + length2) / 2 - mid - 1;
            System.out.println("mid:" + mid + " secondIndex:" + secondIndex);

            int firstLeftMax = mid >= 0 ? nums1[mid] : Integer.MIN_VALUE;
            int firstRightMin = mid + 1 < length1 ? mid < 0 ? nums1[0] : nums1[mid + 1] : Integer.MAX_VALUE;
            int secondLeftMax = secondIndex >= 0 ? nums2[secondIndex] : Integer.MIN_VALUE;
            int secondRightMin = secondIndex + 1 < length2 ? (secondIndex < 0 ? nums2[0] : nums2[secondIndex + 1]) : Integer.MAX_VALUE;
            leftMax = Math.max(firstLeftMax, secondLeftMax);
            rightMin = Math.min(firstRightMin, secondRightMin);
            leftSize = mid + secondIndex + 2;
            rightSize = length1 - mid + length2 - secondIndex - 2;
            System.out.println("mid:" + mid + " secondIndex:" + secondIndex);
            System.out.println("leftSize:" + leftSize + " rightSize:" + rightSize);
            if (leftMax < rightMin && Math.abs(leftSize - rightMin) <= 1) {
                break;
            } else {
                if (firstLeftMax > rightMin) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
                System.out.println("leftMax:" + leftMax + " rightMin:" + rightMin);
            }
        }
        System.out.println("leftMax:" + leftMax + " rightMin:" + rightMin);
        return (length1 + length2) % 2 == 0 ? (leftMax + rightMin) / 2.0 : leftSize > rightSize ? leftMax : rightMin;
    }

    private static int findIndex(int value, int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        System.out.println("1 left:" + left + " right:" + right);
        if (arr[left] > value) return 0;
        System.out.println("2 left:" + left + " right:" + right);
        if (arr[right] < value) return arr.length - 1;
        int middle;
        while (left < right) {
            middle = (left + right) / 2;
            System.out.println("left:" + left + " right:" + right + " middle:" + middle);
            if (arr[middle] == value) {
                return middle;
            } else if (arr[middle] < value) {
                if (middle + 1 < arr.length && arr[middle + 1] > value) {
                    return middle + 1;
                } else {
                    left = middle + 1;
                }
            } else {
                if (middle - 1 >= 0 && arr[middle - 1] < value) {
                    return middle;
                } else {
                    right = middle - 1;
                }
            }
        }
        return 0;
    }

    private static double findMedian(int[] arr) {
        int i = arr.length % 2;
        if (i == 0) {
            return (arr[arr.length / 2] + arr[arr.length / 2 - 1]) / 2.0;
        } else {
            return arr[arr.length / 2];
        }
    }


    private static double guibin(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        //进行归并排序
        sort(arr, 0, arr.length - 1);
        Utils.printIntArr(arr);
        return findMedian(arr);
    }

    private static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            sort(arr, left, middle);
            sort(arr, middle + 1, right);
            merge(arr, left, right, middle);
        }
    }

    private static void merge(int[] arr, int left, int right, int middle) {
        int leftIndex = left;
        int rightIndex = middle + 1;
        int[] tempArr = new int[right - left + 1];
        int tempIndex = 0;
        while (leftIndex <= middle && rightIndex <= right) {
            if (arr[leftIndex] < arr[rightIndex]) {
                tempArr[tempIndex++] = arr[leftIndex++];
            } else {
                tempArr[tempIndex++] = arr[rightIndex++];
            }
        }
        while (leftIndex <= middle) {
            tempArr[tempIndex++] = arr[leftIndex++];
        }
        while (rightIndex <= right) {
            tempArr[tempIndex++] = arr[rightIndex++];
        }
        tempIndex = 0;
        for (int i : tempArr) {
            arr[left + tempIndex++] = i;
        }
    }

}
