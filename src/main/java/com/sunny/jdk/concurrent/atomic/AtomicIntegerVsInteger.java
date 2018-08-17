package com.sunny.jdk.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <Description> Atomics can be used from the java.util.concurrent.atomic.* package.
 *
 * An atomic operation is a compound action that totally completes out totally
 * fails, not supporting inconsistent values or results during it's execution.
 *
 * The classes in this package supports atomic operations on single variables,
 * having get and set (read and write) methods that behave like a volatile
 * variable.
 *
 * The compareAndSet are commonly used in non-blocking algorithms. They
 * basically tries to set a new value to a specified field, and it returns a
 * boolean indicating success or not. All atomic, only blocking briefly.
 *
 * Interesting classes in this package are: AtomicBoolean, AtomicLong,
 * AtomicReference<T>, AtomicMarkableReference<T> and
 * AtomicReferenceFieldUpdater<T, V>. <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/08/17 15:49 <br>
 * @see com.sunny.jdk.concurrent.atomic <br>
 */
public class AtomicIntegerVsInteger {

    /**
     * A Counter using AtomicInteger
     */
    static class AtomicCounter {
        private AtomicInteger atomicInteger = new AtomicInteger(0);

        public void increment() {
            atomicInteger.incrementAndGet();
        }

        public void decrement() {
            atomicInteger.decrementAndGet();
        }

        public int get() {
            return atomicInteger.get();
        }
    }

    static class IntegerCounter {
        private Integer count = new Integer(0);

        public void increment() {
           count++;
        }

        public void decrement() {
            count--;
        }

        public int get() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicCounter aCounter = new AtomicCounter();
        ExecutorService atomicExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10_000; i++) {
            atomicExecutor.execute(() -> aCounter.increment());
        }

        atomicExecutor.shutdown();
        atomicExecutor.awaitTermination(4000, TimeUnit.SECONDS);
        System.out.println("Result of aCounter shound be 10000: Actual result is: " + aCounter.get());

        IntegerCounter iCounter = new IntegerCounter();
        ExecutorService integerExecutor = Executors.newCachedThreadPool();
        for (int i = 0; i < 10_000; i++) {
            integerExecutor.execute(() -> iCounter.increment());
        }

        integerExecutor.shutdown();
        integerExecutor.awaitTermination(4000, TimeUnit.SECONDS);
        System.out.println("Result of iCounter shound be 10000: Actual result is: " + iCounter.get());

    }
}
