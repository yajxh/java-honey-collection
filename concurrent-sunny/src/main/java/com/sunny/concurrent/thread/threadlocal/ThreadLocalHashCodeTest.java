package com.sunny.concurrent.thread.threadlocal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <Description> 检验ThreadLocal中0x61c88647数字的散列能力<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2019/01/02 15:49 <br>
 * @see com.sunny.concurrent.thread.threadlocal <br>
 */
public class ThreadLocalHashCodeTest {
    public static void main(String[] args) {
        AtomicInteger hashCode = new AtomicInteger();
        int hash_increment = 0x61c88647;
        int size = 32;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(hashCode.getAndAdd(hash_increment) & (size - 1));
        }
        System.out.println("Original: " + list);
        Collections.sort(list);
        System.out.println("Sorted: " + list);
    }
}
