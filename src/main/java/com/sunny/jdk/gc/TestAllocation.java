package com.sunny.jdk.gc;

/**
 * <Description> <br>
 *
 * @author Sunny<br>
 * @version 1.0<br>
 * @taskId: <br>
 * @createDate 2018/09/11 11:12 <br>
 * @see com.sunny.jdk.gc <br>
 */
public class TestAllocation {
    private static final Integer _1MB = 1024 * 1024;

    /**
     *  V M 参数 : -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *  -XX:+UseSerialGC 设置使用Serial + Serial Old 收集器组合
     *  -Xms20M -Xmx20M -Xmn10M  限制Java堆大小为20M，10M分配给新生代，10M分配给老年代
     *  -XX:+PrintGCDetails 打印GC日志
     *  -XX:SurvivorRatio=8 决定Eden区与一个Survivor区的空间比例是 8 : 1 新生代总可用9M（Eden区 + 1个Survivor区）
     */
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[5 * _1MB]; // 出现Minor GC
    }
}
