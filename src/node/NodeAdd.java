package node;

public class NodeAdd {

    public static void main(String[] args) {
        ListNode listNode11 = new ListNode(7);
        ListNode listNode12 = new ListNode(5);
        ListNode listNode13 = new ListNode(9);
        listNode11.next = listNode12;
        listNode12.next = listNode13;
        printNodeList(listNode11);
        ListNode listNode21 = new ListNode(0);
//        ListNode listNode22 = new ListNode(5);
//        ListNode listNode23 = new ListNode(9);
//        listNode21.next = listNode22;
//        listNode22.next = listNode23;
        printNodeList(listNode21);

        System.out.println("start");
        ListNode p = new Solution().addTwoNumbers(listNode11, listNode21);
        printNodeList(p);
        System.out.println("end");
    }

    private static void printNodeList(ListNode p) {
        while (p != null) {
            System.out.println(">>>" + p.val);
            p = p.next;
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    static class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummyHead = new ListNode(0);
            ListNode p = l1, q = l2, curr = dummyHead;
            int carry = 0;
            while (p != null || q != null) {
                int x = p == null ? 0 : p.val;
                int y = q == null ? 0 : q.val;
                int sum = carry + x + y;
                carry = sum / 10;
                curr.next = new ListNode(sum % 10);
                curr = curr.next;
                if (p != null ) {
                    p = p.next;
                }
                if (q != null ) {
                    q = q.next;
                }
            }
            if (carry > 0) {
                curr.next = new ListNode(carry);
            }
            return dummyHead.next;
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


}
