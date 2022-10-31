package src.leet2022;

import src.base.ListNode;

public class LeetCode21 {


    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list1_1 = new ListNode(2);
        list1.next = list1_1;
        ListNode list1_2 = new ListNode(4);
        list1_1.next = list1_2;

        ListNode list2 = new ListNode(3);
        ListNode list2_1 = new ListNode(5);
        list2.next = list2_1;
        ListNode list2_2 = new ListNode(6);
        list2_1.next = list2_2;


        ListNode listNode = new LeetCode21().mergeTwoLists(list1, list2);

        ListNode printNode = listNode;
        while (printNode != null) {
            System.out.println("" + printNode.val);
            printNode = printNode.next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode resultHead = new ListNode(Integer.MIN_VALUE);
        ListNode resultCur = resultHead;
        ListNode curLeftNode = list1;
        ListNode curRightNode = list2;
        while (curLeftNode != null || curRightNode != null) {
            // 判定哪个先添加到结果ListNode中
            if (curLeftNode == null || (curRightNode != null && curLeftNode.val > curRightNode.val)) {
                resultCur.next = curRightNode;
                resultCur = curRightNode;
                curRightNode = curRightNode.next;
            } else {
                resultCur.next = curLeftNode;
                resultCur = curLeftNode;
                curLeftNode = curLeftNode.next;
            }

        }
        return resultHead.next;
    }

}
