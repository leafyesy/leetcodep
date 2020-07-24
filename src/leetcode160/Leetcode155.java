package src.leetcode160;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode155 {


    class MinStack {

        List<Integer> value = new ArrayList<>();//存储栈元素
        List<Integer> index = new ArrayList<>();//存储栈大小关系

        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            value.add(x);
            indexAdd(x);
        }

        private void indexAdd(int x){
            if(index.isEmpty()){
                index.add(x);
            }else{
                int pre = index.get(index.size() - 1);
                index.add(Math.min(pre,x));
            }
        }

        public void pop() {
            if(value.isEmpty()){
                return;
            }
            int i = value.size() - 1;
            value.remove(i);
            index.remove(i);
        }

        public int top() {
            if(value.isEmpty()){
                return 0;
            }
            return value.get(value.size() - 1);
        }

        public int getMin() {
            if(index.isEmpty()){
                return 0;
            }
            return index.get(index.size() - 1);
        }
    }


}
