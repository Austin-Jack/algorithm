package juc.two_thread_printer;

public class TwoNumPrinter {
    static final Object lock = new Object();
    static int max = 10;
    static int count = 1;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                while (count < max) {
                    if (count % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }, "奇数线程").start();
        
        
        new Thread(() -> {
            synchronized (lock) {
                while (count < max) {
                    if (count % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " " + count);
                        count++;
                        lock.notifyAll();
                    } else {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }, "偶数线程").start();
        
        Thread.sleep(1000);
    }
}
