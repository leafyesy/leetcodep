package leetcode30;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode23 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static class LinkNode {
        ListNode node;
        LinkNode next;

        LinkNode(ListNode x) {
            this.node = x;
        }
    }

    static class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            if (lists.length == 0) return null;
            if (lists.length == 1) return lists[0];
            LinkNode linkNode = new LinkNode(lists[0]);
            LinkNode cur = linkNode;
            for (int i = 1; i < lists.length; i++) {
                LinkNode next = new LinkNode(lists[i]);
                if (next.node.val > cur.node.val) {
                    cur.next = next;
                    cur = next;
                } else {
                    //重头开始寻找位置
                    linkNode = insert(linkNode, next);
                }
            }
            //对linkNode进行遍历
            cur = linkNode;
            ListNode head = null;
            ListNode curListNode = null;
            while (cur != null) {
                ListNode next = new ListNode(cur.node.val);
                if (head == null)
                    head = next;
                if (curListNode == null)
                    curListNode = next;
                else {
                    curListNode.next = next;
                    curListNode = curListNode.next;
                }
                cur.node = cur.node.next;
                cur = insert(cur.next, cur);
            }
            return head;
        }

        private LinkNode insert(LinkNode linkNode, LinkNode insert) {
            if (insert.node == null) {
                return linkNode;
            }
            LinkNode head = linkNode;
            LinkNode pre = null;
            LinkNode cur = linkNode;
            while (cur != null) {
                if (cur.node.val > insert.node.val) {
                    insert.next = cur;
                    if (pre != null) {
                        pre.next = insert;
                    } else {
                        head = insert;
                    }
                    break;
                } else {
                    pre = cur;
                    cur = cur.next;
                }
            }
            return head;
        }


        public ListNode mergeKLists1(ListNode[] lists) {
            if (lists.length == 0) return null;
            if (lists.length == 1) return lists[0];
            List<ListNode> list = new ArrayList<>(lists.length);
            list.addAll(Arrays.asList(lists));
            ListNode head = null;
            ListNode cur = null;
            //对列表中的数据进行判断
            Integer minIndex = null;
            Integer min = null;
            boolean isAllEnd = false;
            while (!isAllEnd) {
                isAllEnd = true;
                for (int j = 0; j < list.size(); j++) {
                    ListNode node = list.get(j);
                    if (node != null) {
                        isAllEnd = false;
                        if (min == null || min > list.get(j).val) {
                            minIndex = j;
                            min = list.get(j).val;
                        }
                    }
                }
                //加入链表
                if (min != null) {
                    ListNode node = new ListNode(min);
                    if (head == null) head = node;
                    if (cur == null) {
                        cur = node;
                    } else {
                        cur.next = node;
                        cur = node;
                    }
                }
                if (minIndex != null) {
                    ListNode node = list.get(minIndex);
                    list.remove(minIndex.intValue());
                    list.add(minIndex, node.next);
                }
                min = null;
                minIndex = null;
            }
            return head;
        }

    }


}
