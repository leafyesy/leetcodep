package src.interview;

import src.base.ListNode;
import src.utils.Utils;

public class TenxunInterview {


    public static void main(String[] args) {
        ListNode listNode = Utils.createListNode(new int[]{1, 2, 3, 4, 5, 6});
        ListNode reverse = reverse(listNode);
        Utils.printNode(reverse);
    }


    private static ListNode reverse(ListNode root) {
        if (root == null || root.next == null) {
            return null;
        }
        ListNode pre = root;
        ListNode cur = root.next;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        root.next = null;
        return pre;
    }


}
