package com.sunny.concurrent.cow.copyonwritearraylist;

import java.util.List;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/27 13:14 <br>
 * @see com.sunny.concurrent.cow.copyonwritearraylist <br>
 */
public class ReadThread implements Runnable {
    private List<Integer> list;

    public ReadThread(List<Integer> list) {
        this.list = list;
    }

    @Override
    public void run() {
        for (Integer ele : list) {
            System.out.println("ReadThread:"+ele);
        }
    }
}