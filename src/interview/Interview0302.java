package src.interview;

import java.util.Stack;

public class Interview0302 {

    class MinStack2 {

        private class Node {
            int val;
            int min;

            Node(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        private Stack<Node> stack = new Stack<>();

        public MinStack2() {

        }

        public void push(int x) {
            if (stack.empty()) {
                Node node = new Node(x, x);
                stack.push(node);
            } else {
                Node peek = stack.peek();
                Node node = new Node(x, Math.min(peek.min, x));
                stack.push(node);
            }
        }

        public void pop() {
            if (stack.empty()) return;
            stack.pop();
        }

        public int top() {
            if (!stack.empty())
                return stack.peek().val;
            return -1;//可以改成抛出异常
        }

        public int getMin() {
            if (stack.empty()) return -1;
            return stack.peek().min;
        }
    }


    class MinStack {

        private Stack<Integer> stack = new Stack<>();
        private Stack<Integer> minStack = new Stack<>();

        /**
         * initialize your data structure here.
         */
        public MinStack() {

        }

        public void push(int x) {
            stack.push(x);
            if (minStack.empty() || minStack.peek() >= x) {
                minStack.push(x);
            }
        }

        public void pop() {
            if (stack.empty()) return;
            Integer pop = stack.pop();
            if (pop.equals(minStack.peek())) {
                minStack.pop();
            }
        }

        public int top() {
            if (!stack.empty())
                return stack.peek();
            return -1;//可以改成抛出异常
        }

        public int getMin() {
            if (minStack.empty()) return -1;
            return minStack.peek();
        }
    }
}
