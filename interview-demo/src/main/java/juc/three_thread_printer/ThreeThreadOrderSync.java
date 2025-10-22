package juc.three_thread_printer;

class ThreeThreadOrderSync {
    static class Printer {
        private int flag = 1;
        
        public synchronized void thread1() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                while (flag != 1) wait();
                System.out.println("线程1执行 - 第" + (i + 1) + "次");
                flag = 2;
                notifyAll();
            }
        }
        
        public synchronized void thread2() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                while (flag != 2) wait();
                
                System.out.println("线程2执行 - 第" + (i + 1) + "次");
                flag = 3;
                notifyAll();
            }
        }
        
        public synchronized void thread3() throws InterruptedException {
            for (int i = 0; i < 3; i++) {
                while (flag != 3) wait();
                System.out.println("线程3执行 - 第" + (i + 1) + "次");
                flag = 1;
                notifyAll();
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