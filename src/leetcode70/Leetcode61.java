package src.leetcode70;

public class Leetcode61 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        ListNode head = null;
        ListNode cur = null;
        for (int i : arr) {
            ListNode node = new ListNode(i);
            if (head == null) {
                head = node;
                cur = node;
            } else {
                cur.next = node;
                cur = node;
            }
        }

        ListNode node = rotateRight(head, 3);
        cur = node;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }


    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        if (k == 0) return head;
        int count = 0;//用于节点遍历的计数,双指针寻找newHead
        int remainK = k;
        ListNode curNode = head;
        ListNode newHeadPre = null;
        ListNode newHead = null;
        ListNode tail = null;
        while (true) {
            if (remainK > 0) {
                remainK--;
            } else if (remainK == 0) {
                newHeadPre = newHead;
                if (newHead == null) {
                    newHead = head;
                } else {
                    newHead = newHead.next;
                }
            }
            curNode = curNode.next;
            count++;
            if (curNode.next == null) {
                tail = curNode;
                break;
            }
        }
        if (k % count == 0) return head;
        if (newHeadPre == null) {//表示k太大,需要处理一下
            remainK = k % count;
            curNode = head;
            while (true) {
                if (remainK > 0) {
                    remainK--;
                } else if (remainK == 0) {
                    newHeadPre = newHead;
                    if (newHead == null) {
                        newHead = head;
                    } else {
                        newHead = newHead.next;
                    }
                }
                curNode = curNode.next;
                if (curNode.next == null) {
                    tail = curNode;
                    break;
                }
            }
        }
        newHeadPre.next = null;
        tail.next = head;
        return newHead;
    }


}
