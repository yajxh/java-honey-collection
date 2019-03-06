package com.sunny.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/23 9:32 <br>
 * @see com.sunny.concurrent <br>
 */
public class StopThreadTest implements Runnable  {

    private boolean flag = true;
    public static void main(String[] args) throws InterruptedException {
        StopThreadTest stopThreadTest = new StopThreadTest();
        Thread thread = new Thread(stopThreadTest);
        thread.start();
        try {
            //Thread.sleep(1000);
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopThreadTest.flag = false;
    }

    @Override
    public void run() {
        int i = 0;
        while (flag) {
            System.out.println(i);
        }
        System.out.println("计数器已经停止");
    }

}
