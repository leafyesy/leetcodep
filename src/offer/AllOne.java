package src.offer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AllOne {


    public static void main(String[] args) {
        AllOne allOne = new AllOne();
//        allOne.inc("hello");
//        allOne.inc("hello");
//        System.out.println("max:" + allOne.getMaxKey());
//        System.out.println("min:" + allOne.getMinKey());
//        allOne.inc("leet");
//        System.out.println("max:" + allOne.getMaxKey());
//        System.out.println("min:" + allOne.getMinKey());

        //["AllOne","inc","inc","inc","inc","getMaxKey","inc","inc","inc","dec","inc","inc","inc","getMaxKey"]
        //[[],["hello"],["goodbye"],["hello"],["hello"],[],["leet"],["code"],["leet"],["hello"],["leet"],["code"],["code"],[]]
//        allOne.inc("hello");
//        allOne.inc("goodbye");
//        allOne.inc("hello");
//        allOne.inc("hello");
//        System.out.println("max:" + allOne.getMaxKey());
//        allOne.inc("leet");
//        allOne.inc("code");
//        allOne.inc("leet");
//        allOne.dec("hello");
//        allOne.inc("leet");
//        allOne.inc("code");
//        //allOne.inc("code");
//        System.out.println("max:" + allOne.getMaxKey());
        //["AllOne","inc","inc","inc","inc","inc","inc","dec", "dec","getMinKey","dec","getMaxKey","getMinKey"]
        //[[],["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],["a"],[],[]]
        allOne.inc("a");
        allOne.inc("b");
        allOne.inc("b");
        allOne.inc("c");
        allOne.inc("c");
        allOne.inc("c");
        allOne.dec("b");
        allOne.dec("b");
        System.out.println("max:" + allOne.getMaxKey());
        allOne.dec("a");
        System.out.println("max:" + allOne.getMaxKey());
        System.out.println("min:" + allOne.getMinKey());
    }


    /**
     * 存储数据,直接查询使用
     **/
    private final HashMap<String, Integer> cache = new HashMap<>();
    /**
     * 存储value->key,用于保存大小关系顺序
     **/
    private final HashMap<Integer, Node> nodeCache = new HashMap<>();
    /**
     * 虚拟头节点-连接最小值
     **/
    private final Node firstPre = new Node(Integer.MIN_VALUE);
    /**
     * 虚拟尾节点-连接最大值
     **/
    private final Node tailNext = new Node(Integer.MAX_VALUE);

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
        firstPre.next = tailNext;
        tailNext.pre = firstPre;
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        //存入到HashMap中
        Integer preValue = cache.get(key);
        int newValue;
        if (preValue == null) {
            newValue = 1;
        } else {
            newValue = preValue + 1;
        }
        cache.put(key, newValue);
        moveNodeToNewPosition(key, preValue == null ? 0 : preValue, newValue);
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        //减少或者移除
        Integer preValue = cache.get(key);
        if (preValue != null) {
            int newValue = preValue - 1;
            if (newValue == 0) {
                cache.remove(key);
            } else {
                cache.put(key, newValue);
            }
            moveNodeToNewPosition(key, preValue, newValue);
        }
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (tailNext.pre == firstPre) return "";
        return tailNext.pre.getOneKey();
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (firstPre.next == tailNext) return "";
        return firstPre.next.getOneKey();
    }

    private void moveNodeToNewPosition(String key, int oldV, int newV) {
        System.out.println("key:" + key + " oldV:" + oldV + " newV:" + newV);
        //把key从旧的里面删除 移动到新的
        Node oldNode = nodeCache.get(oldV);

        Node newNode = nodeCache.get(newV);
        if (newNode == null) {
            if (newV > 0) {
                //没有这个数据,要自己创建
                newNode = new Node(newV);
                Node pre = oldNode == null ? firstPre : oldNode;
                if (oldV > newV) {//表示减法
                    insertNode(pre.pre, newNode);
                } else {
                    insertNode(pre, newNode);
                }
            }
        }
        if (newNode != null)
            newNode.putKey(key);

        nodeCache.put(newV, newNode);
        if (oldNode != null) {
            oldNode.removeKey(key);
            if (oldNode.isEmpty()) {
                removeNode(oldNode);
                nodeCache.remove(oldV);
            }
        }
        System.out.println("[");
        Node cur = firstPre;
        while (cur.next != null) {
            System.out.println("" + cur.next);
            cur = cur.next;
        }
        System.out.println("]");
    }

    private void insertNode(Node nodePosition, Node insertNode) {
        System.out.println("insert node:  position:" + nodePosition.value + " insertNode:" + insertNode.value);
        Node next = nodePosition.next;
        nodePosition.next = insertNode;
        insertNode.pre = nodePosition;
        insertNode.next = next;
        next.pre = insertNode;
    }

    private void removeNode(Node node) {
        if (node == null) return;
        Node pre = node.pre;
        Node next = node.next;
        if (pre != null) pre.next = next;
        if (next != null) next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    private static class Node {
        private final int value;
        private final HashMap<String, Integer> hashMap = new HashMap<>();

        private Node pre;
        private Node next;

        Node(int value) {
            this.value = value;
        }

        void putKey(String key) {
            hashMap.put(key, value);
        }

        String getOneKey() {
            Iterator<Map.Entry<String, Integer>> it = hashMap.entrySet().iterator();
            if (it.hasNext()) return it.next().getKey();
            return "";
        }

        void removeKey(String key) {
            hashMap.remove(key);
        }

        boolean isEmpty() {
            return hashMap.size() == 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", hashMap=" + hashMap +
                    ", pre=" + (pre == null ? "-1" : pre.value) +
                    ", next=" + (next == null ? "-1" : next.value) +
                    '}';
        }
    }
}
