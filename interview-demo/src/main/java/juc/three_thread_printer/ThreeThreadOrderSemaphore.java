package juc.three_thread_printer;

import java.util.concurrent.Semaphore;

class ThreeThreadOrderSemaphore {
    static class Printer {
        private final Semaphore s1 = new Semaphore(1);
        private final Semaphore s2 = new Semaphore(0);
        private final Semaphore s3 = new Semaphore(0);
        
        public void thread1() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                s1.acquire();
                System.out.println("线程1执行 - 第" + (i + 1) + "次");
                s2.release();
            }
        }
        
        public void thread2() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                s2.acquire();
                System.out.println("线程2执行 - 第" + (i + 1) + "次");
                s3.release();
            }
        }
        
        public void thread3() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                s3.acquire();
                System.out.println("线程3执行 - 第" + (i + 1) + "次");
                s1.release();
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