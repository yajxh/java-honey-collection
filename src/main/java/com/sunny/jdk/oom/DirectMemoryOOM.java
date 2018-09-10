package com.sunny.jdk.oom;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * <Description>
 *  使用 unsafe 分配本机内存
 * VM Args: -Xmx20M -XX:MaxDirectMemorySize=10M<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/06 13:14 <br>
 * @see com.sunny.jdk.oom <br>
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
