package com.sunny.concurrent.thread.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/12/28 3:42 <br>
 * @see com.sunny.concurrent.thread.threadlocal <br>
 */
public class SequenceNumber {
    // 使用lambda表达式定义ThreadLocal
    private static ThreadLocal<Integer> seqNum = ThreadLocal.withInitial(() -> 0);

    // 下一个序列号
    public int getNextNum() {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }
    private static class TestClient extends Thread {
        private SequenceNumber sn;
        public TestClient(SequenceNumber sn) {
            this.sn = sn;
        }
        // 线程产生序列号
        @Override
        public void run() {
            try {
                //Thread.sleep(10000); //方便多线程调试
                TimeUnit.MILLISECONDS.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[" + Thread.currentThread().getName()   + "] sn[" + sn.getNextNum() + "]");
            }
        }
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        SequenceNumber sn = new SequenceNumber();
        // 三个线程产生各自的序列号
        TestClient t1 = new TestClient(sn);
        TestClient t2 = new TestClient(sn);
        TestClient t3 = new TestClient(sn);
        t1.start();
        //Thread.sleep(50000); //方便多线程调试
        TimeUnit.MILLISECONDS.sleep(10000);
        t2.start();
        //Thread.sleep(50000); //方便多线程调试
        TimeUnit.MILLISECONDS.sleep(10000);
        t3.start();
    }
}
