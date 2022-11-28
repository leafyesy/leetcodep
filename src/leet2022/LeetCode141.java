package src.leet2022;

import src.base.ListNode;

public class LeetCode141 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = head;
        boolean result = new LeetCode141().hasCycle(head);
        System.out.println(result);
    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast == slow) {
                return true;
            }
            fast = fast.next != null ? fast.next.next : null;
            slow = slow.next;
        }
        return false;
    }

}
