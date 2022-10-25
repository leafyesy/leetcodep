package src.leet2022;

public class LeetCode4 {


    public static void main(String[] args) {
        double medianSortedArrays = findMedianSortedArrays(new int[]{1, 3}, new int[]{2, 5, 6, 7});
//        double medianSortedArrays = findMedianSortedArrays(new int[]{3}, new int[]{-2, -1});
        System.out.println("medianSortedArrays:" + medianSortedArrays);
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] numsArr = new int[nums1.length + nums2.length];
        int numsArrL = 0;
        int numsArrR = numsArr.length - 1;
        int n1L = 0;
        int n1R = nums1.length - 1;
        int n2L = 0;
        int n2R = nums2.length - 1;
        while (numsArrL <= numsArrR) {
            Integer n1LV = null;
            Integer n1RV = null;
            Integer n2LV = null;
            Integer n2RV = null;
            if (n1R > n1L) {
                n1LV = nums1[n1L];
                n1RV = nums1[n1R];
            } else if (n1R == n1L) {
                n1LV = nums1[n1L];
                n1RV = nums1[n1L];
            }
            if (n2R > n2L) {
                n2LV = nums2[n2L];
                n2RV = nums2[n2R];
            } else if (n2R == n2L) {
                n2LV = nums2[n2L];
                n2RV = nums2[n2L];
            }
            if (n1LV != null && n2LV != null) {
                if (n1LV < n2LV) {
                    numsArr[numsArrL] = n1LV;
                    n1L++;
                } else {
                    numsArr[numsArrL] = n2LV;
                    n2L++;
                }
                numsArrL++;
            } else if (n1LV != null || n2LV != null) {
                if (n1LV == null) {
                    numsArr[numsArrL] = n2LV;
                    n2L++;
                } else {
                    numsArr[numsArrL] = n1LV;
                    n1L++;
                }
                numsArrL++;
            }

            if (n1RV != null && n2RV != null) {
                if (n1RV > n2RV) {
                    numsArr[numsArrR] = n1RV;
                    n1R--;
                } else {
                    numsArr[numsArrR] = n2RV;
                    n2R--;
                }
                numsArrR--;
            } else if (n1RV != null || n2RV != null) {
                if (n2RV == null) {
                    numsArr[numsArrR] = n1RV;
                    n1R--;
                } else {
                    numsArr[numsArrR] = n2RV;
                    n2R--;
                }
                numsArrR--;
            }
        }
        if (numsArr.length % 2 == 0) {
            return (numsArr[numsArr.length / 2] + numsArr[numsArr.length / 2 - 1]) / 2.0;
        }
        return numsArr[numsArr.length / 2];
    }


    public static double findMedianSortedArrays_old(int[] nums1, int[] nums2) {
        if (nums1.length == 0 && nums2.length == 0) {
            return 0;
        }
        if (nums1.length == 0) {
            return findMedianSortedArrays(nums2);
        }
        if (nums2.length == 0) {
            return findMedianSortedArrays(nums1);
        }
        int n1Left = 0;
        int n1Right = nums1.length - 1;
        int n2Left = 0;
        int n2Right = nums2.length - 1;
        while (true) {
            int left;
            int right;
            if (nums1[n1Left] < nums2[n2Left]) {
                if (n1Left < n1Right) {
                    n1Left++;
                }
                left = nums2[n2Left];
            } else {
                if (n2Left < n2Right) {
                    n2Left++;
                }
                left = nums1[n1Left];
            }
            if (nums1[n1Right] > nums2[n2Right]) {
                if (n1Right > n1Left) {
                    n1Right--;
                }
                right = nums2[n2Right];
            } else {
                if (n2Right > n2Left) {
                    n2Right--;
                }
                right = nums1[n1Right];
            }

            if ((n1Left == n1Right || n1Right - n1Left == 1) && (n2Left == n2Right || n2Right - n2Left == -1)) {
                return (left + right) / 2.0;
            }
        }
    }

    private static double findMedianSortedArrays(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int remain = nums.length % 2;
        if (remain == 0) {
            return (nums[nums.length / 2 - 1] + nums[nums.length / 2]) / 2.0;
        } else {
            return (nums[nums.length / 2]);
        }
    }

}
