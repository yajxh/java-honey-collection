package com.sunny.jdk.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * <Description> 创建了10个线程，编号0~9，每个线程调用前一个线程的join()方法，也就是线程0结束了，
 * 线程1才能从join()方法中返回，而线程0需要等待main线程结束。<br>
 *
 * 输出如下。main terminate.
 * 0 terminate.
 * 1 terminate.
 * 2 terminate.
 * 3 terminate.
 * 4 terminate.
 * 5 terminate.
 * 6 terminate.
 * 7 terminate.
 * 8 terminate.
 * 9 terminate.
 * 从上述输出可以看到，每个线程终止的前提是前驱线程的终止，每个线程等待前驱线程终止后，
 * 才从join()方法返回，这里涉及了等待/通知机制（等待前驱线程结束，接收前驱线程结束通知）。
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/08 11:07 <br>
 * @see com.sunny.jdk.concurrent.thread <br>
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), "DominoThread - - -" + String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {
        private Thread thread;
        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }
}
