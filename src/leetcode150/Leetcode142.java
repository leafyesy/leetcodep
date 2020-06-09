package src.leetcode150;

import src.base.ListNode;
import src.utils.Utils;

public class Leetcode142 {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 0, -4};
        ListNode head = Utils.createListNode(arr);
        Utils.printNode(head);
        ListNode last = Utils.getLastListNode(head);
        System.out.println("last:"+last);
        ListNode circleNode = Utils.getListNodeByIndex(head, 1);
        System.out.println("circleNode:"+circleNode);
        last.next = circleNode;
        ListNode listNode = detectCycle(head);
        System.out.println(listNode.toString());
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode meeting = findMeetingNode(head);
        if (meeting == null) return null;
        ListNode node = head;
        ListNode circleInnerNode = meeting;
        while (node != circleInnerNode) {
            node = node.next;
            circleInnerNode = circleInnerNode.next;
        }
        return node;
    }

    //@Nullable
    private static ListNode findMeetingNode(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode fast = null;
        ListNode slow = null;
        while (fast == null || fast != slow) {
            if(fast == null && slow == null){//初始化
                fast = head;
                slow = head;
            }
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
        }
        return fast;
    }

}
