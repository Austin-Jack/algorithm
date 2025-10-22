package juc.two_thread_printer;

import java.util.concurrent.Semaphore;

class TwoThreadOrderSemaphore {
    static class Printer {
        private final Semaphore s1 = new Semaphore(1);
        private final Semaphore s2 = new Semaphore(0);
        
        public void printEven() throws InterruptedException {
            for (int i = 0; i < 10; i += 2) {
                s1.acquire();
                System.out.println("偶数线程: " + i);
                s2.release();
            }
        }
        
        public void printOdd() throws InterruptedException {
            for (int i = 1; i < 10; i += 2) {
                s2.acquire();
                System.out.println("奇数线程: " + i);
                s1.release();
            }
        }
    }
    
    public static void main(String[] args) {
        Printer printer = new Printer();
        new Thread(() -> {
            try {
                printer.printEven();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "偶数").start();
        
        new Thread(() -> {
            try {
                printer.printOdd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "奇数").start();
    }
}
