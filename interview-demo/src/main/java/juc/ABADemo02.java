package juc;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo02 {
    public static void main(String[] args) throws InterruptedException {
        
        AtomicStampedReference<Integer> atomicStampedValue = new AtomicStampedReference<>(10,0);
        Thread t1 = new Thread(()->{
            try{
                System.out.println("线程1开始执行");
                int[] flag = new int[1];
                int currentValue = atomicStampedValue.get(flag);
                int currentStamp = flag[0];
                System.out.println("线程1读取到的值是"+currentValue+"\t版本号是"+currentStamp);
                //线程1 阻塞
                Thread.sleep(2000);
                // 尝试CAS操作 需要检查值和版本号
                boolean success = atomicStampedValue.compareAndSet(currentValue, 20, currentStamp, currentStamp + 1);
                System.out.println("线程1CAS的操作结果:"+ success+"\t当前值是:"+atomicStampedValue.getReference()+"\t版本号"+atomicStampedValue.getStamp());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        // 线程2 先修改10->15 再修改15->10
        Thread t2 = new Thread(()->{
            try{
                // 等待线程1读取值
                Thread.sleep(500);
                int[] flag = new int[1];
                //=============第一次修改=====================
                Integer currentValue = atomicStampedValue.get(flag);
                int currentStamp = flag[0];
                System.out.println("线程2第一次修改: 10->15");
                boolean success1 = atomicStampedValue.compareAndSet(currentValue, 15, currentStamp, currentStamp + 1);
                System.out.println("线程2CAS的操作结果:"+ success1+"\t当前值是:"+atomicStampedValue.getReference()+"\t版本号"+atomicStampedValue.getStamp());

                Thread.sleep(500);
                //=============第一次修改=====================
                currentValue = atomicStampedValue.get(flag);
                currentStamp = flag[0];
                System.out.println("线程2第二次修改: 15->10");
                boolean success2 = atomicStampedValue.compareAndSet(currentValue, 10, currentStamp, currentStamp + 1);
                System.out.println("线程2CAS的操作结果:"+ success2+"\t当前值是:"+atomicStampedValue.getReference()+"\t版本号"+atomicStampedValue.getStamp());
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}