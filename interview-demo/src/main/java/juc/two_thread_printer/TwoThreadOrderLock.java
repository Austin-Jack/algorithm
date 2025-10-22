package juc.two_thread_printer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TwoThreadOrderLock {
    static class Printer {
        private int count = 0;
        private final Lock lock = new ReentrantLock();
        private final Condition condition = lock.newCondition();
        
        public void printEven() {
            while (count < 10) {
                lock.lock();
                try {
                    while (count % 2 == 1) {
                        condition.await();
                    }
                    System.out.println("偶数线程: " + count);
                    count++;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
        
        public void printOdd() {
            while (count < 10) {
                lock.lock();
                try {
                    while (count % 2 == 0) {
                        condition.await();
                    }
                    System.out.println("奇数线程: " + count);
                    count++;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> printer.printEven(), "偶数").start();
        new Thread(() -> printer.printOdd(), "奇数").start();
    }
}
