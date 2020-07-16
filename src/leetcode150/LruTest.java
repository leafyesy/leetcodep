package src.leetcode150;

import java.util.HashMap;

public class LruTest {

    public static void main(String[] args) {
        LRUCacheN cache = new LRUCacheN(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回  1
        cache.put(3, 3);    // 该操作会使得密钥 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得密钥 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回  3
        System.out.println(cache.get(4));       // 返回  4

        //[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
//        cache.put(2, 1);
//        cache.put(1, 1);
//        cache.put(2, 3);
//        cache.put(4, 1);
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(2));
    }


    static class LRUCacheN {

        private final int capacity;

        private Node firstPre;
        private Node tailNext;

        private HashMap<Integer, Node> cache;

        public LRUCacheN(int capacity) {
            this.capacity = capacity;
            init();
        }

        private void init() {
            cache = new HashMap<>();
            firstPre = new Node(-1, -1);
            tailNext = new Node(-2, -2);
            firstPre.next = tailNext;
            tailNext.pre = firstPre;
        }

        public int get(int key) {
            Node node = cache.get(key);
            //把节点移动到头部
            nodeGet(node);
            return node == null ? -1 : node.value;
        }

        public void put(int key, int value) {
            Node node = cache.get(key);
            if (node == null) {
                //检查数量
                checkCapacity();
                node = new Node(key, value);
            } else {
                node.value = value;
            }
            //接到头部
            nodeInsert(node);
            cache.put(key, node);
        }

        /**
         * 节点存入的时候操作
         **/
        protected void nodeInsert(Node node) {
            removeNode(node);
            insertNode(node, firstPre);
        }

        /**
         * 节点取出的时候操作
         **/
        protected void nodeGet(Node node) {
            //把节点移动到头部
            removeNode(node);
            insertNode(node, firstPre);
        }

        private void checkCapacity() {
            if (cache.size() >= capacity) {
                //移除尾巴的节点
                Node tail = tailNext.pre;
                if (tail != firstPre) {
                    cache.remove(tail.key);
                    removeNode(tail);
                }
            }
        }

        private void connectPreAndNext(Node node) {
            if (node == null) return;
            Node pre = node.pre;
            Node next = node.next;
            if (pre != null) pre.next = next;
            if (next != null) next.pre = pre;
        }

        private void removeNode(Node node) {
            if (node == null) return;
            connectPreAndNext(node);
            node.pre = null;
            node.next = null;
        }

        private void insertNode(Node insert, Node position) {
            if (position == null || insert == null) return;
            Node next = position.next;
            if (next != null) next.pre = insert;
            insert.next = next;
            position.next = insert;
            insert.pre = position;
        }


        private static class Node {
            private int key;
            private int value;

            private Node pre;
            private Node next;

            Node(int key, int value) {
                this.key = key;
                this.value = value;
            }

            @Override
            public String toString() {
                return "key:" + key + " value:" + value;
            }
        }

    }


    static class LRUCache {

        LRUCache3<Integer, Integer> lru;

        LRUCache(int capacity) {
            lru = new LRUCache3<>(capacity);
        }

        public int get(int key) {
            Integer value = lru.get(key);
            return value == null ? 0 : value;
        }

        public void put(int key, int value) {
            lru.put(key, value);
        }

        static class LRUCache2<K, V> {

            public static final int DEF_CAPACITY = 10;

            private final int capacity;

            private HashMap<K, Node> cache;

            private Node firstPre;

            private Node tailNext;

            public LRUCache2() {
                this(DEF_CAPACITY);
            }

            public LRUCache2(int capacity) {
                this.capacity = capacity;
                init();
            }

            private void init() {
                cache = new HashMap<>(capacity);
                firstPre = new Node(null, null);
                tailNext = new Node(null, null);
                firstPre.next = tailNext;
                tailNext.pre = firstPre;
            }

            public void put(K k, V v) {
                Node node = cache.get(k);
                if (node == null) {
                    checkCapacityAndRemove();
                    node = new Node(k, v);
                    insertNode(node, firstPre);
                } else {
                    node.value = v;
                }
                cache.put(k, node);
            }

