package src.leet2022;

import java.util.ArrayList;
import java.util.List;

public class LeetCode2 {

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1CurNode = l1;
        ListNode l2CurNode = l2;
        ListNode curNode = null;
        ListNode result = null;
        boolean isTen = false;
        while (l1CurNode != null || l2CurNode != null) {
            int value = (l1CurNode != null ? l1CurNode.val : 0) + (l2CurNode != null ? l2CurNode.val : 0) + (isTen ? 1 : 0);
            if (value >= 10) {
                isTen = true;
                value = value % 10;
            } else {
                isTen = false;
            }
            ListNode node = new ListNode();
            node.val = value;
            if (curNode != null) {
                curNode.next = node;
            } else {
                result = node;
            }
            curNode = node;
            l1CurNode = l1CurNode != null ? l1CurNode.next : null;
            l2CurNode = l2CurNode != null ? l2CurNode.next : null;
        }
        if (isTen) {
            ListNode node = new ListNode();
            node.val = 1;
            curNode.next = node;
        }
        return result;
    }


    // 想复杂了 顺序倒叙
    public ListNode addTwoNumbers_x(ListNode l1, ListNode l2) {
        List<Integer> l1List = parseNodeToList(l1);
        List<Integer> l2List = parseNodeToList(l2);
        int l1Val = 0;
        int l2Val = 0;
        int l1Index = l1List.size() - 1;
        int l2Index = l2List.size() - 1;
        if (l1List.size() > 0) {
            l1Val = l1List.get(l1Index);
        }
        if (l2List.size() > 0) {
            l2Val = l2List.get(l2Index);
        }
        boolean isTen = false;
        ListNode curNode = null;
        while (l1Val > 0 || l2Val > 0) {
            ListNode node = new ListNode();
            int value = l1Val + l2Val + (isTen ? 1 : 0);
            if (value >= 10) {
                isTen = true;
                value = value % 10;
            } else {
                isTen = false;
            }
            node.val = value;
            node.next = curNode;
            curNode = node;
            l1Index--;
            l2Index--;
            if (l1Index >= 0) {
                l1Val = l1List.get(l1Index);
            } else {
                l1Val = 0;
            }
            if (l2Index >= 0) {
                l2Val = l2List.get(l2Index);
            } else {
                l2Val = 0;
            }
        }
        if (isTen) {
            ListNode lastNode = new ListNode();
            lastNode.val = 1;
            lastNode.next = curNode;
            curNode = lastNode;
        }
        return curNode;
    }

    private List<Integer> parseNodeToList(ListNode l1) {
        List<Integer> list = new ArrayList<>();
        ListNode cur = l1;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        return list;
    }

}
