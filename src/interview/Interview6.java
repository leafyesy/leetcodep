package interview;

import java.util.Stack;

public class Interview6 {

    private static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args){
        stack.push(1);
//        Object o = stack.lastElement();
        stack.pop();
        System.out.println(stack);
        test();
    }

    private static void test() {
        MinStack ms = new MinStack();
        ms.push(5);
        System.out.println(ms.min());
        ms.push(6);
        ms.push(2);
        ms.push(1);
        System.out.println(ms.min());
        ms.pop();
        System.out.println(ms.min());
        ms.pop();
        System.out.println(ms.min());
    }

    static class MinStack {
        private Stack<Integer> minStack = new Stack<Integer>();
        private Stack<Integer> stack = new Stack<Integer>();

        public int pop() {
            minStack.pop();
            return stack.pop();
        }

        public void push(int num) {
            if (minStack.size() <= 0) {
                minStack.push(num);
                return;
            }
            Integer min = minStack.lastElement();
            if (num < min) {
                minStack.push(num);
            } else {
                minStack.push(min);
            }
            stack.push(num);
        }

        public int min() {
            if (minStack.size() <= 0) {
                return -1;
            }
            return minStack.lastElement();
        }
    }
}
