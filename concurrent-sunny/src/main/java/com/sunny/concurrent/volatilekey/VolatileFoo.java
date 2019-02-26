package com.sunny.concurrent.volatilekey;

import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/02/18 10:27 <br>
 * @see com.sunny.concurrent.volatilekey <br>
 */
public class VolatileFoo {
    /**
     * init_value的最大值
     */
    final static int MAX = 5;
    /**
     * init_value的初始值
     */
    static int init_value = 0;

    public static void main(String[] args)
    {
        int x = 10;
        x = x++;
        System.out.println();

        /**
         * 启动一个Reader线程，当发现local_value和init_value不同时，则输出init_value被修改的信息
         */
        new Thread(() ->
        {
            int localValue = init_value;
            while (localValue < MAX)
            {
                if (init_value != localValue)
                {
                    System.out.printf("Current thread is [%s] and the init_value is updated to [%d]\n", Thread.currentThread().getName(),init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        /**
         * 启动一个Updater线程，主要用于对init_value的修改，当localValue >= 5 时，则退出生命周期
         */
        new Thread(() ->
        {
            int localValue = init_value;
            while (localValue < MAX)
            {
                System.out.printf("Current thread is [%s] and the init_value will be changed to [%d]\n", Thread.currentThread().getName(),  ++localValue);
                init_value = localValue;
                try
                {
                    // 短暂休眠，目的是为了使Reader线程能够来得及输出变化内容
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
