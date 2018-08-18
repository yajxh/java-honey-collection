package com.sunny.jdk.concurrent.concurrenttool;

import java.util.concurrent.CountDownLatch;

/**
 * <Description> CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果你想等待N个点完成，
 * 这里就传入N。当我们调用CountDownLatch的countDown方法时，N就会减1，CountDownLatch的await方法会阻塞当前线程，
 * 直到N变成零。由于countDown方法可以用在任何地方，所以这里说的N个点，可以是N个线程，也可以是1个线程里的N个执行步骤。
 * 用在多个线程时，只需要把这个CountDownLatch的引用传递到线程里即可。<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/10 10:08 <br>
 * @see com.sunny.jdk.concurrent.concurrenttool <br>
 */
public class CountDownLatchTest {
    static CountDownLatch c = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(1);
            c.countDown();
            System.out.println(2);
            c.countDown();
        }).start();
        Thread.sleep(10000);
        System.out.println(3);
    }
}
