package src.leet2022;

public class LeetCode215 {

    public static void main1(String[] args) {
        int[] arr = {3, 2, 1, 5, 6, 4};
        //int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
//        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        int result = new LeetCode215().startCal(arr, 2);
        System.out.println("result:" + result);
    }

    public int findKthLargest(int[] nums, int k) {
        //quickSort(nums, 0, nums.length - 1);
        fastSort(nums);
        for (int num : nums) {
            System.out.println(num);
        }
        return nums[nums.length - k];
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        int standard = nums[end];
        int left = start;
        int right = end;
        while (left < right) {
            while (nums[left] <= standard && left < right) {
                left++;
            }
            if (left < right) {
                nums[right] = nums[left];
            }
            while (nums[right] > standard && left < right) {
                right--;
            }
            if (left < right) {
                nums[left] = nums[right];
            }
        }
        nums[right] = standard;
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    private void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    private int startCal(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        maxBigHeadBuild(arr, arr.length);
        int calTimes = k;
        while (true) {
            int len = arr.length - (k - calTimes);
            maxHead(arr, len, 0);
            calTimes--;
            if (calTimes > 0) {
                swap(arr, 0, len - 1);
                continue;
            }
            break;
        }
        return arr[0];
    }


    // 大顶堆排序
    private void maxBigHeadBuild(int[] arr, int length) {
        int size = length / 2;
        for (int i = size; i >= 0; i--) {
            maxHead(arr, length, i);
        }
    }

    private void maxHead(int[] arr, int length, int i) {
        // 判断2*i+1和2*1+2和i的大小
        int b1 = 2 * i + 1;
        int b2 = 2 * i + 2;
        int largest = i;
        if (b2 < length && arr[b2] > arr[largest]) {
            largest = b2;
        }
        if (b1 < length && arr[b1] > arr[largest]) {
            largest = b1;
        }
        if (largest != i) {
            swap(arr, largest, i);
            maxHead(arr, length, largest);
        }
    }


    private void fastSort(int[] arr) {
        innerFastSort(arr, 0, arr.length - 1);
    }

    private void innerFastSort(int[] arr, int start, int end) {
        if (start > end) {
            return;
        }
        int left = start;
        int right = end;
        int key = arr[left];
        while (left < right) {
            while (arr[right] >= key && left < right) {
                right--;
            }
            if (left < right) {
                //swap(arr, left, right);
                arr[left] = arr[right];
            }
            while (arr[left] < key && left < right) {
                left++;
            }
            if (left < right) {
                //swap(arr, left, right);
                arr[right] = arr[left];
            }
        }
        arr[left] = key;
        innerFastSort(arr, start, left - 1);
        innerFastSort(arr, left + 1, end);
    }

    public static void main(String[] args) {
//        int[] arr = {3, 2, 1, 5, 6, 4};
        //int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int[] arr = {3, 2, 3, 1, 2, 4, 5, 5, 6, 7, 7, 8, 2, 3, 1, 1, 1, 10, 11, 5, 6, 2, 4, 7, 8, 5, 6};
        //new LeetCode215().quickSort2(arr, 0, arr.length - 1);
        new LeetCode215().maxHeadSort(arr);
        for (int i : arr) {
            System.out.println(">" + i);
        }
    }


    private void quickSort2(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int key = arr[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            while (left < right && arr[left] < key) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        arr[left] = key;
        quickSort2(arr, start, left - 1);
        quickSort2(arr, left + 1, end);
    }

    private void maxHeadSort(int[] arr) {
        buildMaxHead(arr, arr.length);
    }

    private void buildMaxHead(int[] arr, int len) {
        int n = len / 2;
        for (int i = n; i >= 0; i--) {
            maxHead2(arr, i, len);
        }
    }

    private void maxHead2(int[] arr, int i, int len) {
        int largest = i;
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        if (left < len && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < len && arr[right] > arr[largest]) {
            largest = right;
        }
        if (largest != i) {
            swap(arr, largest, i);
            maxHead2(arr, largest, len); // 被交换的位置需要重新一次交换
        }
    }


}
