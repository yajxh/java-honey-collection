package com.sunny.concurrent.volatilekey;

import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/19 15:11 <br>
 * @see com.sunny.concurrent.volatilekey <br>
 */
public class TestVolatile2 {
    //private int num_0 = 0;

    private volatile int num_0 = 0;

    public void thread_0() {
        while (num_0 == 0) {
        }
        System.out.println("thread 0 end");
    }

    public void thread_1() {
        this.num_0 = 1;
    }

    public static void main(String[] args) throws InterruptedException {
        TestVolatile2 test = new TestVolatile2();
        new Thread(() -> {
            test.thread_0();
        }).start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("start thread2");

        new Thread(() -> {
            test.thread_1();
        }).start();

    }

}
