package com.sunny.jdk.concurrent.concurrenttool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * <Description> CyclicBarrier还提供其他有用的方法，比如getNumberWaiting方法可以获得
 * Cyclic-Barrier阻塞的线程数量。isBroken()方法用来了解阻塞的线程是否被中断。<br>
 *
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/10 10:37 <br>
 * @see com.sunny.jdk.concurrent.concurrenttool <br>
 */
public class CyclicBarrierTest3 {
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();
        t.interrupt();

        try {
            c.await();
        } catch (Exception e) {
            System.out.println(c.isBroken());
        }

    }
}
