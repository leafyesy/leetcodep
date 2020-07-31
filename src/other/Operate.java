package src.other;

import java.util.ArrayDeque;
import java.util.Deque;

public class Operate {

    private static Add add = new Add();
    private static Sub sub = new Sub();
    private static Mul mul = new Mul();
    private static Div div = new Div();

    /**
     * 支持 + - * /
     *
     * @param str
     * @return
     */
    public static int handleStr(String str) {
        Deque<Integer> numStack = new ArrayDeque<>();
        Deque<Character> operateStack = new ArrayDeque<>();
        int preIndex = 0;
        int index = 0;
        while (index < str.length()) {
            char c = str.charAt(index);
            if (isOperateChar(c)) {
                //表示为操作符
                while (true) {
                    if (operateStack.size() > 0) {
                        Character peek = operateStack.peek();
                        if (isPre(c, peek)) {
                            //表示需要对栈中数据进行操作
                            Character oPop = operateStack.pop();
                            Integer pop1 = numStack.pop();
                            Integer pop2 = numStack.pop();
                            int value = h(pop2, pop1, oPop);
                            numStack.add(value);
                            continue;
                        }
                    }
                    operateStack.add(c);
                    numStack.add(Integer.valueOf(str.substring(preIndex, index)));
                    break;
                }
            }
            preIndex = index + 1;
            index++;
        }
        numStack.add(Integer.valueOf(str.substring(preIndex, index)));
        while (operateStack.size() > 0) {
            Character oPop = operateStack.pop();
            Integer pop1 = numStack.pop();
            Integer pop2 = numStack.pop();
            int value = h(pop2, pop1, oPop);
            numStack.add(value);
        }
        return numStack.pop();
    }

    private static boolean isOperateChar(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * 后一个的优先级是否比较高
     *
     * @return
     */
    private static boolean isPre(char o1, char o2) {
        return (o2 == '*' || o2 == '/') && (o1 == '+' || o1 == '-');
    }

    private static int h(int first, int second, char o) {
        if (o == '+') {
            return add.handle(first, second);
        } else if (o == '-') {
            return sub.handle(first, second);
        } else if (o == '*') {
            return mul.handle(first, second);
        } else if (o == '/') {
            return div.handle(first, second);
        }
        throw new RuntimeException("参数错误");
    }


    interface IOperate {
        int handle(int first, int second);
    }

    static class Add implements IOperate {

        @Override
        public int handle(int first, int second) {
            return first + second;
        }
    }

    static class Sub implements IOperate {

        @Override
        public int handle(int first, int second) {
            return first - second;
        }
    }

    static class Mul implements IOperate {

        @Override
        public int handle(int first, int second) {
            return first * second;
        }
    }

    static class Div implements IOperate {

        @Override
        public int handle(int first, int second) {
            return first / second;
        }
    }


}
