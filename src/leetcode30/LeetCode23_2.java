package src.leetcode30;

import src.base.ListNode;
import src.utils.Utils;

public class LeetCode23_2 {

    public static void main(String[] args) {
        ListNode[] nodes = new ListNode[]{
                Utils.createListNode(new int[]{-1, 1}),
                Utils.createListNode(new int[]{-3, 1, 4}),
                Utils.createListNode(new int[]{-2, -1, 0, 2}),
        };
        Solution s = new Solution();
        ListNode listNode = s.mergeKLists(nodes);
        Utils.printNode(listNode);
    }


    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            SortListNode sortHeadPre = new SortListNode(null);
            createSortListNode(lists, sortHeadPre);
            //这里获得了一个排序好的链表
            ListNode preHead = new ListNode(-1);
            SortListNode operate;
            ListNode cur = preHead;
            while (sortHeadPre.next != null && sortHeadPre.next.isEnd()) {
                sortHeadPre = sortHeadPre.next;
            }
            while (sortHeadPre.next != null && !sortHeadPre.next.isEnd()) {
                operate = sortHeadPre.next;
                cur.next = operate.cur;
                cur = cur.next;
                operate.jumpToNext();
                findAndInsert(operate);
                printSortNode(sortHeadPre);
                while (sortHeadPre.next != null && sortHeadPre.next.isEnd()) {
                    sortHeadPre = sortHeadPre.next;
                }
            }
            return preHead.next;
        }

         private void printSortNode(SortListNode sortHeadPre) {
             SortListNode curSortNode;
             curSortNode = sortHeadPre.next;
             while (curSortNode != null) {
                 //Utils.printNode(curSortNode.head);
                 System.out.println("cur:" + (curSortNode.isEnd() ? -1 : curSortNode.cur.val));
                 curSortNode = curSortNode.next;
             }
         }
        private void findAndInsert(SortListNode operate) {
            if (!operate.isEnd()) {
                SortListNode temp = operate;
                while (true) {
                    if (temp.next == null || temp.next.cur.val >= operate.cur.val) {
                        if (temp == operate)
                            break;
                        connectNodePreNext(operate);
                        insertAfter(temp, operate);
                        break;
                    }
                    temp = temp.next;
                }
            }
        }

        private void createSortListNode(ListNode[] lists, SortListNode sortHeadPre) {
            SortListNode curSortNode = null;
            for (ListNode cur : lists) {
                SortListNode sNode = new SortListNode(cur);
                if (sortHeadPre.next == null) {
                    sortHeadPre.next = sNode;
                    sNode.pre = sortHeadPre;
                } else {
                    //从头开始向后寻找位置
                    curSortNode = sortHeadPre;
                    while (true) {
                        if (curSortNode.next == null //没有下一个了
                                || (curSortNode.next.cur == null ? Integer.MIN_VALUE : curSortNode.next.cur.val)
                                >= (cur == null ? Integer.MIN_VALUE : cur.val)) {//下一个大于当前的
                            //插入
                            insertAfter(curSortNode, sNode);
                            break;
                        }
                        curSortNode = curSortNode.next;
                    }
                }
            }
        }

        private void connectNodePreNext(SortListNode insertNode) {
            SortListNode pre = insertNode.pre;
            SortListNode next = insertNode.next;
            if (next != null) next.pre = pre;
            if (pre != null) pre.next = next;
            insertNode.pre = null;
            insertNode.next = null;
        }

        private void insertAfter(SortListNode sortNode, SortListNode insertNode) {
            SortListNode next = sortNode.next;
            insertNode.next = next;
            insertNode.pre = sortNode;
            sortNode.next = insertNode;
            if (next != null) next.pre = sortNode;
        }

        /**
         * 用于存储lists,形成关系
         **/
        private static class SortListNode {

            private final ListNode head;

            private ListNode cur;

            SortListNode pre;

            SortListNode next;

            public SortListNode(ListNode node) {
                this.head = node;
                cur = head;
            }

            public void jumpToNext() {
                if (cur != null)
                    cur = cur.next;
            }

            public boolean isEnd() {
                return cur == null;
            }

        }


    }

}
