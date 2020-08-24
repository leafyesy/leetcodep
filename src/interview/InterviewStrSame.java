package src.interview;

import src.base.ListNode;
import src.utils.Utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class InterviewStrSame {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//        resort(arr);
//        Utils.printIntArr(arr);
//        ListNode listNode = Utils.createListNode(arr);
//        reverseMN(listNode, 2, 5);
//        Utils.printNode(listNode);
//        String s1 = "mafazine";
//        String s2 = "validate";
//        System.out.println(findSame(s1, s2));

        int last = findLast(5, 90);
        int last1 = getLast(90, 5);
        int last2 = getLast2(90, 5);
        int last3 = getLast3(90, 5);
        System.out.println("last3:" + last3 + " last1:" + last1 + " last2:" + last2);
    }

    int count = 0;


    public void fiveAdd(){

    }


    public static int getLast3(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        int index = 0;
        while (list.size() > 1) {
            index = (m + index - 1) % list.size();
            Integer remove = list.remove(index);
            System.out.println("remove:" + remove);
        }
        return list.get(0);
    }


    /**
     * @param m 表示m数要被淘汰
     * @param n 表示n个人
     * @return
     */
    public static int findLast(int m, int n) {
        boolean[] arr = new boolean[n];
        int count = 0;
        int arrIndex = -1;
        while (true) {
            int i = m % (n - count);
            if (i == 0) {
                i = n;
            }
            int cur = 0;
            //System.out.println("count:" + count + " i:" + i);
            while (cur < i) {
                arrIndex++;
                if (arrIndex == n) {
                    arrIndex = 0;
                }
                if (count == n - 1) {
                    return arrIndex + 1;
                }
                if (arr[arrIndex]) {//表示已经被丢弃的
                    //System.out.println("arrIndex:" + arrIndex);
                    continue;
                }
                if (cur == i - 1) {
                    arr[arrIndex] = true;
                    count++;
                } else {
                    cur++;
                }
            }
        }
    }

    public static int getLast(int n, int k) {
        int last = 1; //这是初始值，即环内为1个人时剩余的人的编号，必然为1，也是后面推导的基础
        //System.out.println("当环内有1个人时，剩余的是:1");
        for (int i = 2; i <= n; i++) { //当圈内为i个人时，结果是圈内为i-1个人的结果加K后对i求余
            last = ((last + k - 1) % i) + 1; //为避免出现结果为0，让i-1的结果先减1，求余后再加1
            //System.out.println("当环内有" + i + "个人时，剩余的是:" + last);
        }
        return last;
    }

    public static int getLast2(int n, int m) {
        if (n == 0 || m <= 0) {
            return -1;
        }
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }


    private static String findSame(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "";
        }
        HashSet<Character> set = new HashSet<>();
        HashSet<Character> findSet = new HashSet<>();
        for (int i = 0; i < s1.length(); i++) {
            set.add(s1.charAt(i));
        }
        for (int j = 0; j < s2.length(); j++) {
            if (set.contains(s2.charAt(j))) {
                findSet.add(s2.charAt(j));
            }
        }
        StringBuilder result = new StringBuilder();
        for (Character character : findSet) {
            result.append(character);
        }
        return result.toString();
    }


    private static String findMaxSame(String s1, String s2) {
        if (s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) {
            return "";
        }
        String result = "";
        for (int i = 0; i < s1.length(); i++) {
            for (int j = 0; j < s2.length(); j++) {
                if (s2.charAt(j) == s1.charAt(i)) {
                    //这里进行向后读取判断字符
                    int left = j;
                    int right = j;
                    for (int k = 1; k + j < s2.length() && k + i < s1.length(); k++) {
                        if (s1.charAt(k + i) == s2.charAt(k + j)) {
                            right++;
                        } else {
                            break;
                        }
                    }
                    System.out.println("left:" + left + " right:" + right);
                    if (result.length() < right - left + 1) {
                        result = s2.substring(left, right + 1);
                    }
                }
            }
        }
        return result;
    }

    private static void quick(int[] arr) {
        quick(arr, 0, arr.length - 1);
    }

    private static void quick(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int l = start;
        int r = end;
        int key = arr[l];
        while (l < r) {
            while (key <= arr[r] && l < r) {
                r--;
            }
            while (key >= arr[l] && l < r) {
                l++;
            }
            if (l < r) {
                int temp = arr[r];
                arr[r] = arr[l];
                arr[l] = temp;
            }
        }
        //把key放中间
        arr[start] = arr[l];
        arr[l] = key;
        quick(arr, start, l - 1);
        quick(arr, l + 1, end);
    }

    private static void guibing(int[] arr) {
        guibing(arr, 0, arr.length);
    }

    private static void guibing(int[] arr, int start, int end) {
        if (start < end - 1) {
            int mid = start + (end - start) / 2;
            guibing(arr, start, mid);
            guibing(arr, mid, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start];
        int i = 0;//表示temp的index
        int l = start;
        int r = mid;
        while (l < mid && r < end) {
            if (arr[l] < arr[r]) {
                temp[i++] = arr[l++];
            } else {
                temp[i++] = arr[r++];
            }
        }
        while (l < mid) {
            temp[i++] = arr[l++];
        }
        while (r < end) {
            temp[i++] = arr[r++];
        }
        if (temp.length >= 0)
            System.arraycopy(temp, 0, arr, start, temp.length);
    }

    private static void resort(int[] arr) {
        resort(arr, 2);
    }

    private static void resort(int[] arr, int target) {
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] < target) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
        int i = j;
        for (; i < arr.length - 1; i++) {
            if (arr[i] == target) {
                int temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                j++;
            }
        }
    }


    private static void reverseMN(ListNode root, int m, int n) {
        if (root == null) {
            return;
        }
        ListNode pre = new ListNode(-1);
        pre.next = root;
        int count = 0;
        ListNode cur = pre;
        while (cur != null && count < m - 1) {
            cur = cur.next;
            count++;
        }
        if (count < m - 1 || cur == null) {
            return;
        }
        System.out.println("cur:" + cur.val);
        reverse(cur, n - m + 1);
    }

    private static void reverse(ListNode root, int k) {
        int count = 0;
        ListNode pre = root;
        ListNode cur = root.next;
        ListNode top = root.next;
        ListNode next = null;
        while (count <= k) {
            if (count == k || cur == null) {
                //这里进行头尾的节点变换
                root.next = pre;
                if (top != null)
                    top.next = cur;
//                if (count < k) {
//                    System.out.println("无法回转");
//                    reverse(root, count);
//                }
                return;
            }
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }
    }


    private static ListNode reverseK(ListNode head, int k) {
        ListNode pre = head;
        ListNode cur = head.next;
        ListNode next = null;
        ListNode first = head.next;
        if (cur == null) {
            return null;
        }
        int count = 0;
        while (count <= k + 1) {
            if (count == k || cur == null) {
                head.next = pre;
                first.next = cur;
                break;
            }
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }
        Utils.printNode(head);
        return first;
    }


    static class SingleInstance {

        private static volatile SingleInstance instance;

        private SingleInstance() {
        }

        public static SingleInstance getInstance() {
            if (instance == null) {
                synchronized (SingleInstance.class) {
                    if (instance == null) {
                        instance = new SingleInstance();
                    }
                }
            }
            return instance;
        }

    }

    static class ProducerAndConsumer {

        private final Queue<Integer> queue = new LinkedList<>();
        private final Object LOCK = new Object();
        private volatile int maxSize = 20;
        private volatile int count = 0;
        private volatile boolean isStop = false;

        public void setMaxSize(int maxSize) {
            this.maxSize = maxSize;
        }

        public void stop() {
            isStop = true;
        }


        public void start() {
            Thread t1 = new Thread(new Consumer());
            Thread t2 = new Thread(new Producer());
            t1.start();
            t2.start();
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            isStop = true;
        }

        private void producer() throws InterruptedException {
            synchronized (LOCK) {
                if (queue.size() == maxSize) {
                    LOCK.wait();
                }
                count++;
                System.out.println("producer:" + count);
                queue.add(count);
                LOCK.notify();
            }
        }

        private void consumer() throws InterruptedException {
            synchronized (LOCK) {
                if (queue.size() == 0) {
                    LOCK.wait();
                }
                int poll = queue.poll();
                System.out.println("consumer:" + poll);
                LOCK.notify();
            }
        }

        class Consumer implements Runnable {

            @Override
            public void run() {
                try {
                    while (!isStop) {
                        consumer();
                        Thread.yield();
                    }
                } catch (Exception e) {

                }
            }
        }

        class Producer implements Runnable {
            @Override
            public void run() {
                try {
                    while (!isStop) {
                        producer();
                        Thread.yield();
                    }
                } catch (Exception e) {

                }
            }
        }
    }

}
