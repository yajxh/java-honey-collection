package com.sunny.concurrent.synchronizedkey;

/**
 * <Description> synchronized作用于类实例方法,因为每个线程是不同的实例对象，所以synchronized并不能生效<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/27 14:31 <br>
 * @see com.sunny.concurrent.synchronizedkey <br>
 */
public class SynchronizedMethodIncorrect implements Runnable{
    private static int count = 0;

    public synchronized void increase(){
        count++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000000; i++) {
            increase();
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodIncorrect smi1 = new SynchronizedMethodIncorrect();
        SynchronizedMethodIncorrect smi2 = new SynchronizedMethodIncorrect();
        SynchronizedMethodIncorrect smi3 = new SynchronizedMethodIncorrect();
        Thread t1 = new Thread(smi1);
        Thread t2 = new Thread(smi2);
        Thread t3 = new Thread(smi3);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println(count);
    }
}
