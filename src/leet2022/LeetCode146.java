package src.leet2022;

import java.util.HashMap;
import java.util.LinkedList;

public class LeetCode146 {

    class LRUCache {

        int capacity;
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>(); // 需要知道的是头尾,可以替换成双向链表来保存数据

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer result = map.get(key);
            if (result != null) {
                // 调整位置
                list.remove((Object) key);
                list.addFirst(key);
                return result;
            }
            return -1;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                list.remove((Object) key);
            }
            list.addFirst(key);
            map.put(key, value);
            if (list.size() > capacity) {
                // 移除最后一个
                Integer oldKey = list.removeLast();
                map.remove(oldKey);
            }
        }
    }

    public static void main(String[] args) {
        LRUCache2 lruCache2 = new LRUCache2(2);
        lruCache2.put(2,1);
        lruCache2.put(1,1);
        lruCache2.put(2,3);
        lruCache2.put(4,1);

    }

    static class LRUCache2 {

        static class Node {
            Integer val;
            Integer key;
            Node pre;
            Node next;

            public Node() {
            }

            public Node(Integer val, Integer key) {
                this.key = key;
                this.val = val;
            }
        }

        private HashMap<Integer, Node> map = new HashMap<>();

        private Node head = new Node();
        private Node tail = new Node();

        private int capacity;

        public LRUCache2(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.pre = head;
        }

        public Integer get(Integer key) {
            Node node = map.get(key);
            if (node != null) {
                deleteNode(node);
                // 移动到头部
                insertAfter(head, node);
                return node.val;
            }
            return -1;
        }

        public void put(int key, int value) {
            Node putNode = map.get(key);
            if (putNode != null) {
                putNode.val = value;
                putNode.key = key;
                deleteNode(putNode);
            } else {
                if (map.size() == capacity) {
                    Node remove = tail.pre;
                    deleteNode(remove);
                    map.remove(remove.key);
                }
                putNode = new Node(value, key);
            }
            map.put(key, putNode);
            insertAfter(head, putNode);
        }

        public void deleteNode(Node node) {
            // 双向绑定解除
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.pre = null;
            node.next = null;
        }

        public void insertAfter(Node node, Node insert) {
            Node temp = node.next;
            insert.pre = node;
            node.next = insert;
            temp.pre = insert;
            insert.next = temp;
        }
    }


}
