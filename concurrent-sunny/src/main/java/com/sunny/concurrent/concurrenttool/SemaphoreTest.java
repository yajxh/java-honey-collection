package com.sunny.concurrent.concurrenttool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <Description> 在代码中，虽然有30个线程在执行，但是只允许10个并发执行。
 * Semaphore的构造方法Semaphore（int permits）接受一个整型的数字，表示可用的许可证数量。
 * Semaphore（10）表示允许10个线程获取许可证，也就是最大并发数是10。Semaphore的用法也很简单，
 * 首先线程使用Semaphore的acquire()方法获取一个许可证，使用完之后调用release()方法归还许可证。
 * 还可以用tryAcquire()方法尝试获取许可证。<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/10 10:49 <br>
 * @see com.sunny.concurrent.concurrenttool <br>
 */
public class SemaphoreTest {
    private static final int THREAD_COUNT = 100;
    private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);

    private static Semaphore s = new Semaphore(10);

    public static void main(String[] args) {
        for (int i = 0; i < THREAD_COUNT; i++) {
            threadPool.execute(() -> {
                try {
                    s.acquire();
                    System.out.println("save data");
                    //Thread.sleep(10000);
                    TimeUnit.MILLISECONDS.sleep(10000);
                    s.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPool.shutdown();
    }

}
