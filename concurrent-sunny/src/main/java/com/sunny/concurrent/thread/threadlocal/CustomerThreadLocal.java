package com.sunny.concurrent.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/01/03 9:55 <br>
 * @see com.sunny.concurrent.thread.threadlocal <br>
 */
public class CustomerThreadLocal {
    public static class CommonThread extends Thread {
        Map<Integer, Integer> cacheMap = new HashMap<>();
    }

    public static class CustomThreadLocal {
        private int defaultValue;

        public CustomThreadLocal(int value) {
            defaultValue = value;
        }

        public Integer get() {
            Integer id = this.hashCode();
            Map<Integer, Integer> cacheMap = getMap();
            if (cacheMap.containsKey(id)) {
                return cacheMap.get(id);
            }
            return defaultValue;
        }

        public void set(int value) {
            Integer id = this.hashCode();
            Map<Integer, Integer> cacheMap = getMap();
            cacheMap.put(id, value);
        }

        public Map<Integer, Integer> getMap() {
            CommonThread thread = (CommonThread) Thread.currentThread();
            return thread.cacheMap;
        }
    }

    public static class Number {
        private CustomThreadLocal value = new CustomThreadLocal(0);

        public void increase() throws InterruptedException {
            value.set(10);
           // Thread.sleep(10);
            TimeUnit.MILLISECONDS.sleep(10);
            System.out.println("increase value: " + value.get());
        }

        public void decrease() throws InterruptedException {
            value.set(-10);
            //Thread.sleep(10);
            TimeUnit.MILLISECONDS.sleep(10);
            System.out.println("decrease value: " + value.get());
        }
    }


    public static void main(String[] args) throws InterruptedException {
        final Number number = new Number();
        Thread increaseThread = new CommonThread() {
            @Override
            public void run() {
                try {
                    number.increase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };

        Thread decreaseThread = new CommonThread() {
            @Override
            public void run() {
                try {
                    number.decrease();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        increaseThread.start();
        decreaseThread.start();
    }
}
