package src.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

public class Thread3Test {
    private static int numA = 1;

    static {
        System.out.println("static block:" + numA);
    }

    public Thread3Test() {
        System.out.println("Thread3Test");
        {
            System.out.println("Thread3Test normal block");
        }
    }

    {
        System.out.println("normal block");
    }

    /*
    cFizz await 1
    fizzbuzz await 1
    cBuzz await 1
    count:1
    accept num:1
    count:2
    accept num:2
    count:3
    cFizz notify 1
    cFizz await 2
    count:4
    accept num:4
    count:5
    fizzbuzz notify 1
    fizzbuzz await 2
    count:6
    cFizz notify 2
    cFizz await 2
    count:7
    accept num:7
    num return
     */

    public static void main(String[] args) {
        Thread3Test thread3Test = new Thread3Test();
        Thread3Test thread3Test2 = new Thread3Test();

        FizzBuzz2 fizzBuzz2 = new FizzBuzz2(15);
        Runnable runFizz = () -> System.out.println("fizz");
        Runnable runBuzz = () -> System.out.println("buzz");
        Runnable runFizzbuzz = () -> System.out.println("fizzbuzz");
        Thread t1 = new Thread(() -> {
            try {
                fizzBuzz2.fizz(runFizz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                fizzBuzz2.buzz(runBuzz);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread();
        Thread t4 = new Thread();

    }


    static class FizzBuzz2 {
        private int n;

        private final Object lock = new Object();

        private final Object lock1 = new Object();
        private final Object lock2 = new Object();
        private final Object lock3 = new Object();

        private volatile int count = 0;
        private volatile int closeThreadCount = 0;

        public FizzBuzz2(int n) {
            this.n = n;
        }

        public void fizz(Runnable printFizz) throws InterruptedException {
            synchronized (lock1) {
                while (true) {
                    if (count > n) {
                        closeThreadCount++;
                        lock.notifyAll();
                        System.out.println("fizz return 1");
                        return;
                    }
                    if (count % 3 == 0 && count % 5 != 0) {
                        printFizz.run();
                    }
                    lock.notifyAll();
                    lock1.wait();
                }
            }
        }

        public void buzz(Runnable printBuzz) throws InterruptedException {
            synchronized (lock2) {
                while (true) {
                    if (count > n) {
                        closeThreadCount++;
                        lock.notifyAll();
                        System.out.println("buzz return 1");
                        return;
                    }
                    if (count % 5 == 0 && count % 3 != 0) {
                        printBuzz.run();
                    }
                    lock.notifyAll();
                    lock2.wait();
                }
            }
        }

        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            synchronized (lock3) {
                while (true) {
                    if (count > n) {
                        closeThreadCount++;
                        lock.notifyAll();
                        System.out.println("fizzbuzz return 1");
                        return;
                    }
                    if (count != 0 && (count % 3 == 0 && count % 5 == 0)) {
                        printFizzBuzz.run();
                    }
                    lock.notifyAll();
                    lock3.wait();
                }
            }
        }

        //生产者
        public void number(IntConsumer printNumber) throws InterruptedException {
            synchronized (lock) {
                while (true) {
                    count++;
                    System.out.println("count:" + count);
                    if (count > n) {
                        while (true) {
                            lock1.notifyAll();
                            lock2.notifyAll();
                            lock3.notifyAll();
                            lock.wait();
                            if (closeThreadCount >= 3) {
                                return;
                            }
                        }
                    }
                    if (count % 3 != 0 && count % 5 != 0) {
                        printNumber.accept(count);
                        continue;
                    }
                    if (count % 3 == 0 && count % 5 != 0) {
                        lock1.notifyAll();
                        lock.wait();
                    } else if (count % 5 == 0 && count % 3 != 0) {
                        lock2.notifyAll();
                        lock.wait();
                    } else if (count % 3 == 0 && count % 5 == 0) {
                        lock3.notifyAll();
                        lock.wait();
                    }
                }
            }
        }
    }

    class FizzBuzz {
        private int n;

        private int count = 0;

        ReentrantLock lock = new ReentrantLock();
        Condition cInt = lock.newCondition();
        Condition cFizz = lock.newCondition();
        Condition cBuzz = lock.newCondition();
        Condition cFizzbuzz = lock.newCondition();


        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            while (true) {
                lock.lock();
                if (count == 0 || (count % 3 == 0 && count % 5 == 0) || (count % 3 != 0)) {
                    cInt.signal();
                    //表示不是自己的,等待
                    cFizz.await();
                }
                if (count % 3 == 0 && count % 5 != 0) {
                    //表示可以打印
                    printFizz.run();
                }
                if (count >= n) {
                    cInt.signal();
                    lock.unlock();
                    return;
                }
                cInt.signal();
                cFizz.await();
                lock.unlock();
            }

        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (true) {
                lock.lock();
                if (count == 0 || (count % 5 == 0 && count % 3 == 0) || (count % 5 != 0)) {
                    cInt.signal();
                    //表示不是自己的,等待
                    cBuzz.await();
                }
                if (count % 5 == 0 && count % 3 != 0) {
                    //表示可以打印
                    printBuzz.run();
                }
                if (count > n) {
                    cInt.signal();
                    lock.unlock();
                    return;
                }
                cInt.signal();
                cBuzz.await();
                lock.unlock();
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (true) {
                lock.lock();
                if (count == 0 || count % 5 != 0 || count % 3 != 0) {
                    cInt.signal();
                    //表示不是自己的,等待
                    cBuzz.await();
                }
                if (count % 5 == 0 && count % 3 != 0) {
                    //表示可以打印
                    printFizzBuzz.run();
                }
                if (count > n) {
                    cInt.signal();
                    lock.unlock();
                    return;
                }
                cInt.signal();
                cBuzz.await();
                lock.unlock();

            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                lock.lock();
                count++;
                if (count % 3 == 0 && count % 5 != 0) {
                    cFizz.signal();
                    cInt.await();
                    continue;
                }
                if (count % 5 == 0 && count % 3 != 0) {
                    cBuzz.signal();
                    cInt.await();
                    continue;
                }
                if (count % 3 == 0 && count % 5 == 0) {
                    cFizzbuzz.signal();
                    cInt.await();
                    continue;
                }
                printNumber.accept(count);
                if (count > n) {
                    cFizz.signal();
                    cBuzz.signal();
                    cFizzbuzz.signal();
                    lock.unlock();
                    return;
                }
                lock.unlock();
            }
        }
    }


}
