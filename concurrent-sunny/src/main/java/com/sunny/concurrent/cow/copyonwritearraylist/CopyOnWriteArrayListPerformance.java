package com.sunny.concurrent.cow.copyonwritearraylist;

import java.util.*;
import java.util.concurrent.*;

/**
 * <Description> CopyOnWriteArrayList适用于读操作比写操作多很多的并发场景。
 *          如果读操作和写操作的频次相差不大时，建议使用Collections.synchornizedList。<br>
 *
 *     运行结果如下：
 *     addSynchornizedTime:34
 *     getSynchornizedTime:26
 *     addCopyOnWriteArrayListTime:161521
 *     getCopyOnWriteArrayListTime:3
 *     Process finished with exit code 0
 *     可以从运行结果中得出结论：
 *     Collections.synchronizedList的整体的读与写性能都比较稳定。而CopyOnWriteArrayList在写方面，表现的非常差，在读操作上，却非常优秀。
 *     所以CopyOnWriteArrayList适合使用在读操作比较多的并发场景。
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/27 14:23 <br>
 * @see com.sunny.concurrent.cow.copyonwritearraylist <br>
 */
public class CopyOnWriteArrayListPerformance {
    private static final int SIZE = 100000;

    public static long testAddList(List<Integer> list){
        long startTime = System.currentTimeMillis();
        for(int i = 0; i <SIZE; i++){
            list.add(i);
        }
        long time= System.currentTimeMillis() - startTime;
        return time;
    }

    public static long testGetList(List<Integer> list){
        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            list.get(i);
        }
        long time= System.currentTimeMillis() - start;
        return time;
    }


    public static void main(String [] args){
        ArrayList<Integer> list = new ArrayList<>();
        List<Integer> list2 = Collections.synchronizedList(list);
        CopyOnWriteArrayList<Integer> list3 = new CopyOnWriteArrayList<>();

        //多线程测试性能;
        long addSynchronizedListTime = 0L, addCopyOnWriteArrayListTime = 0L;
        long getSynchronizedListTime = 0L, getCopyOnWriteArrayListTime = 0L;
        ExecutorService service = Executors.newCachedThreadPool();

        //测试synchornizedList的写和读操作的性能;
        for(int i = 0 ; i <5; i++) {
            try {
                addSynchronizedListTime += service.submit(new AddDataRunnable(list2)).get();
                getSynchronizedListTime += service.submit(new GetDataRunnable(list2)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        //测试CopyOnWriteArrayList的写和读操作性能;
        for(int i = 0 ; i <5; i++) {
            try {
                addCopyOnWriteArrayListTime += service.submit(new AddDataRunnable(list3)).get();
                getCopyOnWriteArrayListTime += service.submit(new GetDataRunnable(list3)).get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("addSynchornizedTime:"+addSynchronizedListTime);
        System.out.println("getSynchornizedTime:"+getSynchronizedListTime);
        System.out.println("addCopyOnWriteArrayListTime:"+addCopyOnWriteArrayListTime);
        System.out.println("getCopyOnWriteArrayListTime:"+getCopyOnWriteArrayListTime);

    }

    static class AddDataRunnable implements Callable<Long>{

        private List<Integer> mList;
        public AddDataRunnable(List<Integer> l){
            this.mList = l;
        }

        @Override
        public Long call() throws Exception {
            long time = testAddList(mList);
            return time;
        }
    }

    static class GetDataRunnable implements Callable<Long> {

        private List<Integer> mList;
        public GetDataRunnable(List<Integer> l){
            this.mList = l;
        }

        @Override
        public Long call() throws Exception {
            long time = testGetList(mList);
            return time;
        }
    }
}
