package leetcode10;

import java.util.ArrayList;
import java.util.List;

public class Leetcode2 {

    public static void main(String[] args) {
        //329
        ListNode n11 = new ListNode(2);
        ListNode n12 = new ListNode(4);
        ListNode n13 = new ListNode(4);
        n11.next = n12;
        n12.next = n13;
        //821
        ListNode n21 = new ListNode(5);
        ListNode n22 = new ListNode(6);
        ListNode n23 = new ListNode(4);
//        n21.next = n22;
//        n22.next = n23;
        Solution s = new Solution();
        ListNode listNode = s.addTwoNumbers(n11, n21);
        while (listNode != null) {
            System.out.println("" + listNode.val);
            listNode = listNode.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    private static class Solution {

        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            //List<Integer> list = new ArrayList<>();
            ListNode cur1 = l1;
            ListNode cur2 = l2;
            ListNode newNode = null;
            ListNode headNode = null;
            boolean isMoreThan9 = false;

            while (cur1 != null || cur2 != null) {
                int value = (cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0);
                int newValue = value + (isMoreThan9 ? 1 : 0);
                ListNode node = new ListNode(newValue % 10);
                isMoreThan9 = newValue > 9;
                if (newNode == null) {
                    newNode = node;
                    headNode = node;
                } else {
                    newNode.next = node;
                    newNode = node;
                }
                if (cur1 != null) cur1 = cur1.next;
                if (cur2 != null) cur2 = cur2.next;
            }
            if (isMoreThan9) {
                newNode.next = new ListNode(1);
            }
            return headNode;
        }


        public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
            List<Integer> list = new ArrayList<>();
            ListNode cur1 = l1;
            ListNode cur2 = l2;
            while (cur1 != null || cur2 != null) {
                list.add((cur1 != null ? cur1.val : 0) + (cur2 != null ? cur2.val : 0));
                if (cur1 != null) cur1 = cur1.next;
                if (cur2 != null) cur2 = cur2.next;
            }
            ListNode newNode = null;
            ListNode headNode = null;
            boolean isMoreThan9 = false;
            for (Integer value : list) {
                int newValue = value + (isMoreThan9 ? 1 : 0);
                ListNode node = new ListNode(newValue % 10);
                isMoreThan9 = newValue > 9;
                if (newNode == null) {
                    newNode = node;
                    headNode = node;
                } else {
                    newNode.next = node;
                    newNode = node;
                }
            }
            if (isMoreThan9) {
                newNode.next = new ListNode(1);
            }
            return headNode;
        }
    }


}
