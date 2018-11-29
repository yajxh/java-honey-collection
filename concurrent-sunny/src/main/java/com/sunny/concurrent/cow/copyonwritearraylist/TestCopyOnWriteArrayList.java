package com.sunny.concurrent.cow.copyonwritearraylist;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <Description> 证明CopyOnWriteArrayList是线程安全的<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/27 13:14 <br>
 * @see com.sunny.concurrent.cow.copyonwritearraylist <br>
 */
public class TestCopyOnWriteArrayList {
    private void testCopyOnWriteArrayList() {
        //1、初始化CopyOnWriteArrayList
        List<Integer> tempList = Arrays.asList(new Integer [] {1,2});
        CopyOnWriteArrayList<Integer> copyList = new CopyOnWriteArrayList<>(tempList);


        //2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.execute(new ReadThread(copyList));
        executorService.execute(new WriteThread(copyList));
        executorService.shutdown();

        System.out.println("copyList size:"+copyList.size());
    }
    private void testArrayList() {
        //1、初始化CopyOnWriteArrayList
        List<Integer> arrList = new ArrayList();
        arrList.add(1);
        arrList.add(2);


        //2、模拟多线程对list进行读和写
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new ReadThread(arrList));
        executorService.execute(new WriteThread(arrList));
        executorService.execute(new WriteThread(arrList));
        executorService.execute(new WriteThread(arrList));
        executorService.execute(new ReadThread(arrList));
        executorService.execute(new WriteThread(arrList));
        executorService.execute(new ReadThread(arrList));
        executorService.execute(new WriteThread(arrList));
        executorService.shutdown();

        System.out.println("arrList size:"+ arrList.size());
    }


    public static void main(String[] args) {
        TestCopyOnWriteArrayList tcowal = new TestCopyOnWriteArrayList();
        tcowal.testCopyOnWriteArrayList();
        //tcowal.testArrayList();
    }
}
