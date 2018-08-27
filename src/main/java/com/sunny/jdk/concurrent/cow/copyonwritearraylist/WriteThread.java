package com.sunny.jdk.concurrent.cow.copyonwritearraylist;

import java.util.List;
import java.util.Random;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/27 13:14 <br>
 * @see com.sunny.jdk.concurrent.cow.copyonwritearraylist <br>
 */
public class WriteThread implements Runnable {
    private List<Integer> list;

    public WriteThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        Integer num = new Random().nextInt(10);
        this.list.add(num);
        System.out.println("Write Thread:" + num);
    }
}