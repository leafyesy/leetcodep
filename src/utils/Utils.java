package src.utils;

import src.base.ListNode;

import java.util.List;

public class Utils {

    public static <T> void printList(List<T> list) {
        System.out.print("[");
        int count = 0;
        for (T obj : list) {
            System.out.print("" + obj);
            count++;
            if (count < list.size())
                System.out.print(",");
        }
        System.out.println("]");
    }

    public static void printIntArr(int[] arr) {
        System.out.print("[");
        int count = 0;
        for (int elem : arr) {
            System.out.print("" + elem);
            count++;
            if (count < arr.length)
                System.out.print(",");
        }
        System.out.println("]");
    }

    public static void printCharArr(char[] arr) {
        System.out.print("[");
        int count = 0;
        for (char elem : arr) {
            System.out.print("" + elem);
            count++;
            if (count < arr.length)
                System.out.print(",");
        }
        System.out.println("]");
    }


    public static void printNode(ListNode node) {
        System.out.print("[");
        ListNode cur = node;
        while (true) {
            System.out.print(cur.val + "");
            if (cur.next != null) {
                System.out.print(",");
                cur = cur.next;
            } else {
                break;
            }
        }
        System.out.println("]");
    }

    public static ListNode deleteCenterNode(ListNode node) {
        ListNode last = node;
        ListNode deletePre = null;
        int count = 0;
        while (last.next != null) {
            count++;
            last = last.next;
            if (count >= 2 && (count % 2 == 0)) {
                if (deletePre == null) deletePre = node;
                else deletePre = deletePre.next;
            }
        }
        if (count < 2) return node;
        assert deletePre != null;
        deletePre.next = deletePre.next.next;
        return node;
    }

    public static ListNode createListNode(int[] arr) {
        if (arr.length == 0) return null;
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i++) {
            ListNode node = new ListNode(arr[i]);
            cur.next = node;
            cur = node;
        }
        return head;
    }

    public static ListNode getListNodeByIndex(ListNode head, int index) {
        if (index < 0) return null;
        int count = index;
        ListNode cur = head;
        while (count > 0) {
            cur = cur.next;
            count--;
        }
        return cur;
    }

    public static ListNode getLastListNode(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        return cur;
    }


}
