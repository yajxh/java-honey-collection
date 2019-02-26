package com.sunny.concurrent.volatilekey;

import java.util.concurrent.CountDownLatch;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/24 11:08 <br>
 * @see com.sunny.concurrent.volatilekey <br>
 */
public class VolatileNonAtomic {
    /**
     * 使用volatile修饰共享资源i
     */
    private static volatile int i = 0;
    private static final CountDownLatch latch = new CountDownLatch(10);

    private static void inc() {
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int x = 0; x < 1000; x++) {
                    inc();
                }
                /**
                 * 使计数器减1
                 */
                latch.countDown();
            }).start();
        }
        // 等待所有的线程完成工作
        latch.await();
        System.out.println(i);
    }
}
