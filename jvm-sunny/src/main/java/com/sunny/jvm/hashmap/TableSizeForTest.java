package com.sunny.jvm.hashmap;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/10/23 14:02 <br>
 * @see com.sunny.jvm.hashmap <br>
 */
public class TableSizeForTest {
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        System.out.println("cap - 1 = " + n);

        n |= n >>> 1;
        System.out.println("n |= n >>> 1 = " + n);
        n |= n >>> 2;
        System.out.println("n |= n >>> 2 = " + n);
        n |= n >>> 4;
        System.out.println("n |= n >>> 4 = " + n);
        n |= n >>> 8;
        System.out.println("n |= n >>> 8 = " + n);
        n |= n >>> 16;
        System.out.println("n |= n >>> 16 = " + n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        System.out.println("MAXIMUM_CAPACITY is " + MAXIMUM_CAPACITY);
        System.out.println(tableSizeFor(536870913));
        System.out.println(Math.pow(2, 30));
        System.out.println(1023|(1023 >>> 2));
        System.out.println(15&20);
    }
}
