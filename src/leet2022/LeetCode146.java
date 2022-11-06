package src.leet2022;

import java.util.HashMap;
import java.util.LinkedList;

public class LeetCode146 {

    class LRUCache {

        int capacity;
        HashMap<Integer, Integer> map = new HashMap<>();
        LinkedList<Integer> list = new LinkedList<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {
            Integer result = map.get(key);
            if (result != null) {
                // 调整位置
                list.remove((Object)key);
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

}
