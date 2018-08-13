package com.sunny.jdk.concurrent.container;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/09 13:09 <br>
 * @see com.sunny.jdk.concurrent.container <br>
 */
public class HashMapInfiniteLoop {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf-" + i).start();
                }
            }
        }, "ftf");

        t.start();
        t.join();
    }
}
