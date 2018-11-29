package com.sunny.jvm.gc;

/**
 * <Description> 引用计数算法的缺陷<br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/06 14:01 <br>
 * @see com.sunny.jvm.gc <br>
 */
public class ReferenceCountingGC {
    public Object instance = null;

    public static final int _1MB = 1024 * 1024;

    /**
     * 占点内存，以便GC日志观看
     */
    private byte[] bigSize = new byte[2 * _1MB];

    public static void main(String[] args) {
        testGC();
    }

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        //这里发生GC， objA 和 objB能否被回收？
        System.gc();
    }

}
