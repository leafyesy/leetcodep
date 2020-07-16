package src.leetcode30;

import src.base.ListNode;

public class LeetCode23_3 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeList(lists, 0, lists.length - 1);
    }

    private static ListNode mergeList(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + right / 2;
        ListNode node1 = mergeList(lists, left, mid);
        ListNode node2 = mergeList(lists, mid + 1, right);
        return merge(node1, node2);
    }


    private static ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        if (node1.val < node2.val) {
            node1.next = merge(node1.next, node2);
            return node1;
        } else {
            node2.next = merge(node1, node2.next);
            return node2;
        }
    }


}
