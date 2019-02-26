package com.sunny.concurrent.volatilekey;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/18 23:06 <br>
 * @see com.sunny.concurrent.volatilekey <br>
 */
public class VolatileTest {
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (count == 0) {}
        }).start();

        Thread.sleep(1000);
        count = 1;
    }

}