            public V get(K k) {
                Node node = cache.get(k);
                connectNodePreAndNext(node);
                insertNode(node, firstPre);
                return node == null ? null : node.value;
            }

            public void remove(K k) {
                Node node = cache.get(k);
                if (node != null) cache.remove(node.key);
                connectNodePreAndNext(node);
            }

            private void checkCapacityAndRemove() {
                if (cache.size() >= capacity) {
                    if (tailNext.pre != null && tailNext.pre != firstPre) {
                        cache.remove(tailNext.pre.key);
                        connectNodePreAndNext(tailNext.pre);
                    }
                }
            }

            private void connectNodePreAndNext(Node node) {
                if (node == null) return;
                Node pre = node.pre;
                Node next = node.next;
                node.pre = null;
                node.next = null;
                if (pre != null) pre.next = next;
                if (next != null) next.pre = pre;
            }

            private void insertNode(Node node, Node insert) {
                if (node == null) return;
                Node next = insert.next;
                insert.next = node;
                node.pre = insert;
                next.pre = node;
                node.next = next;
            }


            private class Node {

                private final K key;
                private V value;

                private Node pre;
                private Node next;

                private Node(K k, V v) {
                    this.key = k;
                    this.value = v;
                }
            }


        }


        static class LRUCache3<K, V> {

            private static final int DEF_CAPACITY = 10;

            /**
             * 存储使用的节点
             */
            private class Node {

                private final K key;

                private V value;

                private Node pre;

                private Node next;

                private Node(K k, V v) {
                    this.key = k;
                    this.value = v;
                }

            }

            /**
             * 容量
             */
            private final int capacity;

            private final HashMap<K, Node> cache;

            private final Node first;//头节点前面那个节点

            private final Node tail;//尾节点后面那个节点

            public LRUCache3() {
                this(DEF_CAPACITY);
            }

            public LRUCache3(int capacity) {
                this.capacity = capacity;
                cache = new HashMap<>();
                first = new Node(null, null);
                tail = new Node(null, null);
                first.next = tail;
                tail.pre = first;
            }

            /**
             * 存入
             * 这种是修改不改变位置
             *
             * @param k
             * @param v
             */
            public void put(K k, V v) {
                Node node = cache.get(k);
                if (node == null) {
                    if (cache.size() >= capacity) {//移除尾巴
                        removeTail();
                    }
                    node = new Node(k, v);
                    insertNodeNext(node, first);
                } else {
                    node.value = v;
                }
                cache.put(k, node);
            }

            /**
             * 取出
             *
             * @param k
             * @return
             */
            public V get(K k) {
                Node node = cache.get(k);
                connectNodePreNext(node);
                insertNodeNext(node, first);
                return node == null ? null : node.value;
            }


            public void remove(K k) {
                Node node = cache.get(k);
                connectNodePreNext(node);
                if (node != null) {
                    cache.remove(node.key);
                }
            }

            /**
             * 移除尾巴前的一个节点
             */
            private void removeTail() {
                Node pre = tail.pre;
                if (pre != first) {
                    remove(pre.key);
                }
            }

            /**
             * 把node前后节点连接起来
             *
             * @param node
             */
            private void connectNodePreNext(Node node) {
                if (node == null) return;
                Node pre = node.pre;
                Node next = node.next;
                pre.next = next;
                next.pre = pre;
                node.pre = null;
                node.next = null;
            }

            /**
             * 插入到某个节点的下一个
             */
            private void insertNodeNext(Node node, Node insert) {
                if (node == null) return;
                Node fNext = insert.next;
                insert.next = node;
                node.pre = insert;
                node.next = fNext;
                fNext.pre = node;
            }

            /**
             * 插入到链表中
             */
            private void insertFirst(Node node) {
                Node fNext = first.next;
                first.next = node;
                node.pre = first;
                node.next = fNext;
                fNext.pre = node;
            }

        }

    }


}
