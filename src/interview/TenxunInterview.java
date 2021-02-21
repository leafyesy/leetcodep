package src.interview;

import src.base.ListNode;
import java.util.LinkedList;
import java.util.Queue;

public class TenxunInterview {


    public static void main(String[] args) {
        ThreadProducerConsumer threadProducerConsumer = new ThreadProducerConsumer();
        threadProducerConsumer.start();
    }


    static class ThreadProducerConsumer {

        private static final int MAX_SIZE = 20;

        private volatile int count = 0;

        private final Queue<Integer> queue = new LinkedList<>();

        private final Object LOCK = new Object();

        private volatile boolean isStop = false;

        public void start() {
            Producer producer1 = new Producer();
            Producer producer2 = new Producer();
            Producer producer3 = new Producer();
            Consumer consumer1 = new Consumer();
            Consumer consumer2 = new Consumer();
            Consumer consumer3 = new Consumer();
            Consumer consumer4 = new Consumer();
            Consumer consumer5 = new Consumer();

            Thread pThread1 = new Thread(producer1);
            Thread pThread2 = new Thread(producer2);
            Thread pThread3 = new Thread(producer3);
            Thread cThread1 = new Thread(consumer1);
            Thread cThread2 = new Thread(consumer2);
            Thread cThread3 = new Thread(consumer3);
            Thread cThread4 = new Thread(consumer4);
            Thread cThread5 = new Thread(consumer5);

            pThread1.start();
            pThread2.start();
            pThread3.start();
            cThread1.start();
            cThread2.start();
            cThread3.start();
            cThread4.start();
            cThread5.start();

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            stop();
        }

        public void stop() {
            isStop = true;
        }

        private void consumer() throws InterruptedException {
            synchronized (LOCK) {
                if (queue.size() == 0) {
                    LOCK.wait();
                    return;
                }
                Thread.sleep(10);
                Integer poll = queue.poll();
                System.out.println("c:" + poll);
                LOCK.notify();
            }

        }

        private void producer() throws InterruptedException {
            synchronized (LOCK) {
                if (queue.size() >= MAX_SIZE) {
                    LOCK.wait();
                    return;
                }
                Thread.sleep(10);
                System.out.println("p:" + count++);
                queue.add(count);
                LOCK.notify();
            }
        }

        class Producer implements Runnable {

            @Override
            public void run() {
                try {
                    while (!isStop) {
                        producer();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        class Consumer implements Runnable {

            @Override
            public void run() {
                try {
                    while (!isStop) {
                        consumer();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private static ListNode reverse(ListNode root) {
        if (root == null || root.next == null) {
            return null;
        }
        ListNode pre = root;
        ListNode cur = root.next;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        root.next = null;
        return pre;
    }


}
