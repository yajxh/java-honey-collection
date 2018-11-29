package com.sunny.concurrent.concurrenttool;

/**
 * <Description> join用于让当前执行线程等待join线程执行结束。其实现原理是不停检查join线程是否存活，
 * 如果join线程存活则让当前线程永远等待。其中，wait（0）表示永远等待下去，代码片段如下。
 * while (isAlive()) {wait(0);}
 * 直到join线程中止后，线程的this.notifyAll()方法会被调用，调用notifyAll()方法是在JVM里实现的，
 * 所以在JDK里看不到，大家可以查看JVM源码。<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/10 10:05 <br>
 * @see com.sunny.concurrent.concurrenttool <br>
 */
public class JoinCountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        Thread parser1 = new Thread(() -> {

        });

        Thread parser2 = new Thread(() -> {
            System.out.println("parser2 finish!");
        });

        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
    }
}
