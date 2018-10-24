package com.sunny.jdk.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * <Description> HashMap的死循环, JDK1.8不会出现该问题<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/22 10:59 <br>
 * @see com.sunny.jdk.hashmap <br>
 */
public class HashMapHangDemo {
    final Map<Integer, Object> holder = new HashMap<Integer, Object>();

    public static void main(String[] args) {
        HashMapHangDemo demo = new HashMapHangDemo();
        for (int i = 0; i < 100; i++) {
            demo.holder.put(i, null);
        }

        Thread thread = new Thread(demo.getConcurrencyCheckTask());
        thread.start();
        thread = new Thread(demo.getConcurrencyCheckTask());
        thread.start();

        System.out.println("Start get in main!");
        for (int i = 0; ; ++i) {
            for (int j = 0; j < 10000; ++j) {
                demo.holder.get(j);

                // 如果出现hashmap的get hang住问题，则下面的输出就不会再出现了。
                // 在我的开发机上，很容易在第一轮就观察到这个问题。
                System.out.printf("Got key %s in round %s\n", j, i);
            }
        }
    }

    ConcurrencyTask getConcurrencyCheckTask() {
        return new ConcurrencyTask();
    }

    private class ConcurrencyTask implements Runnable {
        Random random = new Random();

        /**
         * When an object implementing interface <code>Runnable</code> is used
         * to create a thread, starting the thread causes the object's
         * <code>run</code> method to be called in that separately executing
         * thread.
         * <p>
         * The general contract of the method <code>run</code> is that it may
         * take any action whatsoever.
         *
         * @see Thread#run()
         */
        @Override
        public void run() {
            System.out.println("Add loop started in task!");

            while (true) {
                holder.put(random.nextInt() % (1024 * 1024 * 100), null);
            }
        }
    }
}
