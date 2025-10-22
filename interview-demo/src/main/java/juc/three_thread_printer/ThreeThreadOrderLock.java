package juc.three_thread_printer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ThreeThreadOrderLock {
    static class Printer {
        private int flag = 1;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        
        public void thread1() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 1) condition.await();
                    System.out.println("线程1执行 - 第" + (i + 1) + "次");
                    flag = 2;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
        
        public void thread2() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 2) condition.await();
                    System.out.println("线程2执行 - 第" + (i + 1) + "次");
                    flag = 3;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
        
        public void thread3() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                lock.lock();
                try {
                    while (flag != 3) condition.await();
                    System.out.println("线程3执行 - 第" + (i + 1) + "次");
                    flag = 1;
                    condition.signalAll();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> {
            try {
                printer.thread1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T1").start();
        new Thread(() -> {
            try {
                printer.thread2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T2").start();
        new Thread(() -> {
            try {
                printer.thread3();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "T3").start();
    }
}