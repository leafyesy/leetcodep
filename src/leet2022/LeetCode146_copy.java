package src.leet2022;

import java.util.HashMap;

public class LeetCode146_copy {

    public static class LRUCache {

        // 1.hashMap 存储数据 用于快速找到数据
        // 2.双向链表 用于存储数据的顺序 来判断哪些数据常用 哪些数据需要在溢出后删除

        private static class Node {

            private final Integer key;
            private Integer value;

            private Node pre;
            private Node next;

            public Node(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }

        private static final Node HEAD = new Node(-1,-1);
        private static final Node TAIL = new Node(-1,-1);

        private final int capacity;
        private final HashMap<Integer, Node> map = new HashMap<>();
        private final Node head = HEAD;
        private final Node tail = TAIL;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public Integer get(Integer key) {
            Node result = map.get(key);
            if (result != null) {
                // 把节点从原节点移动出来放到顶部
                removeNode(result);
                insertHead(result);
                return result.value;
            }
            return -1;
        }

        public void put(Integer key, Integer value) {
            Node node = map.get(key);
            if (node != null) {
                node.value = value;
                removeNode(node);
            } else {
                node = new Node(key, value);
                map.put(key, node);
                //检查数量
                if (map.size() > capacity) {
                    Node deleteNode = tail.pre;
                    removeNode(deleteNode);
                    map.remove(deleteNode.key);
                }
            }
            insertHead(node);
        }

        private void insertHead(Node insert) {
            Node next = head.next;
            head.next = insert;
            insert.pre = head;
            next.pre = insert;
            insert.next = next;
        }

        private void removeNode(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            pre.next = next;
            next.pre = pre;
        }

    }


}
