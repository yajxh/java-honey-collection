package com.sunny.concurrent.hashmap;

import org.junit.Test;

import java.util.HashMap;
import java.util.UUID;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/23 18:53 <br>
 * @see com.sunny.concurrent.hashmap <br>
 */
public class HashMapTest {

    @Test
    public void testTraversal() {
        HashMap<Integer, String> map = new HashMap(16);
        map.put(7, "");
        map.put(11, "");
        map.put(43, "");
        map.put(59, "");
        map.put(19, "");
        map.put(3, "");
        map.put(35, "");

        System.out.println("遍历结果：");
        for (Integer key : map.keySet()) {
            System.out.print(key + " -> ");
        }
    }

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);

        Thread t = new Thread(new Runnable() {

            @Override

            public void run() {

                for (int i = 0; i < 100000; i++) {

                    new Thread(new Runnable() {

                        @Override

                        public void run() {

                            map.put(UUID.randomUUID().toString(), "");

                        }

                    }, "ftf" + i).start();

                }

            }

        }, "ftf");

        t.start();

        t.join();
    }
}
