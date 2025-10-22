package juc.two_thread_printer;

class TwoThreadOrderSync {
    static class Printer {
        private int count = 0;
        
        public synchronized void printEven() {
            while (count < 10) {
                if (count % 2 == 0) {
                    System.out.println("偶数线程: " + count);
                    count++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        
        public synchronized void printOdd() {
            while (count < 10) {
                if (count % 2 == 1) {
                    System.out.println("奇数线程: " + count);
                    count++;
                    notifyAll();
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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