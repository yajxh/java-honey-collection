package com.sunny.concurrent.thread.threadlocal;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/27 10:56 <br>
 * @see com.sunny.concurrent.thread.threadlocal <br>
 */
public class MemoryLeak {
    public static void main(String[] args) {
        for (int i = 0; i < 1100000; i++) {
            ThreadLocal t = new ThreadLocal();
            t.set(new Object());
            t.set(null);
        }
    }
}
