package com.sunny.jdk.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * <Description> 以下例子主要说明了调用wait()、notify()以及notifyAll()
 *                  时需要注意的细节，如下。
 * 1）使用wait()、notify()和notifyAll()时需要先对调用对象加锁。
 * 2）调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列。
 * 3）notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifAll()的线程释放锁之后，
 *      等待线程才有机会从wait()返回。
 * 4）notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将等待队列中
 *      所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
 * 5）从wait()方法返回的前提是获得了调用对象的锁。从上述细节中可以看到，等待/通知机制依托于同步机制，
 *      其目的就是确保等待线程从wait()方法返回时能够感知到通知线程对变量做出的修改。<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/08 9:16 <br>
 * @see com.sunny.jdk.concurrent.thread <br>
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(), "WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }

    static class Wait implements Runnable {

        @Override
        public void run() {
            //加锁拥有lock的monitor
            synchronized (lock) {
                // 当条件不满足时，继续wait，同时释放了lock的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ "
                                + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ "
                        + new SimpleDateFormat("HH:mm:ss").format(new Date()));

            }

        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            //加锁拥有lock的monitor
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread() + " hold lock. notify @ "
                            + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                    lock.notifyAll();
                    flag = false;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep " +
                        "@ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
