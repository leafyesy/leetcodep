package src.leetcode150;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class Leetcode146 {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        cache.get(2);       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        cache.get(1);       // 返回 -1 (未找到)
        cache.get(3);       // 返回  3
        cache.get(4);       // 返回  4

    }


    static class LRUCacheImp<K, V> {

        private int capacity;
        private LinkedHashMap<K, V> list = new LinkedHashMap<>();
        private LinkedList<K> lruList = new LinkedList<>();
        private HashMap<K, Integer> indexHashMap = new HashMap<>();

        public LRUCacheImp(int capacity) {
            if (capacity < 0) throw new IllegalArgumentException("capacity must > 0");
            this.capacity = capacity;
        }

        public V get(K key) {
            V value = list.get(key);
            if (value == null) return null;
            Integer index = indexHashMap.get(key);
            if (index != null) {
                lruList.remove(index.intValue());
                lruList.add(key);
                indexHashMap.put(key, lruList.size() - 1);
            }
            return value;
        }

        public void put(K key, V value) {
            if (!list.containsKey(key) && list.size() >= capacity) {
                //删除最久的数据
                K k = lruList.removeFirst();
                list.remove(k);
                indexHashMap.remove(key);
            }
            if (list.containsKey(key)) {
                Integer index = indexHashMap.get(key);
                if (index != null) {
                    K remove = lruList.remove(index.intValue());
                    System.out.println("remove:" + remove);
                }
            }
            lruList.add(key);
            indexHashMap.put(key, lruList.size() - 1);
            list.put(key, value);
        }
    }

    class LRUCache1 extends LinkedHashMap<Integer, Integer> {
        private int capacity;

        public LRUCache1(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key, value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return size() > capacity;
        }
    }


    static class LRUCache {

        private int capacity;

        static class Node {
            private int key;
            private int val;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
            }

            Node pre;
            Node next;
        }

        private HashMap<Integer, Node> cache = new HashMap<>();

        private static Node FIRST_PRE = new Node(-1, -1);
        private static Node LAST_NEXT = new Node(-1, -1);

        private Node lastLast = LAST_NEXT;
        private Node firstFirst = FIRST_PRE;


        public LRUCache(int capacity) {
            if (capacity < 0) throw new IllegalArgumentException("capacity must > 0");
            this.capacity = capacity;
            init();
        }

        private void init() {
            firstFirst.next = lastLast;
            lastLast.pre = firstFirst;
        }

        public int get(int key) {
            System.out.println("获取:key:" + key);
            Node node = cache.get(key);
            if (node == null) return -1;
            //移动到最后面
            putLast(node);
            return node.val;
        }

        public void put(int key, int value) {
            System.out.println("存入:key:" + key + " val:" + value);
            Node cacheNode = cache.get(key);
            if (cacheNode == null)//需要新的位置并且位置已满
                if (cache.size() >= capacity) {
                    //删除最早的
                    removeFirst();
                }
            if (cacheNode == null) {
                cacheNode = new Node(key, value);
            } else {
                cacheNode.val = value;
            }
            putLast(cacheNode);
            cache.put(key, cacheNode);
        }

        private void removeFirst() {
            Node next = firstFirst.next;
            System.out.println("key:" + next.key + " val:" + next.val);
            if (next == lastLast) return;
            cache.remove(next.key);
            Node nextNext = firstFirst.next.next;
            nextNext.pre = firstFirst;
            firstFirst.next = nextNext;
        }

        private void putLast(Node node) {
            //和原来的断开联系
            deleteFromList(node);
            //插入到最后
            printNode(firstFirst, "开始");
            Node lastPre = lastLast.pre;
            lastPre.next = node;
            node.pre = lastPre;
            node.next = lastLast;
            lastLast.pre = node;
            printNode(firstFirst, "结束");
        }

        private void deleteFromList(Node node) {
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) pre.next = next;
            if (next != null) next.pre = pre;
        }

        public void printNode(Node node, String head) {
            System.out.print(head + "[");
            while (node != null) {
                System.out.print("key:" + node.key + " val:" + node.val + " ->");
                node = node.next;
            }
            System.out.println("]");
        }
    }


    static class LRUCache2<K, V> {
        //lru算法的容量,存储的数据不能大于这个值
        //>0 表示容量
        //<0 表示不限制容量
        private int capacity;
        //缓存内容
        private HashMap<K, Node<K, V>> cache;
        //头部节点之前的虚拟节点
        private Node<K, V> preFirst;
        //尾部节点之后的虚拟节点
        private Node<K, V> tail;

        public LRUCache2(int capacity) {
            this.capacity = capacity;
            cache = new HashMap<>();
            preFirst = new Node<>(null, null);
            tail = new Node<>(null, null);
            preFirst.next = tail;
            tail.pre = preFirst;
        }

        public V get(K key) {
            Node<K, V> node = cache.get(key);
            if (null == node) return null;
            moveToLast(node);
            return node.val;
        }

        public void put(K key, V val) {
            if (!cache.containsKey(key) && capacity > 0 && cache.size() >= capacity) {
                deleteFirst();
            }
            Node<K, V> kvNode = cache.get(key);
            if (kvNode == null) {
                kvNode = new Node<>(key, val);
            } else {
                kvNode.val = val;
            }
            moveToLast(kvNode);
            cache.put(key, kvNode);
        }

        //移动节点到尾部
        private void moveToLast(Node<K, V> node) {
            //从原节点中移除
            Node<K, V> preSrc = node.pre;
            Node<K, V> nextSrc = node.next;
            if (preSrc != null) preSrc.next = nextSrc;
            if (nextSrc != null) nextSrc.pre = preSrc;
            //移动到最后节点
            Node<K, V> targetPre = tail.pre;
            if (targetPre != null) targetPre.next = node;
            node.pre = targetPre;
            node.next = tail;
            tail.pre = node;
        }

        //删除头节点
        private void deleteFirst() {
            Node<K, V> removeFirstNode = preFirst.next;
            if (removeFirstNode != null && removeFirstNode != tail) {
                if (removeFirstNode.key != null)
                    cache.remove(removeFirstNode.key);
                removeFirstNode.pre = null;
                Node<K, V> newFirst = removeFirstNode.next;
                removeFirstNode.next = null;
                preFirst.next = newFirst;
                newFirst.pre = preFirst;
            }
        }

        /**
         * LRU存储值使用的节点
         *
         * @param <K>
         * @param <V>
         */
        private static class Node<K, V> {
            private K key;
            private V val;

            private Node<K, V> pre;
            private Node<K, V> next;

            private Node(K key, V val) {
                this.key = key;
                this.val = val;
            }
        }

    }

}
